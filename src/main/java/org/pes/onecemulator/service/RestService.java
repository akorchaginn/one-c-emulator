package org.pes.onecemulator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;

public abstract class RestService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String CONTENT_TYPE = "Content-Type";

    protected static final String APPLICATION_JSON = "application/json";

    protected static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    protected static HttpClient.Builder getHttpClientBuilder() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1);
    }
}
