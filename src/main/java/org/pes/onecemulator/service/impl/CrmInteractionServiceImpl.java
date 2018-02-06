package org.pes.onecemulator.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
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
import org.pes.onecemulator.service.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        ValidationUtils.requireNonNull(accountingEntry);

        final String endpointUrl =
                environment.getRequiredProperty("crm.interaction.host")
                    .replaceAll("\"", StringUtils.EMPTY) +
                environment.getRequiredProperty("crm.interaction.uri")
                    .replaceAll("\"", StringUtils.EMPTY);

        final String token = environment.getRequiredProperty("crm.interaction.token")
                .replaceAll("\"", StringUtils.EMPTY);

        try {
            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Request request = asyncHttpClient
                    .prepareGet(createDataUrl(endpointUrl, accountingEntry))
                    .addHeader("crm-api-token", token)
                    .addHeader("crm-1c-database-source",
                            accountingEntry.getExpenseRequest().getSource().getName())
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
                                accountingEntryId.toString(),  response.getStatusCode()));
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

    private String createDataUrl(String endpointUrl, AccountingEntry accountingEntry) {
        // Порядок join'а важен!
        String parameterData = new StringJoiner(",")
                .add(accountingEntry.getExpenseRequest().getNumber())
                .add(accountingEntry.getSum().toString())
                .add(accountingEntry.getExpenseRequest().getPaid().toString())
                .add(accountingEntry.getExpenseRequest().getCurrency())
                .add(accountingEntry.getCode())
                .add(accountingEntry.getDocumentName())
                .add(accountingEntry.getExpenseRequest().getConfirm().toString())
                .toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String parameterDate = simpleDateFormat.format(accountingEntry.getDate().getTime());

        return new StringJoiner("/")
                .add(endpointUrl)
                .add(parameterData)
                .add(parameterDate)
                .toString();
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
