package org.skygreen.kantanmemo.controller;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.skygreen.kantanmemo.data.FileUploadForm;
import org.skygreen.kantanmemo.service.IWordlistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Path("/wordlist")
public class WordlistController {
    @Inject
    IWordlistService wordlistService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public Response upload(@MultipartForm FileUploadForm form) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(form.file, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        var body = textBuilder.toString();
        try {
            var result = wordlistService.uploadCsv(form.filename, body);
            return Response.ok(result).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response all() {
        try {
            var result = wordlistService.getAllWordlists();
            return Response.ok(result).build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
