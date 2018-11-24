package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.service.CrmRestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;

@Service
public class CrmRestClientServiceImpl implements CrmRestClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmRestClientServiceImpl.class);

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();

    private static final String CRM_API_TOKEN = "crm-api-token";

    private static final String CRM_1C_DATABASE_SOURCE = "crm-1c-database-source";

    @Value("${crm.interaction.host:#{null}}")
    private String crmHost;

    @Value("${crm.interaction.uri:#{null}}")
    private String crmUri;

    @Value("${crm.interaction.token:#{null}}")
    private String crmToken;

    @Async
    @Override
    public void sendExpenseRequest(final AccountingEntry accountingEntry) throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(prepareExpenseRequestURI(accountingEntry))
                .setHeader(CRM_API_TOKEN, crmToken)
                .setHeader(CRM_1C_DATABASE_SOURCE, accountingEntry.getExpenseRequest().getSource().getName())
                .build();

        LOGGER.info("Request: " + request + " headers = " + request.headers());

        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        LOGGER.info("Response:" +
                " status code = " + response.statusCode() +
                " body = " + response.body() +
                " headers = " + response.headers());

        if (response.statusCode() != 200) {
            throw new Exception("Status code not equal 200.");
        }
    }

    private URI prepareExpenseRequestURI(final AccountingEntry accountingEntry) {
        final String uriString =  getBaseCrmUri() + String.join(",",
                accountingEntry.getExpenseRequest().getNumber(),
                accountingEntry.getSum(),
                toCrmString(Boolean.TRUE.equals(accountingEntry.getExpenseRequest().getPaid())),
                accountingEntry.getExpenseRequest().getCurrency(),
                accountingEntry.getCode(),
                accountingEntry.getDocumentName(),
                toCrmString(Boolean.TRUE.equals(accountingEntry.getExpenseRequest().getConfirm())))
                + "/" + accountingEntry.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return URI.create(uriString);
    }

    private String getBaseCrmUri() {
        return crmHost + crmUri + "/";
    }

    private String toCrmString(boolean value) {
        return value ? "1" : "0";
    }
}