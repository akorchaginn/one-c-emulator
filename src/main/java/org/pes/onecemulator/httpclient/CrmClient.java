package org.pes.onecemulator.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class CrmClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmClient.class);

    private static final CloseableHttpClient client = HttpClients.createDefault();

    public static void expenseRequest(String url, String token, String sourceName) throws Exception {
        Objects.requireNonNull(url);
        Objects.requireNonNull(token);
        Objects.requireNonNull(sourceName);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("crm-api-token", token);
        httpGet.setHeader("crm-1c-database-source", sourceName);
        CloseableHttpResponse response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("Status code not equal 200.");
        }
    }
}
