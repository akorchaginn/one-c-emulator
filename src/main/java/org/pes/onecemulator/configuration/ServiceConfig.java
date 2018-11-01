package org.pes.onecemulator.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.pes.onecemulator")
@PropertySource(value = {"classpath:application.properties", "file:/etc/one-c/application.properties"}, ignoreResourceNotFound = true)
public class ServiceConfig {
}
