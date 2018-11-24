package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.DocumentCrmConverter;
import org.pes.onecemulator.converter.EmployeeCrmConverter;
import org.pes.onecemulator.converter.PayerCrmConverter;
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

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CrmInteractionServiceImpl implements CrmInteractionService {

    private static final DocumentCrmConverter DOCUMENT_CRM_CONVERTER = new DocumentCrmConverter();

    private static final PayerCrmConverter PAYER_CRM_CONVERTER = new PayerCrmConverter();

    private static final EmployeeCrmConverter EMPLOYEE_CRM_CONVERTER = new EmployeeCrmConverter();

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
                .map(Source::getPayers)
                .orElse(new HashSet<>())
                .stream()
                .map(PAYER_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeCrm> getAllEmployeesCrmBySourceAndExternalIds(final String sourceName,
                                                                      final List<String> externalIds) {
        return externalIds.stream()
                .map(employeeRepository::findByExternalId)
                .map(oe -> oe.orElse(null))
                .filter(Objects::nonNull)
                .filter(e -> e.getSources().stream().anyMatch(s -> s.getName().equals(sourceName)))
                .map(EMPLOYEE_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeCrm> getAllEmployeesCrmBySource(String source) {
        return sourceRepository.findByName(source)
                .map(Source::getEmployees)
                .orElse(new HashSet<>())
                .stream()
                .map(EMPLOYEE_CRM_CONVERTER::convert)
                .collect(Collectors.toList());
    }
}
