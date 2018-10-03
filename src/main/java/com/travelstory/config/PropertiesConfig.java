package com.travelstory.config;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesConfig {
    private static ClassLoader classLoader = PropertiesConfig.class.getClassLoader();
    private static final String APP_PROPERTIES = "app.properties";

    private static Properties getConfigurationPropetries() {
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(APP_PROPERTIES));
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
        return properties;
    }

    public static String getPropertyValue(String key) {
        return String.valueOf(getConfigurationPropetries().getProperty(key));
    }
}
