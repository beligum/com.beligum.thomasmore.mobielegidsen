package com.beligum.thomasmore.mobielegidsen.rdf;

import com.beligum.blocks.rdf.ifaces.RdfResourceFactory;
import com.google.common.collect.Sets;

/**
 * Created by bram on 3/23/16.
 */
public class Mappings implements RdfResourceFactory
{
    //-----ENTRIES-----
    static {
        Classes.Region.setProperties(Sets.newHashSet(
                        com.beligum.blocks.rdf.ontology.factories.Terms.title,
                        com.beligum.blocks.rdf.ontology.factories.Terms.image,
                        com.beligum.blocks.rdf.ontology.factories.Terms.text,
                        com.beligum.blocks.rdf.ontology.factories.Terms.sameAs
        ));

        Classes.Route.setProperties(Sets.newHashSet(
                        com.beligum.blocks.rdf.ontology.factories.Terms.title,
                        com.beligum.blocks.rdf.ontology.factories.Terms.image,
                        com.beligum.blocks.rdf.ontology.factories.Terms.text,
                        com.beligum.blocks.rdf.ontology.factories.Terms.sameAs,

                        Terms.latitude,
                        Terms.longitude,
                        Terms.zoom,
                        Terms.distance,
                        Terms.time,
                        Terms.path,
                        Terms.poi
        ));

        Classes.POI.setProperties(Sets.newHashSet(
                        com.beligum.blocks.rdf.ontology.factories.Terms.title,
                        com.beligum.blocks.rdf.ontology.factories.Terms.image,
                        com.beligum.blocks.rdf.ontology.factories.Terms.text,
                        com.beligum.blocks.rdf.ontology.factories.Terms.sameAs,

                        com.beligum.blocks.rdf.ontology.factories.Terms.type,
                        Terms.latitude,
                        Terms.longitude
        ));

    }
}
