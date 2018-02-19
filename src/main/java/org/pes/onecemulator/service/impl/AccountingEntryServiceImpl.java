package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
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

    private final ExpenseRequestRepository expenseRequestRepository;

    private final AccountingEntryRepository accountingEntryRepository;

    private final CrmInteractionService crmInteractionService;

    @Autowired
    AccountingEntryServiceImpl(AccountingEntryRepository accountingEntryRepository, ExpenseRequestRepository expenseRequestRepository, CrmInteractionService crmInteractionService) {
        this.accountingEntryRepository = accountingEntryRepository;
        this.expenseRequestRepository = expenseRequestRepository;
        this.crmInteractionService = crmInteractionService;
    }

    @Transactional
    @Override
    public AccountingEntryModel getById(UUID id) throws NotFoundException {
        AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
        if (accountingEntry != null) {
            return getModel(accountingEntry);
        }

        throw new NotFoundException(AccountingEntryModel.class, id);
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
    public AccountingEntryModel create(AccountingEntryModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            throw new ValidationException("Expense number is null.");
        }

        if (model.getDate() == null) {
            throw new ValidationException("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());
        if (expenseRequest == null) {
            throw new NotFoundException(ExpenseRequest.class, "number:" + model.getExpenseNumber());
        }
        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(toCalendar(model.getDate()));
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(new BigDecimal(model.getSum()));
        accountingEntry = accountingEntryRepository.saveAndFlush(accountingEntry);
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return getModel(accountingEntry);
    }

    @Transactional
    @Override
    public AccountingEntryModel update(AccountingEntryModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            throw new ValidationException("Expense number is null.");
        }

        if (model.getDate() == null) {
            throw new ValidationException("Date is null.");
        }

        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber());
        if (expenseRequest == null) {
            throw new NotFoundException(ExpenseRequest.class, "number: " + model.getExpenseNumber());
        }

        AccountingEntry accountingEntry = accountingEntryRepository.findOne(model.getId());
        if (accountingEntry == null) {
            throw new NotFoundException(AccountingEntry.class, model.getId());
        }
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(toCalendar(model.getDate()));
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(new BigDecimal(model.getSum()));
        accountingEntry = accountingEntryRepository.saveAndFlush(accountingEntry);
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
