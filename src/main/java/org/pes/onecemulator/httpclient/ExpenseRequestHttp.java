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

    private String url;

    private String token;

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

        this.url = host + uri;
        this.token = token;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void call() throws Exception {
        String params = new StringJoiner(",")
                .add(number)
                .add(sum.toString())
                .add(paid.toString())
                .add(currency)
                .add(code)
                .add(documentName)
                .add(confirm.toString())
                .toString();

        HttpGet httpGet = new HttpGet(url + "/" + params + "/" + date.format(formatter));
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
            throw e;
        }
    }
}
