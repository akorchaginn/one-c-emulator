package org.pes.onecemulator.service;

import org.pes.onecemulator.model.onec.DocumentModel;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;

import java.util.List;

public interface CrmInteractionService {

    List<DocumentModel> getDocumentsCrmById(List<DocumentModel> documentCrmList, String source);

    List<DocumentModel> getDocumentsCrmByExternalId(List<DocumentModel> documentCrmList, String source);

    List<PayerModel> getAllPayersCrmBySource(String source);

    List<EmployeeModel> getAllEmployeesCrmBySourceAndExternalIds(String source, List<String> externalIds);

    List<EmployeeModel> getAllEmployeesCrmBySource(String source);
}
