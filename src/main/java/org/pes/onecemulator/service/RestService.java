package org.pes.onecemulator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;

public abstract class RestService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String SLASH = "/";

    protected static final String CONTENT_TYPE = "Content-Type";

    protected static final String APPLICATION_JSON = "application/json";

    protected static HttpClient createHttpClient() {
        return createHttpClientBuilder().build();
    }

    protected static HttpClient createHttpClient(final String username, final String password) {
        final HttpClient.Builder httpClientBuilder = createHttpClientBuilder();
        final char[] passwordCharArray = password != null ? password.toCharArray() : new char[0];
        httpClientBuilder.authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, passwordCharArray);
            }
        });

        return httpClientBuilder.build();
    }

    private static HttpClient.Builder createHttpClientBuilder() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1);
    }
}
