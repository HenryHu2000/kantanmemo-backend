package org.skygreen.kantanmemo.controller;

import org.apache.camel.ProducerTemplate;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dto.MultipartBody;
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
    ProducerTemplate producerTemplate;

    @Inject
    WordDao wordDao;

    @Inject
    IWordlistService wordlistService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/json")
    public Response upload(@MultipartForm MultipartBody requestBody) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(requestBody.file, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        var body = textBuilder.toString();
        var result = wordlistService.uploadCsv(requestBody.filename, body);
        return generateResponse(result);
    }

    @POST
    @Path("/select")
    @Produces("application/json")
    public Response select(@CookieParam(value = "user_id") Long userId, @FormParam(value = "wordlist_id") Long wordlistId) {
        var result = wordlistService.userSelectWordlist(userId, wordlistId);
        return generateResponse(result);
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response all() {
        return generateResponse(wordlistService.getAllWordlists());
    }

    private Response generateResponse(Object entity) {
        if (entity == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok(entity).build();
    }

}
