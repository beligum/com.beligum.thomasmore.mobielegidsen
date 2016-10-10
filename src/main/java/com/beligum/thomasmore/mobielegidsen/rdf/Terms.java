package com.beligum.thomasmore.mobielegidsen.rdf;

import com.beligum.blocks.config.InputType;
import com.beligum.blocks.rdf.ifaces.RdfProperty;
import com.beligum.blocks.rdf.ifaces.RdfResourceFactory;
import com.beligum.blocks.rdf.ontology.RdfPropertyImpl;
import com.beligum.blocks.rdf.ontology.vocabularies.SettingsVocabulary;
import com.beligum.blocks.rdf.ontology.vocabularies.XSD;
import gen.com.beligum.thomasmore.mobielegidsen.messages.mobielegidsen.ontology;

import java.net.URI;

/**
 * Useful search engines:
 * http://lov.okfn.org/dataset/lov/terms?q=destroyed
 * https://www.w3.org/wiki/VocabularyMarket
 * <p/>
 * Cross reference with: https://www.w3.org/2011/rdfa-context/rdfa-1.1
 * <p/>
 * Created by bram on 2/25/16.
 */
public class Terms implements RdfResourceFactory
{
    //-----CONSTANTS-----
    private static final int AUTOCOMPLETE_MAX_RESULTS = 10;

    //-----ENTRIES-----
    public static final RdfProperty latitude = new RdfPropertyImpl("latitude",
                                                                         SettingsVocabulary.INSTANCE,
                                                                         ontology.Entries.propertyTitle_latitude,
                                                                         ontology.Entries.propertyLabel_latitude,
                                                                         XSD.FLOAT,
                                                                         InputType.Number,
                                                                         null,
                                                                         new URI[] {},
                                                                         true);

    public static final RdfProperty longitude = new RdfPropertyImpl("longitude",
                                                                          SettingsVocabulary.INSTANCE,
                                                                          ontology.Entries.propertyTitle_longitude,
                                                                          ontology.Entries.propertyLabel_longitude,
                                                                          XSD.FLOAT,
                                                                          InputType.Number,
                                                                          null,
                                                                          new URI[] {},
                                                                          true);

    public static final RdfProperty zoom = new RdfPropertyImpl("zoom",
                                                                     SettingsVocabulary.INSTANCE,
                                                                     ontology.Entries.propertyTitle_zoom,
                                                                     ontology.Entries.propertyLabel_zoom,
                                                                     XSD.INT,
                                                                     InputType.Number,
                                                                     null,
                                                                     new URI[] {},
                                                                     true);

    public static final RdfProperty distance = new RdfPropertyImpl("distance",
                                                                   SettingsVocabulary.INSTANCE,
                                                                   ontology.Entries.propertyTitle_distance,
                                                                   ontology.Entries.propertyLabel_distance,
                                                                   XSD.FLOAT,
                                                                   InputType.Number,
                                                                   null,
                                                                   new URI[] {},
                                                                   true);

    public static final RdfProperty time = new RdfPropertyImpl("time",
                                                               SettingsVocabulary.INSTANCE,
                                                               ontology.Entries.propertyTitle_time,
                                                               ontology.Entries.propertyLabel_time,
                                                               XSD.INT,
                                                               InputType.Number,
                                                               null,
                                                               new URI[] {},
                                                               true);

    public static final RdfProperty path = new RdfPropertyImpl("path",
                                                               SettingsVocabulary.INSTANCE,
                                                               ontology.Entries.propertyTitle_path,
                                                               ontology.Entries.propertyLabel_path,
                                                               XSD.BASE64_BINARY,
                                                               InputType.InlineEditor,
                                                               null,
                                                               new URI[] {},
                                                               true);

    public static final RdfProperty poi = new RdfPropertyImpl("poi",
                                                              SettingsVocabulary.INSTANCE,
                                                              ontology.Entries.propertyTitle_poi,
                                                              ontology.Entries.propertyLabel_poi,
                                                              Classes.POI,
                                                              InputType.Resource,
                                                              Classes.DEFAULT_POI_ENDPOINT_CONFIG,
                                                              new URI[] {},
                                                              true);

    //-----CONFIGS-----
    /**
     * Need to come here, because we have a cyclic reference otherwise (we would be using the property during it's static initialization)
     */
    static {
        //        Terms.rcbPeriod.setWidgetConfig(new InputTypeConfig(new String[][] {
        //                        { gen.com.beligum.blocks.core.constants.blocks.core.Entries.INPUT_TYPE_CONFIG_RESOURCE_AC_ENDPOINT.getValue(),
        //                          //let's re-use the same endpoint for the enum as for the resources so we can re-use it's backend code
        //                          gen.com.beligum.blocks.endpoints.RdfEndpointRoutes.getResources(Terms.rcbPeriod.getCurieName(), -1, false, "").getAbsoluteUrl()
        //                        },
        //                        }));
    }

}
