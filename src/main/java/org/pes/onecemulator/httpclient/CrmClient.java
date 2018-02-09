package org.pes.onecemulator.httpclient;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public final class CrmClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmClient.class);

    private static AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

    public static void expenseRequest(String url, String token, String sourceName, UUID id) {
        try {
            Objects.requireNonNull(url);
            Objects.requireNonNull(token);
            Objects.requireNonNull(sourceName);
            Objects.requireNonNull(id);
            Request request = asyncHttpClient
                    .prepareGet(url)
                    .addHeader("crm-api-token", token)
                    .addHeader("crm-1c-database-source", sourceName)
                    .build();
            LOGGER.info("Start request to CRM: " + request.getUrl() + " : " + request.getHeaders().toString());
            asyncHttpClient.executeRequest(request, new RequestCompletionHandler(id));
        } catch (Exception e) {
            LOGGER.error("Error request to CRM: ", e);
        }
    }
}
