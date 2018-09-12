package org.pes.onecemulator.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(basePackages = "org.pes.onecemulator")
@PropertySources({
        @PropertySource(value = "classpath:application.properties"),
        @PropertySource(value = "file:/etc/one-c/application.properties", ignoreResourceNotFound = true)
})
public class ServiceConfig {
}
