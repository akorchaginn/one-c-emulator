package org.pes.onecemulator.facade.impl;

import org.pes.onecemulator.converter.internal.ExpenseRequestModelConverter;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.facade.ExpenseRequestFacade;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestFacadeImpl implements ExpenseRequestFacade {

    private static final ExpenseRequestModelConverter EXPENSE_REQUEST_MODEL_CONVERTER = new ExpenseRequestModelConverter();

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public ExpenseRequestFacadeImpl(final ExpenseRequestService expenseRequestService) {
        this.expenseRequestService = expenseRequestService;
    }

    @Override
    public List<ExpenseRequestModel> list() {
        return expenseRequestService.list().stream()
                .map(EXPENSE_REQUEST_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listNumbers() {
        return expenseRequestService.list().stream()
                .map(ExpenseRequest::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseRequestModel create(final ExpenseRequestModel model) throws NotFoundException, ValidationException {
        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequestService.create(model));
    }

    @Override
    public ExpenseRequestModel update(final ExpenseRequestModel model) throws NotFoundException, ValidationException {
        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequestService.update(model));
    }

    @Override
    public void delete(final UUID id) {
        expenseRequestService.delete(id);
    }
}
