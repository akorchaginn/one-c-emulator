package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Transactional
    @Override
    public AccountingEntryModel getById(UUID id) {
        AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
        if (accountingEntry != null) {
            return getModel(accountingEntry);
        }

        return new AccountingEntryModel("Accounting entry with id: " + id + " not found at database.");
    }

    @Transactional
    @Override
    public List<AccountingEntryModel> list() {
        List<AccountingEntry> accountingEntries = accountingEntryRepository.findAll();
        return accountingEntries
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AccountingEntryModel create(AccountingEntryModel model) {

        if (model == null) {
            return new AccountingEntryModel("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            return new AccountingEntryModel("Expense number is null.");
        }

        if (model.getDate() == null) {
            return new AccountingEntryModel("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());

        if (expenseRequest == null) {
            return new AccountingEntryModel(
                    "Expense request with number: " + model.getExpenseNumber() + " not found at database.");
        }

        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(toCalendar(model.getDate()));
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(new BigDecimal(model.getSum()));
        accountingEntry = accountingEntryRepository.save(accountingEntry);

        // Запрос в CRM
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return getModel(accountingEntry);
    }

    @Transactional
    @Override
    public AccountingEntryModel update(AccountingEntryModel model) {
        if (model == null) {
            return new AccountingEntryModel("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            return new AccountingEntryModel("Expense number is null.");
        }

        if (model.getDate() == null) {
            return new AccountingEntryModel("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());

        if (expenseRequest == null) {
            return new AccountingEntryModel(
                    "Expense request with number: " + model.getExpenseNumber() + " not found at database.");
        }

        AccountingEntry accountingEntry = accountingEntryRepository.findOne(model.getId());

        if (accountingEntry == null) {
            return new AccountingEntryModel("Accounting entry with id: " + model.getId() + "not found at database.");
        }

        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(toCalendar(model.getDate()));
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(new BigDecimal(model.getSum()));
        accountingEntry = accountingEntryRepository.save(accountingEntry);

        // Запрос в CRM
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return getModel(accountingEntry);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
       accountingEntryRepository.delete(id);
    }

    private AccountingEntryModel getModel(AccountingEntry entity) {
        AccountingEntryModel model = new AccountingEntryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDate(toLocalDate(entity.getDate()));
        model.setDocumentName(entity.getDocumentName());
        model.setExpenseNumber(entity.getExpenseRequest() != null
                ? entity.getExpenseRequest().getNumber()
                : null
        );
        model.setSum(entity.getSum().toString());

        return model;
    }

    private LocalDate toLocalDate(Calendar calendar) {
        return calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Calendar toCalendar(LocalDate localDate) {
        return GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    }
}
