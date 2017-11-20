package org.pes.onecemulator.service.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.dto.PayerCrm;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.utils.CrmSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CrmInteractionService {

    private static final Logger log = LoggerFactory.getLogger(CrmInteractionService.class);

    @Autowired
    private Environment environment;

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private PayerService payerService;

    @Autowired
    private InvoiceService invoiceService;

    public List<DocumentCrm> getDocumentsCrmById(UUID id) {
        log.info("DocumentCrm getDocumentCrmById method start...");
        List<DocumentCrm> documentCrmList = new ArrayList<>();
        try {
            documentCrmList.add(convertToDoc(invoiceService.getInvoiceById(id)));
            log.info("DocumentCrm count: " + documentCrmList.size());
            return documentCrmList;
        } catch (NotFoundEntityException e) {
            e.printStackTrace();
        }

        return documentCrmList;
    }

    public DocumentCrm getDocumentCrmByExternalId(UUID externalId) {
        log.info("DocumentCrm getDocumentCrmByExternalId method start...");
        try {
            return convertToDoc(invoiceService.getInvoiceByExternalId(externalId));
        } catch (NotFoundEntityException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DocumentCrm> getDocumentsCrmByParameters(String name, BigDecimal sum, Calendar date) {
        log.info("DocumentCrm getDocumentCrmByParameters method start...");
        List<DocumentCrm> documentCrmList = new ArrayList<>();
        date.set(Calendar.HOUR_OF_DAY, 0);
        PayerDto payer = payerService.getPayerByCode(name);
        Set<InvoiceDto> invoiceDtos = payer.getInvoices();
        if (invoiceDtos != null && invoiceDtos.size() > 0) {
            log.info("DocumentCrm find by parameters: name = " + name + ", sum = " + sum + ", date = " + date.getTime());
            for (InvoiceDto i : invoiceDtos) {
                if (sum.compareTo(i.getSum()) == 0
                        && DateUtils.isSameDay(date, i.getDate())) {
                    documentCrmList.add(convertToDoc(i));
                    log.info("Invoice with id: " + i.getId() + " equal parameters");
                }
            }
            log.info("DocumentCrm count: " + documentCrmList.size());
            return documentCrmList;
        }
        log.warn("Invoice count: 0");
        return documentCrmList;
    }

    public List<PayerCrm> getAllPayersCrm() {
        List<PayerCrm> payerCrms = convertToPayerCrm(payerService.listPayer());
        if (payerCrms != null)
            return payerCrms;

        return new ArrayList<>();
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
            log.info("Start request to CRM: " + resultUrl);

            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            asyncHttpClient
                    .prepareGet(resultUrl)
                    .addHeader("crm-api-token", CrmSecurityUtils.CRM_TOKEN)
                    .addHeader("crm-1c-database-source", CrmSecurityUtils.ONEC_DATABASE_SOURCE)
                    .execute(new CompletionHandler(accountingEntryDto.getId()));

        } catch (Exception e) {
            log.warn("Error request to CRM: " + e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
    }

    private static class CompletionHandler extends AsyncCompletionHandler<Void> {
        private final UUID aEnId;
        public CompletionHandler(UUID aEnId) { this.aEnId = aEnId; }

        @Override
        public Void onCompleted(Response response) {
            try {
                log.warn(String.format("End request to CRM for new AccountingEntry %s: %s", aEnId.toString(),  response.getResponseBody()));
            } catch (Exception e) {
                log.warn("Error onCompleted(): "+ e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
            return null;
        }

        @Override
        public void onThrowable(Throwable t) {
            log.warn("End CRM request with error: " + t.getMessage() +"\n" + Arrays.toString(t.getStackTrace()));
        }
    }

    private DocumentCrm convertToDoc(InvoiceDto invoice) {
        return mapperFactoryService.getMapper().map(invoice, DocumentCrm.class);
    }

    private List<DocumentCrm> convertToDoc(List<InvoiceDto> invoices) {
        return invoices.stream().map(this::convertToDoc).collect(Collectors.toList());
    }

    private InvoiceDto convertToInvoice(DocumentCrm documentCrm) {
        return mapperFactoryService.getMapper().map(documentCrm, InvoiceDto.class);
    }

    private PayerCrm convertToPayerCrm(PayerDto payerDto) {
        return mapperFactoryService.getMapper().map(payerDto, PayerCrm.class);
    }

    private List<PayerCrm> convertToPayerCrm(List<PayerDto> payerDtos) {
        return payerDtos.stream().map(this::convertToPayerCrm).collect(Collectors.toList());
    }
}
