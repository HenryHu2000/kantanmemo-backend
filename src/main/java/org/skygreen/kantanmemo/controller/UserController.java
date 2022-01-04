package org.skygreen.kantanmemo.controller;

import org.skygreen.kantanmemo.dto.UserSettingsDto;
import org.skygreen.kantanmemo.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserController {
    @Inject
    IUserService userService;

    @POST
    @Path("/register")
    @Produces("application/json")
    public Response register(@FormParam(value = "user_name") String name) {
        var result = userService.register(name);
        return Response.ok(result).build();
    }

    @GET
    @Path("/info")
    @Produces("application/json")
    public Response info(@QueryParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var result = userService.getUserInfo(userId);
        return Response.ok(result).build();
    }

    @GET
    @Path("/me")
    @Produces("application/json")
    public Response me(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var result = userService.getUserInfo(userId);
        return Response.ok(result).build();
    }

    @GET
    @Path("/settings")
    @Produces("application/json")
    public Response settings(@CookieParam(value = "user_id") Long userId) {
        if (userId == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var result = userService.getUserSettings(userId);
        return Response.ok(result).build();
    }

    @POST
    @Path("/settings/edit")
    @Produces("application/json")
    public Response settingsEdit(@CookieParam(value = "user_id") Long userId, UserSettingsDto userSettings) {
        if (userId == null || userSettings == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        var result = userService.setUserSettings(userId, userSettings);
        return Response.ok(result).build();
    }
}
