package com.beligum.thomasmore.mobielegidsen.endpoints;

import com.beligum.base.templating.ifaces.Template;
import com.beligum.thomasmore.mobielegidsen.beans.app.Schorre;
import com.beligum.thomasmore.mobielegidsen.beans.app.ifaces.Route;
import com.beligum.thomasmore.mobielegidsen.config.Settings;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.TPAccessRepository;
import com.beligum.thomasmore.mobielegidsen.repositories.tpaccess.beans.search.Node;
import gen.com.beligum.thomasmore.mobielegidsen.fs.html.views.app.route.index;
import gen.com.beligum.thomasmore.mobielegidsen.fs.html.views.app.route.map;
import gen.com.beligum.thomasmore.mobielegidsen.fs.html.views.app.route.overview;
import gen.com.beligum.thomasmore.mobielegidsen.fs.html.views.app.route.stop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/")
public class ApplicationEndpoint
{
    //-----CONSTANTS-----

    //-----VARIABLES-----

    //-----STATIC METHODS-----

    //-----PUBLIC METHODS-----
    @GET
    @Path("/test")
    public Response routeIndex() throws Exception
    {
        TPAccessRepository tpAccessRepo = Settings.instance().getTpAccessRepository();

        Schorre schorre = new Schorre();
        Set<Node> retVal = tpAccessRepo.findHorecaNear(schorre.getLocation().getLatitude(), schorre.getLocation().getLongitude(), 10.0);

        return Response.ok("DEBUG ONLY").build();
    }
    //    @GET
    //    //HACK: we need to use a dummy here to get past the blocks entry page in priority order
    //    @Path("/{randomPage:.*}{dummy:.*}")
    //    public Response index()
    //    {
    //        Region region = Settings.INSTANCE.getRegion();
    //        Template template = gen.com.beligum.thomasmore.mobielegidsen.fs.html.views.site.index.get().getNewTemplate();
    //        template.set("region", region);
    //        return Response.ok(template).build();
    //    }
    @GET
    @Path("/route/{routeSlug}")
    public Response routeIndex(@PathParam("routeSlug") String routeSlug)
    {
        Route route = Settings.instance().getRegion().getRoutes().get(routeSlug);
        Template template = index.get().getNewTemplate();
        template.set("route", route);

        return Response.ok(template).build();
    }
    @GET
    @Path("/route/{routeSlug}/overview")
    public Response routeOverview(@PathParam("routeSlug") String routeSlug)
    {
        Route route = Settings.instance().getRegion().getRoutes().get(routeSlug);
        Template template = overview.get().getNewTemplate();
        template.set("route", route);

        return Response.ok(template).build();
    }
    @GET
    @Path("/route/{routeSlug}/map")
    public Response routeMap(@PathParam("routeSlug") String routeSlug)
    {
        Route route = Settings.instance().getRegion().getRoutes().get(routeSlug);
        Template template = map.get().getNewTemplate();
        template.set("route", route);

        return Response.ok(template).build();
    }
    @GET
    @Path("/route/{routeSlug}/{stopSlug}")
    public Response routeStop(@PathParam("routeSlug") String routeSlug, @PathParam("stopSlug") String stopSlug)
    {
        Route route = Settings.instance().getRegion().getRoutes().get(routeSlug);
        Template template = stop.get().getNewTemplate();
        template.set("route", route);
        template.set("stop", route.getStops().get(stopSlug));

        return Response.ok(template).build();
    }

    //-----PRIVATE METHODS-----
}