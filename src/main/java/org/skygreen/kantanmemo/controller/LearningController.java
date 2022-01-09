package org.skygreen.kantanmemo.controller;

import org.skygreen.kantanmemo.service.ILearningService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/learning")
public class LearningController {
    @Inject
    ILearningService learningService;

    @GET
    @Path("/current")
    @Produces("application/json")
    public Response current(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try {
            var word = learningService.getCurrentWord(userId);
            return Response.ok(word).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/proceed")
    @Produces("application/json")
    public Response proceed(@CookieParam(value = "user_id") Long userId, @FormParam(value = "is_known") boolean isKnown) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try {
            var word = learningService.proceedToNextWord(userId, isKnown);
            return Response.ok(word).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/progress")
    @Produces("application/json")
    public Response progress(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try {
            var dailyProgress = learningService.getDailyProgress(userId);
            return Response.ok(dailyProgress).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/reset")
    @Produces("application/json")
    public Response reset(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try {
            var result = learningService.resetLearningProcess(userId);
            return Response.ok(result).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/more")
    @Produces("application/json")
    public Response more(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        try {
            var result = learningService.addNewToLearningProcess(userId);
            return Response.ok(result).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
