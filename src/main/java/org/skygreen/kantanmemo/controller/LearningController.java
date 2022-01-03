package org.skygreen.kantanmemo.controller;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/learning")
public class LearningController {
    @GET
    @Path("/pull")
    @Produces("application/json")
    public Response pull(@CookieParam(value = "user_id") Long userId) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
