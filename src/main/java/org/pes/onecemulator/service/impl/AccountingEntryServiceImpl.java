package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.model.AEntryModel;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryServiceImpl implements AccountingEntryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingEntryServiceImpl.class);

    @Autowired
    private ExpenseRequestRepository expenseRequestRepository;

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;

    @Autowired
    private CrmInteractionService crmInteractionService;

    public AEntryModel getById(UUID id) {
        AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
        if (accountingEntry != null) {
            return getModel(accountingEntry);
        }

        return new AEntryModel("Accounting entry with id: " + id + " not found at database.");
    }

    public List<AEntryModel> list() {
        List<AccountingEntry> accountingEntries = accountingEntryRepository.findAll();
        return accountingEntries
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    public AEntryModel create(AEntryModel model) {

        if (model == null) {
            return new AEntryModel("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            return new AEntryModel("Expense number is null.");
        }

        if (model.getDate() == null) {
            return new AEntryModel("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());

        if (expenseRequest == null) {
            return new AEntryModel(
                    "Expense request with number: " + model.getExpenseNumber() + " not found at database.");
        }

        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(model.getDate());
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(model.getSum());
        accountingEntry = accountingEntryRepository.save(accountingEntry);

        // Запрос в CRM
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return getModel(accountingEntry);
    }

    public AEntryModel update(AEntryModel model) {
        if (model == null) {
            return new AEntryModel("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            return new AEntryModel("Expense number is null.");
        }

        if (model.getDate() == null) {
            return new AEntryModel("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());

        if (expenseRequest == null) {
            return new AEntryModel(
                    "Expense request with number: " + model.getExpenseNumber() + " not found at database.");
        }

        AccountingEntry accountingEntry = accountingEntryRepository.findOne(model.getId());

        if (accountingEntry == null) {
            return new AEntryModel("Accounting entry with id: " + model.getId() + "not found at database.");
        }

        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(model.getDate());
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(model.getSum());
        accountingEntry = accountingEntryRepository.save(accountingEntry);

        // Запрос в CRM
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return getModel(accountingEntry);
    }

    public void delete(UUID id) {
       accountingEntryRepository.delete(id);
    }

    private AEntryModel getModel(AccountingEntry entity) {
        AEntryModel model = new AEntryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDate(entity.getDate());
        model.setDocumentName(entity.getDocumentName());
        model.setExpenseNumber(entity.getExpenseRequest() != null
                ? entity.getExpenseRequest().getNumber()
                : null
        );
        model.setSum(entity.getSum());

        return model;
    }
}
