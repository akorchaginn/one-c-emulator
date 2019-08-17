package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.service.CrmRestClientService;
import org.pes.onecemulator.service.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CrmRestClientServiceImpl extends RestService implements CrmRestClientService {

    private static final String CRM_API_TOKEN = "crm-api-token";

    private static final String CRM_1C_DATABASE_SOURCE = "crm-1c-database-source";

    private static final HttpClient HTTP_CLIENT = createHttpClient();

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Value("${crm.interaction.token:#{null}}")
    private String crmToken;

    @Value("${crm.interaction.host:#{null}}")
    private String crmHost;

    @Value("${crm.interaction.expense-request.uri:#{null}}")
    private String expenseRequestUri;

    @Override
    public void sendExpenseRequest(final AccountingEntry accountingEntry) throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(prepareExpenseRequestURI(accountingEntry))
                .setHeader(CRM_API_TOKEN, crmToken)
                .setHeader(CRM_1C_DATABASE_SOURCE, accountingEntry.getExpenseRequest().getSource().getName())
                .build();

        logger.info("Request: " + request + " headers = " + request.headers());

        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response:" +
                " status code = " + response.statusCode() +
                " body = " + response.body() +
                " headers = " + response.headers());

        if (response.statusCode() != 200) {
            throw new Exception("Status code not equal 200.");
        }
    }

    private URI prepareExpenseRequestURI(final AccountingEntry accountingEntry) {
        final ExpenseRequest expenseRequest = accountingEntry.getExpenseRequest();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(crmHost)
                     .append(expenseRequestUri)
                     .append(SLASH);

        final String mainPart = String.join(",",
                expenseRequest.getNumber(),
                accountingEntry.getSum(),
                toCrmString(expenseRequest.getPaid()),
                expenseRequest.getCurrency(),
                accountingEntry.getCode(),
                accountingEntry.getDocumentName(),
                toCrmString(expenseRequest.getConfirm()));

        final LocalDate date = accountingEntry.getDate();
        final String dateString = date != null ? DATE_TIME_FORMATTER.format(date) : "";

        stringBuilder.append(mainPart)
                     .append(SLASH)
                     .append(dateString);

        return URI.create(stringBuilder.toString());
    }

    private static String toCrmString(final Boolean value) {
        return Boolean.TRUE.equals(value) ? "1" : "0";
    }
}
