package org.pes.onecemulator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;

public abstract class RestService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    protected static HttpClient.Builder getHttpClientBuilder() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1);
    }
}
