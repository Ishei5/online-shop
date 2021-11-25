package com.pankov.roadtosenior.onlineshop.web;

import com.pankov.roadtosenior.templater.Configuration;
import com.pankov.roadtosenior.templater.Template;

import java.util.Map;

public class PageGenerator {

    private static final PageGenerator PAGE_GENERATOR_INSTANCE = new PageGenerator();
    private Configuration configuration;

    public PageGenerator() {
        configuration = Configuration.getConfiguration();
        configuration.setPathToDirectory("templates");
    }

    public static PageGenerator getInstance() {
        return PAGE_GENERATOR_INSTANCE;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Template template = new Template();
        return template.process(fileName, data);
    }

}
