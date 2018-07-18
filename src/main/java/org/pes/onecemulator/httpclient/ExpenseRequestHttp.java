package org.pes.onecemulator.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExpenseRequestHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestHttp.class);

    private String crmUri;

    private String crmToken;

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

    public ExpenseRequestHttp(final AccountingEntry accountingEntry, final String host, final String uri, final String token) {
        Objects.requireNonNull(accountingEntry);
        Objects.requireNonNull(accountingEntry.getExpenseRequest());
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getSource());
        Objects.requireNonNull(host);
        Objects.requireNonNull(uri);
        Objects.requireNonNull(token);

        final ExpenseRequest expenseRequest = accountingEntry.getExpenseRequest();

        this.crmUri = host + uri;
        this.crmToken = token;
        this.number = expenseRequest.getNumber();
        this.sum = accountingEntry.getSum();
        this.paid = Boolean.TRUE.equals(expenseRequest.getPaid());
        this.currency = expenseRequest.getCurrency();
        this.code = accountingEntry.getCode();
        this.documentName = accountingEntry.getDocumentName();
        this.confirm = Boolean.TRUE.equals(expenseRequest.getConfirm());
        this.date = accountingEntry.getDate();
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.source = expenseRequest.getSource().getName();
    }

    public String call() throws Exception {
        final HttpGet httpGet = prepareRequest();

        LOGGER.info("Request to CRM: " + httpGet.toString() + " : " + Arrays.toString(httpGet.getAllHeaders()));

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("Status code not equal 200.");
                }
            }
            return httpGet.getURI().toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    private HttpGet prepareRequest() {
        final String params = Stream
                .of(number, sum, paid ? "1" : "0", currency, code, documentName, confirm ? "1" : "0")
                .collect(Collectors.joining(","));

        final HttpGet request = new HttpGet(crmUri + "/" + params + "/" + date.format(formatter));
        request.setHeader("crm-api-token", crmToken);
        request.setHeader("crm-1c-database-source", source);
        return request;
    }
}
