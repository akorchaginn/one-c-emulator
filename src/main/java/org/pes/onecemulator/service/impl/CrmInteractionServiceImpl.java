package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.onec.DocumentModelConverter;
import org.pes.onecemulator.converter.onec.EmployeeModelConverter;
import org.pes.onecemulator.converter.onec.PayerModelConverter;
import org.pes.onecemulator.entity.PayerSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.onec.DocumentModel;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;
import org.pes.onecemulator.repository.EmployeeRepository;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    private static final DocumentModelConverter DOCUMENT_CRM_CONVERTER = new DocumentModelConverter();

    private static final PayerModelConverter PAYER_CRM_CONVERTER = new PayerModelConverter();

    private static final EmployeeModelConverter EMPLOYEE_MODEL_CONVERTER = new EmployeeModelConverter();

    private final InvoiceRepository invoiceRepository;

    private final SourceRepository sourceRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public CrmInteractionServiceImpl(final InvoiceRepository invoiceRepository,
                                     final SourceRepository sourceRepository,
                                     final EmployeeRepository employeeRepository) {
        this.invoiceRepository = invoiceRepository;
        this.sourceRepository = sourceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<DocumentModel> getDocumentsCrmById(final List<DocumentModel> documentCrmList, final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm -> invoiceRepository.findByIdAndSource(documentCrm.getId(), sourceName))
                .flatMap(Optional::stream)
                .map(DOCUMENT_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentModel> getDocumentsCrmByExternalId(final List<DocumentModel> documentCrmList,
                                                           final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm -> invoiceRepository.findByExternalIdAndSource(documentCrm.getExternalId(), sourceName))
                .flatMap(Optional::stream)
                .map(DOCUMENT_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayerModel> getAllPayersCrmBySource(final String sourceName) {
        return sourceRepository.findByName(sourceName)
                .map(Source::getPayerSources)
                .orElseGet(ArrayList::new)
                .stream()
                .map(PayerSource::getPayer)
                .map(PAYER_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeModel> getAllEmployeesCrmBySourceAndExternalIds(final String source,
                                                                        final List<String> externalIds) {
        return externalIds.stream()
                .map(employeeRepository::findByExternalId)
                .flatMap(Optional::stream)
                .flatMap(e -> e.getEmployeeSources().stream())
                .filter(es -> es.getSource().getName().equals(source))
                .map(EMPLOYEE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeModel> getAllEmployeesCrmBySource(final String source) {
        return sourceRepository.findByName(source)
                .map(Source::getEmployeeSources)
                .orElseGet(ArrayList::new)
                .stream()
                .map(EMPLOYEE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }
}
