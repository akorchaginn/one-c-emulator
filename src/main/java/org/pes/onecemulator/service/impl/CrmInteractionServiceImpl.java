package org.pes.onecemulator.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmInteractionServiceImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    private PayerRepository payerRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SourceRepository sourceRepository;

    public DocumentCrm getDocumentsCrmById(UUID id, String sourceName) {
        Invoice invoice = invoiceRepository.findByIdAndSource(id, sourceName);
        if (invoice != null) {
            return getDocumentCrm(invoice);
        }

        return new DocumentCrm();
    }

    public DocumentCrm getDocumentCrmByExternalId(String externalId, String sourceName) {
        Invoice invoice = invoiceRepository.findByExternalIdAndSource(externalId, sourceName);
        if (invoice != null) {
            return getDocumentCrm(invoice);
        }

        return new DocumentCrm();
    }

    public List<PayerCrm> getAllPayersCrmBySource(String sourceName) {
        Source source = sourceRepository.findByName(sourceName);
        if (source != null) {
            return source.getPayers()
                    .stream()
                    .map(this::getPayerCrm)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public void sendAccountingEntryToCrm(AccountingEntry accountingEntry) {

        requireNonNullAccountingEntry(accountingEntry);

        final String host = environment.getRequiredProperty("crm.interaction.host")
                .replaceAll("\"", StringUtils.EMPTY);

        final String uri = environment.getRequiredProperty("crm.interaction.uri")
                .replaceAll("\"", StringUtils.EMPTY);

        final String token = environment.getRequiredProperty("crm.interaction.token")
                .replaceAll("\"", StringUtils.EMPTY);

        ExpenseRequest expenseRequest = accountingEntry.getExpenseRequest();

        String parameterData = new StringJoiner(",")
                .add(expenseRequest.getNumber())
                .add(expenseRequest.getConfirm().toString())
                .add(expenseRequest.getPaid().toString())
                .add(expenseRequest.getCurrency())
                .add(accountingEntry.getSum().toString())
                .add(accountingEntry.getCode())
                .add(accountingEntry.getDocumentName())
                .toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String parameterDate = simpleDateFormat.format(accountingEntry.getDate().getTime());

        String resultUrl = new StringJoiner("/")
                .add(host + uri)
                .add(parameterData)
                .add(parameterDate)
                .toString();

        try {
            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Request request = asyncHttpClient
                    .prepareGet(resultUrl)
                    .addHeader("crm-api-token", token)
                    .addHeader("crm-1c-database-source", expenseRequest.getSource().getName())
                    .build();

            LOGGER.info("Start request to CRM: " + request.getUrl() + " : " + request.getHeaders().toString());

            asyncHttpClient.executeRequest(request, new CompletionHandler(accountingEntry.getId()));

        } catch (Exception e) {
            LOGGER.warn("Error request to CRM: " + e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
    }

    private static class CompletionHandler extends AsyncCompletionHandler<Void> {

        private final UUID accountingEntryId;

        CompletionHandler(UUID aEnId) { this.accountingEntryId = aEnId; }

        @Override
        public Void onCompleted(Response response) {
            try {
                LOGGER.info(
                        String.format("End request to CRM for new AccountingEntry %s: %s",
                                accountingEntryId.toString(),  response.getResponseBody()));
            } catch (Exception e) {
                LOGGER.warn("Error onCompleted(): "+ e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
            return null;
        }

        @Override
        public void onThrowable(Throwable t) {
            LOGGER.error("Error request to CRM: " + t.getMessage() +"\n" + Arrays.toString(t.getStackTrace()));
        }
    }

    private void requireNonNullAccountingEntry(AccountingEntry accountingEntry) {
        Objects.requireNonNull(accountingEntry,
                "AccountingEntry is null.");
        ExpenseRequest expenseRequest = accountingEntry.getExpenseRequest();
        Objects.requireNonNull(expenseRequest,
                "ExpenseRequest is null.");
        Objects.requireNonNull(expenseRequest.getNumber(),
                "expenseRequest.getNumber() is null.");
        Objects.requireNonNull(expenseRequest.getConfirm(),
                "expenseRequest.getConfirm() is null.");
        Objects.requireNonNull(expenseRequest.getPaid(),
                "expenseRequest.getPaid() is null.");
        Objects.requireNonNull(expenseRequest.getCurrency(),
                "expenseRequest.getCurrency() is null.");
        Objects.requireNonNull(expenseRequest.getSource(),
                "expenseRequest.getSource() is null.");
        Objects.requireNonNull(expenseRequest.getSource().getName(),
                "expenseRequest.getSource().getName() is null.");
        Objects.requireNonNull(accountingEntry.getId(),
                "accountingEntry.getId() is null.");
        Objects.requireNonNull(accountingEntry.getSum(),
                "accountingEntry.getSum() is null.");
        Objects.requireNonNull(accountingEntry.getCode(),
                "accountingEntry.getCode() is null.");
        Objects.requireNonNull(accountingEntry.getDocumentName(),
                "accountingEntry.getDocumentName() is null.");
        Objects.requireNonNull(accountingEntry.getDate(),
                "accountingEntry.getDate() is null.");
    }

    private DocumentCrm getDocumentCrm(Invoice entity) {
        DocumentCrm model = new DocumentCrm();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerName(entity.getPayer() != null ? entity.getPayer().getName() : null);
        model.setSum(entity.getSum());
        model.setDate(entity.getDate());
        model.setStatus(entity.getStatus());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSum(entity.getPaymentSum());
        model.setExternalId(entity.getExternalId());

        return model;
    }

    private PayerCrm getPayerCrm(Payer entity) {
        PayerCrm model = new PayerCrm();
        model.setId(entity.getAddress());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());

        return model;
    }
}
