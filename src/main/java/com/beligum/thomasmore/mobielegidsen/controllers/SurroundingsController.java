package com.beligum.thomasmore.mobielegidsen.controllers;

import com.beligum.base.server.R;
import com.beligum.base.utils.Logger;
import com.beligum.blocks.config.StorageFactory;
import com.beligum.blocks.fs.index.entries.pages.IndexSearchResult;
import com.beligum.blocks.fs.index.entries.pages.PageIndexEntry;
import com.beligum.blocks.fs.index.entries.pages.SparqlIndexEntry;
import com.beligum.blocks.fs.index.ifaces.LuceneQueryConnection;
import com.beligum.blocks.fs.index.ifaces.PageIndexConnection;
import com.beligum.blocks.templating.blocks.DefaultTemplateController;
import com.beligum.thomasmore.mobielegidsen.beans.app.Schorre;
import com.beligum.thomasmore.mobielegidsen.config.Settings;
import com.beligum.thomasmore.mobielegidsen.rdf.Classes;
import com.beligum.thomasmore.mobielegidsen.rdf.Terms;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.TPAccessRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.Node;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * Created by bram on 9/27/16.
 */
public class SurroundingsController extends DefaultTemplateController
{
    //-----CONSTANTS-----

    //-----VARIABLES-----
    private Set<Node> horecaEntries;

    //-----CONSTRUCTORS-----
    @Override
    public void created()
    {
        try {
            TPAccessRepository tpAccessRepo = Settings.instance().getTpAccessRepository();

            //because of the slowness of TPAccess, it takes ages (half an hour) to rebuild the cache,
            // so let's group the locations around the schorre together.
            final double radiusKm = 10.0;
            Schorre schorre = new Schorre();
            double[] coordinates = getCoordinates();
            double distanceToSchorre = this.haversine(coordinates[0], coordinates[1], schorre.getLocation().getLatitude(), schorre.getLocation().getLongitude());
            if (distanceToSchorre < radiusKm) {
                coordinates = new double[] { schorre.getLocation().getLatitude(), schorre.getLocation().getLongitude() };
            }

            this.horecaEntries = tpAccessRepo.findHorecaNear(coordinates[0], coordinates[1], radiusKm);
        }
        catch (Exception e) {
            Logger.error("Error while initializing this controller; ", e);
        }
    }

    //-----PUBLIC METHODS-----
    public static double[] getCoordinates() throws IOException
    {
        double[] retVal = new double[2];

        URI currentUri = R.requestContext().getJaxRsRequest().getUriInfo().getRequestUri();
        UriBuilder uriBuilder = UriBuilder.fromUri(currentUri);
        Locale language = R.i18nFactory().getUrlLocale(currentUri, uriBuilder, null);
        URI currentUriNoLang = uriBuilder.build();

        //Note: we should actually re-use the connection above but we have an interface mismatch
        LuceneQueryConnection queryConnection = StorageFactory.getMainPageQueryConnection();

        BooleanQuery pageQuery = new BooleanQuery();
        pageQuery.add(new TermQuery(new Term(PageIndexEntry.Field.typeOf.name(), Classes.Route.getCurieName().toString())), BooleanClause.Occur.FILTER);
        pageQuery.add(queryConnection.buildWildcardQuery(PageIndexEntry.Field.canonicalAddress.name(), currentUriNoLang.getPath(), false), BooleanClause.Occur.MUST);

        IndexSearchResult results = queryConnection.search(pageQuery, -1);

        if (results.getTotalHits() > 0) {
            PageIndexEntry first = (PageIndexEntry) results.getResults().get(0);
            PageIndexConnection conn = (PageIndexConnection) StorageFactory.getTriplestoreQueryConnection();
            SparqlIndexEntry entry = (SparqlIndexEntry) conn.get(URI.create(first.getResource()));

            Double latitude = Double.valueOf(entry.getObject(Terms.latitude));
            Double longitude = Double.valueOf(entry.getObject(Terms.longitude));

            retVal = new double[] { latitude, longitude };
        }

        return retVal;
    }
    public Iterator<Node> getCateringEntries()
    {
        return Iterables.filter(this.horecaEntries, new Predicate<Node>()
        {
            @Override
            public boolean apply(Node p)
            {
                return TPAccessRepository.isFoodRelatedEntry(p);
            }
        }).iterator();
    }
    public Iterator<Node> getLodgingEntries()
    {
        return Iterables.filter(this.horecaEntries, new Predicate<Node>()
        {
            @Override
            public boolean apply(Node p)
            {
                return TPAccessRepository.isLodgingRelatedEntry(p);
            }
        }).iterator();
    }

    //-----PROTECTED METHODS-----

    //-----PRIVATE METHODS-----
    //see https://rosettacode.org/wiki/Haversine_formula#Java
    private double haversine(double lat1, double lon1, double lat2, double lon2)
    {
        final double R = 6372.8; // In kilometers

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return R * c;
    }
}
