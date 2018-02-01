package org.pes.onecemulator.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
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
import org.pes.onecemulator.utils.CrmSecurityUtils;
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

    public DocumentCrm getDocumentsCrmById(UUID id, String source) {
        Invoice invoice = invoiceRepository.findOneAndSource(id, source);
        if (invoice != null) {
            return getDocumentCrm(invoice);
        }

        return new DocumentCrm();
    }

    public DocumentCrm getDocumentCrmByExternalId(String externalId, String source) {
        Invoice invoice = invoiceRepository.findByExternalIdAndSource(externalId, source);
        if (invoice != null) {
            return getDocumentCrm(invoice);
        }

        return new DocumentCrm();
    }

    public List<PayerCrm> getAllPayersCrm() {
        List<Payer> payers = payerRepository.findAll();
        return payers
                .stream()
                .map(this::getPayerCrm)
                .collect(Collectors.toList());
    }

    public List<PayerCrm> getAllPayersCrmBySource(String src) {
        Source source = sourceRepository.findByName(src);
        if (source != null) {
            return source.getPayers()
                    .stream()
                    .map(this::getPayerCrm)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public void sendAccountingEntryToCrm(AccountingEntry accountingEntry) {
        String endpointUrl =
                environment.getProperty("crm.interaction.url").replaceAll("\"", StringUtils.EMPTY) +
                        environment.getProperty("crm.interaction.uri").replaceAll("\"", StringUtils.EMPTY);

        ExpenseRequest expenseRequest = accountingEntry.getExpenseRequest();

        String parameterData = new StringJoiner(",")
                .add(expenseRequest.getNumber())
                .add(expenseRequest.getSum().toString())
                .add(expenseRequest.getPaid().toString())
                .add(expenseRequest.getCurrency())
                .add(accountingEntry.getCode())
                .add(accountingEntry.getDocumentName())
                .add(expenseRequest.getConfirm().toString())
                .toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String parameterDate = simpleDateFormat.format(accountingEntry.getDate().getTime());

        String resultUrl = new StringJoiner("/")
                .add(endpointUrl)
                .add(parameterData)
                .add(parameterDate)
                .toString();

        try {
            LOGGER.info("Start request to CRM: " + resultUrl);

            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            asyncHttpClient
                    .prepareGet(resultUrl)
                    .addHeader("crm-api-token", CrmSecurityUtils.CRM_TOKEN)
                    .addHeader("crm-1c-database-source", expenseRequest.getSource().getName())
                    .execute(new CompletionHandler(accountingEntry.getId()));

        } catch (Exception e) {
            LOGGER.warn("Error request to CRM: " + e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
    }

    private static class CompletionHandler extends AsyncCompletionHandler<Void> {
        private final UUID aEnId;
        CompletionHandler(UUID aEnId) { this.aEnId = aEnId; }

        @Override
        public Void onCompleted(Response response) {
            try {
                LOGGER.info(String.format("End request to CRM for new AccountingEntry %s: %s", aEnId.toString(),  response.getResponseBody()));
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

    private DocumentCrm getDocumentCrm(Invoice entity) {
        final DocumentCrm model = new DocumentCrm();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setExternalId(entity.getExternalId());
        model.setDate(entity.getDate());
        model.setSum(entity.getSum());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSum(entity.getPaymentSum());
        model.setStatus(entity.getStatus());
        model.setPayerName(entity.getPayer() != null ? entity.getPayer().getName() : null);

        return model;
    }

    private PayerCrm getPayerCrm(Payer entity) {
        final PayerCrm model = new PayerCrm();
        model.setId(entity.getAddress());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setAddress(entity.getAddress());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());

        return model;
    }
}
