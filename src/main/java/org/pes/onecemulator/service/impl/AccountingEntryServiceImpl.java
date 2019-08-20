package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.AccountingEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AccountingEntryServiceImpl implements AccountingEntryService {

    private final ExpenseRequestRepository expenseRequestRepository;

    private final AccountingEntryRepository accountingEntryRepository;

    @Autowired
    public AccountingEntryServiceImpl(final ExpenseRequestRepository expenseRequestRepository,
                                      final AccountingEntryRepository accountingEntryRepository) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.accountingEntryRepository = accountingEntryRepository;
    }

    @Override
    public AccountingEntry getById(final UUID id) throws NotFoundException {
        return accountingEntryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(AccountingEntryModel.class, id));
    }

    @Override
    public List<AccountingEntry> list() {
        return accountingEntryRepository.findAll();
    }

    @Transactional
    @Override
    public AccountingEntry create(final AccountingEntryModel model) throws NotFoundException, ValidationException {
        validateModel(model);

        return updateOrCreate(model, new AccountingEntry());
    }

    @Transactional
    @Override
    public AccountingEntry update(final AccountingEntryModel model) throws NotFoundException, ValidationException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final AccountingEntry accountingEntry = accountingEntryRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(AccountingEntry.class, model.getId()));

        return updateOrCreate(model, accountingEntry);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
       accountingEntryRepository.deleteById(id);
    }

    private AccountingEntry updateOrCreate(final AccountingEntryModel model, final AccountingEntry accountingEntry) throws NotFoundException {
        final ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(model.getExpenseNumber())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, "number:" + model.getExpenseNumber()));

        accountingEntry.setCode(model.getCode());
        accountingEntry.setDate(model.getDate());
        accountingEntry.setDocumentName(model.getDocumentName());
        accountingEntry.setExpenseRequest(expenseRequest);
        accountingEntry.setSum(model.getSum());

        return accountingEntryRepository.save(accountingEntry);
    }

    private void validateModel(final AccountingEntryModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getExpenseNumber() == null) {
            throw new ValidationException("Expense number is null.");
        }

        if (model.getDate() == null) {
            throw new ValidationException("Date is null.");
        }
    }
}
