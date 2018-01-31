package org.pes.onecemulator.service;

import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.PayerCrm;

import java.util.List;
import java.util.UUID;

public interface CrmInteractionService {

    DocumentCrm getDocumentsCrmById(UUID id, String source);

    DocumentCrm getDocumentCrmByExternalId(String externalId, String source);

    List<PayerCrm> getAllPayersCrm();

    void sendAccountingEntryToCrm(AccountingEntryDto dto);
}
