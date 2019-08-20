package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.AccountingEntry;

public interface CrmRestClientService {

    void sendExpenseRequest(AccountingEntry accountingEntry);
}
