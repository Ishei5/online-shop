package com.pankov.roadtosenior.onlineshop.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {

    private static final PageGenerator PAGE_GENERATOR_INSTANCE = new PageGenerator();
    private Configuration configuration;

    public PageGenerator() {
        configuration = new Configuration(Configuration.VERSION_2_3_31);
    }

    public static PageGenerator getInstance() {
        return PAGE_GENERATOR_INSTANCE;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer writer = new StringWriter();

        try {
            Template template = getTemplate(fileName);
            template.process(data, writer);
        } catch (IOException | TemplateException exception) {
            throw new RuntimeException(exception);
        }

        return writer.toString();
    }

    private Template getTemplate(String name) throws IOException {
        configuration.setClassForTemplateLoading(getClass(), "/templates");

        return configuration.getTemplate(name);
    }
}
