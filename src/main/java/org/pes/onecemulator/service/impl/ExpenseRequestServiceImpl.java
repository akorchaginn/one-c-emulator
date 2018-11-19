package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.ExpenseRequestModelConverter;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestServiceImpl implements ExpenseRequestService {

    private static final ExpenseRequestModelConverter EXPENSE_REQUEST_MODEL_CONVERTER = new ExpenseRequestModelConverter();

    private final ExpenseRequestRepository expenseRequestRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public ExpenseRequestServiceImpl(final ExpenseRequestRepository expenseRequestRepository,
                                     final SourceRepository sourceRepository) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.sourceRepository = sourceRepository;
    }

    @Override
    public ExpenseRequestModel getById(final UUID id) throws NotFoundException {
        return expenseRequestRepository.findById(id)
                .map(EXPENSE_REQUEST_MODEL_CONVERTER::convert)
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, id));
    }

    @Override
    public List<ExpenseRequestModel> list() {
        return expenseRequestRepository.findAll()
                .stream()
                .map(EXPENSE_REQUEST_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ExpenseRequestModel create(final ExpenseRequestModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getNumber() == null) {
            throw new ValidationException("Number is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setSource(source);
        expenseRequest.setCurrency(model.getCurrency());
        expenseRequest.setConfirm(model.isConfirm());
        expenseRequest.setPaid(model.isPaid());
        expenseRequest.setNumber(model.getNumber());
        expenseRequest.setSum(model.getSum());
        expenseRequest = expenseRequestRepository.save(expenseRequest);

        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequest);
    }

    @Transactional
    @Override
    public ExpenseRequestModel update(final ExpenseRequestModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        if (model.getNumber() == null) {
            throw new ValidationException("Number is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        ExpenseRequest expenseRequest = expenseRequestRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, model.getId()));

        expenseRequest.setSource(source);
        expenseRequest.setCurrency(model.getCurrency());
        expenseRequest.setConfirm(model.isConfirm());
        expenseRequest.setPaid(model.isPaid());
        expenseRequest.setNumber(model.getNumber());
        expenseRequest.setSum(model.getSum());
        expenseRequest = expenseRequestRepository.save(expenseRequest);

        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequest);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        expenseRequestRepository.deleteById(id);
    }
}
