package org.pes.onecemulator.service.api;

import org.apache.commons.lang3.time.DateUtils;
import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrmInteractionService {

    private static final Logger log = LoggerFactory.getLogger(CrmInteractionService.class);

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
