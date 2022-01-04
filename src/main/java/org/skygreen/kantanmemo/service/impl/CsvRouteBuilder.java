package org.skygreen.kantanmemo.service.impl;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.skygreen.kantanmemo.entity.Word;

public class CsvRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        DataFormat bindy = new BindyCsvDataFormat(Word.class);
        from("direct:csv-to-json").unmarshal(bindy);
    }
}
