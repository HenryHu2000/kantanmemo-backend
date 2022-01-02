package org.skygreen.kantanmemo.controller;

import org.skygreen.kantanmemo.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@FormParam(value = "user_id") Long userId) {
        var result = userService.login(userId);
        return Response.ok(result).build();
    }

}
