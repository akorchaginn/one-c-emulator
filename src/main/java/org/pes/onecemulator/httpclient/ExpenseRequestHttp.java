package org.pes.onecemulator.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.pes.onecemulator.entity.AccountingEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class ExpenseRequestHttp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestHttp.class);

    private String crmUri;

    private String crmToken;

    private String number;

    private BigDecimal sum;

    private Boolean paid;

    private String currency;

    private String code;

    private String documentName;

    private Boolean confirm;

    private LocalDate date;

    private String source;

    private DateTimeFormatter formatter;

    public ExpenseRequestHttp(AccountingEntry accountingEntry, String host, String uri, String token) {
        Objects.requireNonNull(accountingEntry);
        Objects.requireNonNull(accountingEntry.getExpenseRequest());
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getSource());
        Objects.requireNonNull(host);
        Objects.requireNonNull(uri);
        Objects.requireNonNull(token);

        this.crmUri = host + uri;
        this.crmToken = token;
        this.number = accountingEntry.getExpenseRequest().getNumber();
        this.sum = accountingEntry.getSum();
        this.paid = accountingEntry.getExpenseRequest().getPaid();
        this.currency = accountingEntry.getExpenseRequest().getCurrency();
        this.code = accountingEntry.getCode();
        this.documentName = accountingEntry.getDocumentName();
        this.confirm = accountingEntry.getExpenseRequest().getConfirm();
        this.date = accountingEntry.getDate();
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.source = accountingEntry.getExpenseRequest().getSource().getName();
    }

    public String call() throws Exception {
        HttpGet httpGet = prepareRequest();
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
        String params = new StringJoiner(",")
                .add(number)
                .add(sum.toString())
                .add(paid ? "1" : "0")
                .add(currency)
                .add(code)
                .add(documentName)
                .add(confirm ? "1" : "0")
                .toString();
        HttpGet request = new HttpGet(crmUri + "/" + params + "/" + date.format(formatter));
        request.setHeader("crm-api-token", crmToken);
        request.setHeader("crm-1c-database-source", source);
        return request;
    }
}
