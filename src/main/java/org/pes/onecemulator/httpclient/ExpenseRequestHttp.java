package org.pes.onecemulator.httpclient;

import lombok.AllArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@AllArgsConstructor
public class ExpenseRequestHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestHttp.class);

    private String host;

    private String uri;

    private String token;

    private String number;

    private String sum;

    private boolean paid;

    private String currency;

    private String code;

    private String documentName;

    private boolean confirm;

    private LocalDate date;

    private String source;

    private DateTimeFormatter formatter;

    public void call() {
        final String params =
                String.join(",", number, sum, paid ? "1" : "0", currency, code, documentName, confirm ? "1" : "0");
        final HttpGet httpGet = new HttpGet(host + uri + "/" + params + "/" + date.format(formatter));
        httpGet.setHeader("crm-api-token", token);
        httpGet.setHeader("crm-1c-database-source", source);

        LOGGER.info("Request to CRM: " + httpGet.toString() + " : " + Arrays.toString(httpGet.getAllHeaders()));

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("Status code not equal 200.");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
