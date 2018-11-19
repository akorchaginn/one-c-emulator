package org.pes.onecemulator.httpclient;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Setter
@Accessors(chain = true)
public class ExpenseRequestHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestHttp.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final String CRM_API_TOKEN = "crm-api-token";

    private static final String CRM_1C_DATABASE_SOURCE = "crm-1c-database-source";

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

    public void call() throws Exception {
        validateParams();

        final HttpGet httpGet = createHttpRequest();

        LOGGER.info("Start request: " + httpGet.toString() + " -> " + headersToString(httpGet.getAllHeaders()));

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("Status code not equal 200.");
                }
            }
        }

        LOGGER.info("Request is sent.");
    }

    private HttpGet createHttpRequest() {
        final HttpGet httpGet = new HttpGet(host + uri + "/" + buildParamsString() + "/" + date.format(DATE_FORMATTER));
        httpGet.setHeader(CRM_API_TOKEN, token);
        httpGet.setHeader(CRM_1C_DATABASE_SOURCE, source);

        return httpGet;
    }

    private String buildParamsString() {
        return String.join(",",
                number, sum, booleanToString(paid), currency, code, documentName, booleanToString(confirm));
    }

    private void validateParams() {
        if (stringIsEmpty(host)) {
            throw new IllegalArgumentException("Host is null or empty");
        }
        if (stringIsEmpty(uri)) {
            throw new IllegalArgumentException("Uri is null or empty");
        }
        if (stringIsEmpty(token)) {
            throw new IllegalArgumentException("Token is null or empty");
        }
        if (stringIsEmpty(number)) {
            throw new IllegalArgumentException("Number is null or empty");
        }
        if (stringIsEmpty(sum)) {
            throw new IllegalArgumentException("Sum is null or empty");
        }
        if (stringIsEmpty(currency)) {
            throw new IllegalArgumentException("Currency is null or empty");
        }
        if (stringIsEmpty(code)) {
            throw new IllegalArgumentException("Code is null or empty");
        }
        if (stringIsEmpty(documentName)) {
            throw new IllegalArgumentException("DocumentName is null or empty");
        }
        if (stringIsEmpty(source)) {
            throw new IllegalArgumentException("Source is null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date is null");
        }
    }

    private String headersToString(Header[] headers) {
        return Arrays.stream(headers)
                .map(h -> h.getName() + ": " + h.getValue())
                .collect(Collectors.joining(", "));
    }

    private boolean stringIsEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private String booleanToString(boolean value) {
        return value ? "1" : "0";
    }
}
