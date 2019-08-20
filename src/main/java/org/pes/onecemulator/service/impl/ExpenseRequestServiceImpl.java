package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseRequestServiceImpl implements ExpenseRequestService {

    private final ExpenseRequestRepository expenseRequestRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public ExpenseRequestServiceImpl(final ExpenseRequestRepository expenseRequestRepository,
                                     final SourceRepository sourceRepository) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.sourceRepository = sourceRepository;
    }

    @Override
    public ExpenseRequest getById(final UUID id) throws NotFoundException {
        return expenseRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, id));
    }

    @Override
    public List<ExpenseRequest> list() {
        return expenseRequestRepository.findAll();
    }

    @Transactional
    @Override
    public ExpenseRequest create(final ExpenseRequestModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        return updateOrCreate(model, new ExpenseRequest());
    }

    @Transactional
    @Override
    public ExpenseRequest update(final ExpenseRequestModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final ExpenseRequest expenseRequest = expenseRequestRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, model.getId()));

        return updateOrCreate(model, expenseRequest);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        expenseRequestRepository.deleteById(id);
    }

    private ExpenseRequest updateOrCreate(final ExpenseRequestModel model, final ExpenseRequest expenseRequest) throws NotFoundException {
        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        expenseRequest.setSource(source);
        expenseRequest.setCurrency(model.getCurrency());
        expenseRequest.setConfirm(model.isConfirm());
        expenseRequest.setPaid(model.isPaid());
        expenseRequest.setNumber(model.getNumber());
        expenseRequest.setSum(model.getSum());

        return expenseRequestRepository.save(expenseRequest);
    }

    private void validateModel(final ExpenseRequestModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getNumber() == null) {
            throw new ValidationException("Number is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }
    }
}
