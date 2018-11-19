package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.AccountingEntryModelConverter;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryServiceImpl implements AccountingEntryService {

    private static final AccountingEntryModelConverter ACCOUNTING_ENTRY_MODEL_CONVERTER = new AccountingEntryModelConverter();

    private final ExpenseRequestRepository expenseRequestRepository;

    private final AccountingEntryRepository accountingEntryRepository;

    private final CrmInteractionService crmInteractionService;

    @Autowired
    public AccountingEntryServiceImpl(final ExpenseRequestRepository expenseRequestRepository,
                                      final AccountingEntryRepository accountingEntryRepository,
                                      final CrmInteractionService crmInteractionService) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.accountingEntryRepository = accountingEntryRepository;
        this.crmInteractionService = crmInteractionService;
    }

    @Override
    public AccountingEntryModel getById(final UUID id) throws NotFoundException {
        return accountingEntryRepository.findById(id)
                .map(ACCOUNTING_ENTRY_MODEL_CONVERTER::convert)
                .orElseThrow(() -> new NotFoundException(AccountingEntryModel.class, id));
    }

    @Override
    public List<AccountingEntryModel> list() {
        return accountingEntryRepository.findAll()
                .stream()
                .map(ACCOUNTING_ENTRY_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AccountingEntryModel create(final AccountingEntryModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            throw new ValidationException("Expense number is null.");
        }

        if (model.getDate() == null) {
            throw new ValidationException("Date is null.");
        }

        final ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, "number:" + model.getExpenseNumber()));

        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(model.getDate());
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(model.getSum());
        accountingEntry = accountingEntryRepository.save(accountingEntry);
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @Transactional
    @Override
    public AccountingEntryModel update(final AccountingEntryModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            throw new ValidationException("Expense number is null.");
        }

        if (model.getDate() == null) {
            throw new ValidationException("Date is null.");
        }

        final ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, "number: " + model.getExpenseNumber()));

        AccountingEntry accountingEntry = accountingEntryRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(AccountingEntry.class, model.getId()));
        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(model.getDate());
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(model.getSum());
        accountingEntry = accountingEntryRepository.save(accountingEntry);
        crmInteractionService.sendAccountingEntryToCrm(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
       accountingEntryRepository.deleteById(id);
    }
}
