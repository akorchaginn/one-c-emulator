package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.ERequestModel;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestServiceImpl implements ExpenseRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestServiceImpl.class);

    @Autowired
    private ExpenseRequestRepository expenseRequestRepository;

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;

    @Autowired
    private SourceRepository sourceRepository;

    public ERequestModel getById(UUID id) {
        ExpenseRequest expenseRequest = expenseRequestRepository.findOne(id);
        if (expenseRequest != null) {
            return getModel(expenseRequest);
        }

        return new ERequestModel("Expense request with id: " + id + " not found at database.");
    }

    public List<ERequestModel> list() {
        List<ExpenseRequest> expenseRequests = expenseRequestRepository.findAll();
        return expenseRequests
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    public ERequestModel create(ERequestModel model) {
        if (model != null && model.getNumber() != null && model.getSource() != null) {
            Source source = sourceRepository.findByName(model.getSource());
            if (source != null) {
                ExpenseRequest expenseRequest = new ExpenseRequest();
                expenseRequest.setSource(source);
                expenseRequest.setCurrency(model.getCurrency());
                expenseRequest.setConfirm(model.getConfirm());
                expenseRequest.setPaid(model.getPaid());
                expenseRequest.setNumber(model.getNumber());
                expenseRequest.setSum(model.getSum());
                expenseRequest = expenseRequestRepository.save(expenseRequest);

                return getModel(expenseRequest);
            }

            return new ERequestModel("Source with name: " + model.getSource() + " not found at database.");
        }

        return new ERequestModel(
                model == null
                        ? "Model is null."
                        : model.getNumber() == null
                            ? "Number is null."
                            : "Source is null."
        );
    }

    public ERequestModel update(ERequestModel model) {
        if (model != null && model.getId() != null && model.getNumber() != null && model.getSource() != null) {
            ExpenseRequest expenseRequest = expenseRequestRepository.findOne(model.getId());
            if (expenseRequest != null) {
                Source source = sourceRepository.findByName(model.getSource());
                if (source != null) {
                    expenseRequest.setSource(source);
                    expenseRequest.setCurrency(model.getCurrency());
                    expenseRequest.setConfirm(model.getConfirm());
                    expenseRequest.setPaid(model.getPaid());
                    expenseRequest.setNumber(model.getNumber());
                    expenseRequest.setSum(model.getSum());
                    expenseRequest = expenseRequestRepository.save(expenseRequest);

                    return getModel(expenseRequest);
                }

                return new ERequestModel("Source with name: " + model.getSource() + " not found at database.");
            }

            return new ERequestModel("Expense request with id: " + model.getId() + " not found at database.");
        }

        return new ERequestModel(
                model == null
                        ? "Model is null."
                        : model.getId() == null
                            ? "Id is null."
                            : model.getNumber() == null
                                ? "Number is null."
                                : "Source is null."
        );
    }

    public void delete(UUID id) {
        expenseRequestRepository.delete(id);
    }

    private ERequestModel getModel(ExpenseRequest entity) {
        ERequestModel model = new ERequestModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setCurrency(entity.getCurrency());
        model.setConfirm(entity.getConfirm());
        model.setPaid(entity.getPaid());
        model.setNumber(entity.getNumber());
        model.setSum(entity.getSum());

        return model;
    }
}
