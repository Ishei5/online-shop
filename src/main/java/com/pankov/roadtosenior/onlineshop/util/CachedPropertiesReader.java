package com.pankov.roadtosenior.onlineshop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CachedPropertiesReader {
    private final String path;

    private Properties cachedProperties;

    public CachedPropertiesReader(String path) {
        this.path = path;
        this.cachedProperties = readProperties();
    }

    public Properties getProperties() {
        return new Properties(cachedProperties);
    }

    private Properties readProperties() {
        cachedProperties = new Properties();

        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path)) {
            cachedProperties.load(inputStream);

            return cachedProperties;
        } catch (IOException exception) {
            throw new RuntimeException("Property file " + path + " not found");
        }
    }

    public Long getLongProperty(String propertyName) {
        return Long.valueOf(cachedProperties.getProperty(propertyName));
    }
}
