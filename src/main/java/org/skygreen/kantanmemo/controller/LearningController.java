package org.skygreen.kantanmemo.controller;

import org.skygreen.kantanmemo.service.impl.LearningService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/learning")
public class LearningController {
    @Inject
    LearningService learningService;

    @GET
    @Path("/current")
    @Produces("application/json")
    public Response current(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var word = learningService.getCurrentWord(userId);
        return Response.ok(word).build();
    }

    @POST
    @Path("/proceed")
    @Produces("application/json")
    public Response proceed(@CookieParam(value = "user_id") Long userId, @FormParam(value = "is_known") boolean isKnown) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var word = learningService.proceedToNextWord(userId, isKnown);
        return Response.ok(word).build();
    }
}
