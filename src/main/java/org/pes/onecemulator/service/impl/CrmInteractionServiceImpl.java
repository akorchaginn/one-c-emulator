package org.pes.onecemulator.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.pes.onecemulator.utils.CrmSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    public DocumentCrm getDocumentsCrmById(UUID id, String source) {
        Invoice invoice = invoiceRepository.findOneAndSource(id, source);
        if (invoice != null) {
            return convertToDoc(invoice);
        }

        return new DocumentCrm();
    }

    public DocumentCrm getDocumentCrmByExternalId(String externalId, String source) {
        Invoice invoice = invoiceRepository.findByExternalIdAndSource(externalId, source);
        if (invoice != null) {
            return convertToDoc(invoice);
        }

        return new DocumentCrm();
    }

    public List<PayerCrm> getAllPayersCrm() {
        return convertToPayerCrm(payerRepository.findAll());
    }

    public void sendAccountingEntryToCrm(AccountingEntryDto accountingEntryDto) {
        ExpenseRequestDto expenseRequestDto = accountingEntryDto.getExpenseRequest();

        String endpointUrl = environment.getProperty("crm.interaction.url").replaceAll("\"", StringUtils.EMPTY)
                + environment.getProperty("crm.interaction.uri").replaceAll("\"", StringUtils.EMPTY);

        String parameterData = new StringJoiner(",")
                .add(expenseRequestDto.getNumber())
                .add(accountingEntryDto.getSum().toString())
                .add(expenseRequestDto.getPaid().toString())
                .add(expenseRequestDto.getCurrency())
                .add(accountingEntryDto.getCode())
                .add(accountingEntryDto.getDocumentName())
                .add(expenseRequestDto.getConfirm().toString())
                .toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String parameterDate = simpleDateFormat.format(accountingEntryDto.getDate().getTime());

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
                    .addHeader("crm-1c-database-source", expenseRequestDto.getSource())
                    .execute(new CompletionHandler(accountingEntryDto.getId()));

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

    private DocumentCrm convertToDoc(Invoice invoice) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(invoice, DocumentCrm.class);
    }

    private List<DocumentCrm> convertToDoc(List<Invoice> invoices) {
        return invoices.stream().map(this::convertToDoc).collect(Collectors.toList());
    }

    private Invoice convertToInvoice(DocumentCrm documentCrm) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(documentCrm, Invoice.class);
    }

    private PayerCrm convertToPayerCrm(Payer payer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(payer, PayerCrm.class);
    }

    private List<PayerCrm> convertToPayerCrm(List<Payer> payers) {
        return payers.stream().map(this::convertToPayerCrm).collect(Collectors.toList());
    }
}
