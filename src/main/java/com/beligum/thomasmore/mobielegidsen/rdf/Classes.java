package com.beligum.thomasmore.mobielegidsen.rdf;

import com.beligum.blocks.config.InputTypeConfig;
import com.beligum.blocks.rdf.ifaces.RdfClass;
import com.beligum.blocks.rdf.ifaces.RdfResourceFactory;
import com.beligum.blocks.rdf.ontology.RdfClassImpl;
import com.beligum.blocks.rdf.ontology.vocabularies.SettingsVocabulary;
import com.beligum.blocks.rdf.ontology.vocabularies.endpoints.SettingsQueryEndpoint;
import gen.com.beligum.thomasmore.mobielegidsen.messages.mobielegidsen.ontology;

import java.net.URI;

/**
 * Created by bram on 2/25/16.
 */
public class Classes implements RdfResourceFactory
{
    //-----ENTRIES-----
    public static final RdfClass Region = new RdfClassImpl("Region",
                                                          SettingsVocabulary.INSTANCE,
                                                          ontology.Entries.classTitle_Region,
                                                          ontology.Entries.classLabel_Region,
                                                          new URI[] {
                                                          },
                                                          true,
                                                          new SettingsQueryEndpoint(),
                                                          null
    );

    public static final RdfClass Route = new RdfClassImpl("Route",
                                                          SettingsVocabulary.INSTANCE,
                                                          ontology.Entries.classTitle_Route,
                                                          ontology.Entries.classLabel_Route,
                                                          new URI[] {
                                                              },
                                                          true,
                                                          new SettingsQueryEndpoint(),
                                                          null
    );

    public static final RdfClass POI = new RdfClassImpl("POI",
                                                          SettingsVocabulary.INSTANCE,
                                                          ontology.Entries.classTitle_POI,
                                                          ontology.Entries.classLabel_POI,
                                                          new URI[] {
                                                          },
                                                          true,
                                                          new SettingsQueryEndpoint(),
                                                          null
    );

    //-----CONFIGS-----
    public static final InputTypeConfig DEFAULT_POI_ENDPOINT_CONFIG = new InputTypeConfig(new String[][] {
                    { gen.com.beligum.blocks.core.constants.blocks.core.Entries.INPUT_TYPE_CONFIG_RESOURCE_AC_ENDPOINT.getValue(),
                      gen.com.beligum.blocks.endpoints.RdfEndpointRoutes
                                      .getResources(Classes.POI.getCurieName(), com.beligum.blocks.rdf.ontology.factories.Classes.AUTOCOMPLETE_MAX_RESULTS, true, "").getAbsoluteUrl()
                    },
                    { gen.com.beligum.blocks.core.constants.blocks.core.Entries.INPUT_TYPE_CONFIG_RESOURCE_VAL_ENDPOINT.getValue(),
                      gen.com.beligum.blocks.endpoints.RdfEndpointRoutes
                                      .getResource(Classes.POI.getCurieName(), URI.create("")).getAbsoluteUrl()
                    },
                    { gen.com.beligum.blocks.core.constants.blocks.core.Entries.INPUT_TYPE_CONFIG_RESOURCE_MAXRESULTS.getValue(),
                      "" + com.beligum.blocks.rdf.ontology.factories.Classes.AUTOCOMPLETE_MAX_RESULTS
                    }
    });

}
