package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;

import java.util.List;

public interface CrmInteractionService {

    List<DocumentCrm> getDocumentsCrmById(List<DocumentCrm> documentCrmList, String source);

    List<DocumentCrm> getDocumentsCrmByExternalId(List<DocumentCrm> documentCrmList, String source);

    List<PayerCrm> getAllPayersCrmBySource(String source);

    List<EmployeeCrm> getAllEmployeesCrmBySourceAndExternalIds(String source, List<String> externalIds);

    List<EmployeeCrm> getAllEmployeesCrmBySource(String source);

    void sendAccountingEntryToCrm(final AccountingEntry accountingEntry) throws Exception;
}
