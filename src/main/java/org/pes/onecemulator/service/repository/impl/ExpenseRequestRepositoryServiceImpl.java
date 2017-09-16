package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseRequestRepositoryServiceImpl implements ExpenseRequestRepositoryService {

    @Resource
    private ExpenseRequestRepository expenseRequestRepository;

    @Override
    @Transactional
    public ExpenseRequest findById(UUID id) {
        return expenseRequestRepository.findOne(id);
    }

    @Override
    @Transactional
    public ExpenseRequest findByNumber(String number) {
        return expenseRequestRepository.findByNumber(number);
    }

    @Override
    @Transactional
    public List<ExpenseRequest> findAll() {
        return expenseRequestRepository.findAll();
    }

    @Override
    @Transactional
    public ExpenseRequest create(ExpenseRequest expenseRequest) throws Exception {
        if (!expenseRequestRepository.exists(expenseRequest.getId())) {
            return expenseRequestRepository.saveAndFlush(expenseRequest);
        } else {
            throw new Exception("Entity: " + expenseRequest.toString() + " exist at database");
        }
    }

    @Override
    @Transactional
    public ExpenseRequest update(ExpenseRequest expenseRequest) throws Exception {
        if (expenseRequestRepository.exists(expenseRequest.getId())) {
            return expenseRequestRepository.saveAndFlush(expenseRequest);
        } else {
            throw new Exception("Entity " + expenseRequest.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        if (expenseRequestRepository.exists(id)) {
            expenseRequestRepository.delete(id);
        } else {
            throw new Exception("Entity with id: " + id + " not exist at database");
        }
    }
}
