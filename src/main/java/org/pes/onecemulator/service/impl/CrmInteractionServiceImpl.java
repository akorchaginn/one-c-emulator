package org.pes.onecemulator.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import org.pes.onecemulator.bus.event.UINotificationEvent;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.httpclient.ExpenseRequestHttp;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private AsyncEventBus asyncEventBus;

    public List<DocumentCrm> getDocumentsCrmById(List<DocumentCrm> documentCrmList, String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm ->
                        invoiceRepository.findByIdAndSource(
                                documentCrm.getId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(this::getDocumentCrm)
                .collect(Collectors.toList());
    }

    public List<DocumentCrm> getDocumentsCrmByExternalId(List<DocumentCrm> documentCrmList, String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm ->
                        invoiceRepository.findByExternalIdAndSource(
                                documentCrm.getExternalId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(this::getDocumentCrm)
                .collect(Collectors.toList());
    }

    public List<PayerCrm> getAllPayersCrmBySource(String sourceName) {
        Source source = sourceRepository.findByName(sourceName).orElseGet(Source::new);
        return source.getPayers()
                .stream()
                .map(this::getPayerCrm)
                .collect(Collectors.toList());
    }

    @Async
    public void sendAccountingEntryToCrm(AccountingEntry accountingEntry) {
        try {
            final ExpenseRequestHttp expenseRequestHttp =
                    new ExpenseRequestHttp(accountingEntry, crmHost, crmUri, crmToken);
            expenseRequestHttp.call();
            postExpenseRequestInfoToUI();
        } catch (Exception e) {
            errorExpenseRequestInfoToUI(e);
        }
    }

    private void postExpenseRequestInfoToUI() {
        asyncEventBus.post(
                new UINotificationEvent(this, "Запрос в CRM отправлен."));
    }

    private void errorExpenseRequestInfoToUI(Exception e) {
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
