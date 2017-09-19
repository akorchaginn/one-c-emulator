package org.pes.onecemulator.service.api;

import org.apache.commons.lang3.time.DateUtils;
import org.asynchttpclient.*;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.asynchttpclient.Dsl.asyncHttpClient;

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

    public List<DocumentCrm> getDocumentsCrmByParameters(String name, BigDecimal sum, Calendar date) {
        log.info("DocumentCrm getDocumentCrmByParameters method start...");
        List<DocumentCrm> documentCrmList = new ArrayList<>();
        date.set(Calendar.HOUR_OF_DAY, 0);
        try {
            Set<InvoiceDto> invoiceDtos = payerService.getPayerByCode(name).getInvoices();
            if (invoiceDtos != null) {
                log.info("DocumentCrm find by parameters: name = " + name + ", sum = " + sum + ", date= " + date.getTime());
                for (InvoiceDto i : invoiceDtos) {
                    if (sum.equals(i.getSum())
                            && DateUtils.isSameDay(date, i.getDate())) {
                        documentCrmList.add(convertToDoc(i));
                        log.info("Invoice with id: " + i.getId() + " equal parameters");
                    }
                }
                log.info("DocumentCrm count: " + documentCrmList.size());

                return documentCrmList;
            }
            return documentCrmList;
        } catch (NotFoundEntityException e) {
            e.printStackTrace();
        }
        return documentCrmList;
    }

    public void sendAccountingEntryToCrm(AccountingEntryDto accountingEntryDto) throws IOException {
        ExpenseRequestDto expenseRequestDto = accountingEntryDto.getExpenseRequest();
        String endpointUrl = environment.getProperty("crm.interaction.url") + environment.getProperty("crm.interaction.uri");
        String parameterData = new StringJoiner(",")
                .add(expenseRequestDto.getNumber())
                .add(expenseRequestDto.getSum().toString())
                .add(expenseRequestDto.getPaid().toString())
                .add(expenseRequestDto.getCurrency())
                .add(accountingEntryDto.getCode())
                .add(accountingEntryDto.getDocumentName())
                .add(expenseRequestDto.getConfirm().toString())
                .toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String parameterDate = simpleDateFormat.format(accountingEntryDto.getDate());
        String resultUrl = new StringJoiner("/")
                .add(endpointUrl)
                .add(parameterData)
                .add(parameterDate)
                .toString();

        try (AsyncHttpClient asyncHttpClient = asyncHttpClient()) {
            asyncHttpClient
                    .prepareGet(resultUrl)
                    .execute()
                    .toCompletableFuture()
                    .thenApply(Response::getResponseBody)
                    .thenAccept(System.out::println)
                    .join();
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
}
