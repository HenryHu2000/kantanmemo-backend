package org.skygreen.kantanmemo.controller;

import org.apache.camel.ProducerTemplate;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dto.MultipartBody;
import org.skygreen.kantanmemo.service.IWordlistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Path("")
public class MainController {
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    WordDao wordDao;

    @Inject
    IWordlistService wordlistService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String upload(@MultipartForm MultipartBody requestBody) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(requestBody.file, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        var body = textBuilder.toString();
        var wordlistId = wordlistService.uploadCsv(requestBody.filename, body);
        return String.valueOf(wordlistId);
    }
}