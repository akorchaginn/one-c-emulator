package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestServiceImpl implements ExpenseRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestServiceImpl.class);

    private final ExpenseRequestRepository expenseRequestRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    ExpenseRequestServiceImpl(ExpenseRequestRepository expenseRequestRepository, SourceRepository sourceRepository) {
        this.expenseRequestRepository = expenseRequestRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public ExpenseRequestModel getById(UUID id) throws NotFoundException {
        ExpenseRequest expenseRequest = expenseRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, id));
        return getModel(expenseRequest);
    }

    @Transactional
    @Override
    public List<ExpenseRequestModel> list() {
        List<ExpenseRequest> expenseRequests = expenseRequestRepository.findAll();
        return expenseRequests
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ExpenseRequestModel create(ExpenseRequestModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getNumber() == null) {
            throw new ValidationException("Number is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setSource(source);
        expenseRequest.setCurrency(model.getCurrency());
        expenseRequest.setConfirm(model.getConfirm());
        expenseRequest.setPaid(model.getPaid());
        expenseRequest.setNumber(model.getNumber());
        expenseRequest.setSum(new BigDecimal(model.getSum()));
        expenseRequest = expenseRequestRepository.save(expenseRequest);

        return getModel(expenseRequest);
    }

    @Transactional
    @Override
    public ExpenseRequestModel update(ExpenseRequestModel model) throws Exception {

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

        ExpenseRequest expenseRequest = expenseRequestRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(ExpenseRequest.class, model.getId()));

        Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        expenseRequest.setSource(source);
        expenseRequest.setCurrency(model.getCurrency());
        expenseRequest.setConfirm(model.getConfirm());
        expenseRequest.setPaid(model.getPaid());
        expenseRequest.setNumber(model.getNumber());
        expenseRequest.setSum(new BigDecimal(model.getSum()));
        expenseRequest = expenseRequestRepository.save(expenseRequest);

        return getModel(expenseRequest);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        expenseRequestRepository.deleteById(id);
    }

    private ExpenseRequestModel getModel(ExpenseRequest entity) {
        ExpenseRequestModel model = new ExpenseRequestModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setCurrency(entity.getCurrency());
        model.setConfirm(entity.getConfirm());
        model.setPaid(entity.getPaid());
        model.setNumber(entity.getNumber());
        model.setSum(entity.getSum().toString());

        return model;
    }
}
