package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.DocumentCrmConverter;
import org.pes.onecemulator.converter.EmployeeCrmConverter;
import org.pes.onecemulator.converter.PayerCrmConverter;
import org.pes.onecemulator.entity.EmployeeSource;
import org.pes.onecemulator.entity.PayerSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.repository.EmployeeRepository;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    private static final DocumentCrmConverter DOCUMENT_CRM_CONVERTER = new DocumentCrmConverter();

    private static final PayerCrmConverter PAYER_CRM_CONVERTER = new PayerCrmConverter();

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
    public List<DocumentCrm> getDocumentsCrmById(final List<DocumentCrm> documentCrmList, final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm -> invoiceRepository.findByIdAndSource(documentCrm.getId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(DOCUMENT_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentCrm> getDocumentsCrmByExternalId(final List<DocumentCrm> documentCrmList,
                                                         final String sourceName) {
        return documentCrmList.stream()
                .filter(Objects::nonNull)
                .map(documentCrm ->
                        invoiceRepository.findByExternalIdAndSource(
                                documentCrm.getExternalId(), sourceName).orElse(null))
                .filter(Objects::nonNull)
                .map(DOCUMENT_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayerCrm> getAllPayersCrmBySource(final String sourceName) {
        return sourceRepository.findByName(sourceName)
                .map(Source::getPayerSources)
                .orElse(new ArrayList<>())
                .stream()
                .map(PayerSource::getPayer)
                .map(PAYER_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeCrm> getAllEmployeesCrmBySourceAndExternalIds(final String source,
                                                                      final List<String> externalIds) {
        return externalIds.stream()
                .map(employeeRepository::findByExternalId)
                .map(oe -> oe.orElse(null))
                .filter(Objects::nonNull)
                .flatMap(e -> e.getEmployeeSources().stream())
                .filter(es -> es.getSource().getName().equals(source))
                .map(EmployeeSource::getEmployee)
                .map(e -> new EmployeeCrmConverter(source).convert(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeCrm> getAllEmployeesCrmBySource(String source) {
        return sourceRepository.findByName(source)
                .map(Source::getEmployeeSources)
                .stream()
                .flatMap(Collection::stream)
                .map(EmployeeSource::getEmployee)
                .map(e -> new EmployeeCrmConverter(source).convert(e))
                .collect(Collectors.toList());
    }
}
