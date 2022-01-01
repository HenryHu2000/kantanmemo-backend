package org.skygreen.kantanmemo.controller;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.model.dataformat.CsvDataFormat;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.skygreen.kantanmemo.dao.WordDao;
import org.skygreen.kantanmemo.dto.MultipartBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Path("")
public class MainController {
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    WordDao wordDao;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String upload(@MultipartForm MultipartBody requestBody) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (requestBody.file, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        var csv = textBuilder.toString();
        var result = producerTemplate.requestBody("direct:csv-to-json", csv, List.class);
        wordDao.saveAll(result);
        return "";
    }
}