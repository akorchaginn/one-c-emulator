package org.pes.onecemulator.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.httpclient.CrmClient;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.pes.onecemulator.bus.event.UINotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmInteractionServiceImpl.class);

    @Value("${crm.interaction.host:#{null}}")
    private String crmHost;

    @Value("${crm.interaction.uri:#{null}}")
    private String crmUri;

    @Value("${crm.interaction.token:#{null}}")
    private String crmToken;

    private final InvoiceRepository invoiceRepository;

    private final SourceRepository sourceRepository;

    private final AsyncEventBus asyncEventBus;

    @Autowired
    CrmInteractionServiceImpl(InvoiceRepository invoiceRepository, SourceRepository sourceRepository, AsyncEventBus asyncEventBus) {
        this.invoiceRepository = invoiceRepository;
        this.sourceRepository = sourceRepository;
        this.asyncEventBus = asyncEventBus;
    }

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

    @Async
    public void sendAccountingEntryToCrm(AccountingEntry accountingEntry) {
        try {
            final String host = Objects.requireNonNull(crmHost);
            final String uri =  Objects.requireNonNull(crmUri);
            final String token = Objects.requireNonNull(crmToken);
            final String dataUrl = createDataUrl(host + uri, accountingEntry);
            final String sourceName = accountingEntry.getExpenseRequest().getSource().getName();
            try {
                preExpenseRequestInfo(dataUrl, token, sourceName);
                CrmClient.expenseRequest(dataUrl, token, sourceName);
                postExpenseRequestInfo();
            } catch (Exception e) {
                errorExpenseRequestInfo(e);
            }
        } catch (Exception e) {
            errorExpenseRequestInfo(e);
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

    private void preExpenseRequestInfo(String dataUrl, String token, String sourceName) {
        LOGGER.info("Start request to CRM: " + dataUrl + " : " + token + " : " + sourceName);
        asyncEventBus.post(
                new UINotificationEvent(this, "Запрос: " + dataUrl + " отправлен в CRM."));
    }

    private void postExpenseRequestInfo() {
        LOGGER.info("End request to CRM.");
        asyncEventBus.post(
                new UINotificationEvent(this, "Запрос в CRM отправлен."));
    }

    private void errorExpenseRequestInfo(Exception e) {
        LOGGER.error("Error request to CRM: ", e);
        asyncEventBus.post(
                new UINotificationEvent(this, "Ошибка отправки запроса в CRM: " + e.getMessage()));
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
