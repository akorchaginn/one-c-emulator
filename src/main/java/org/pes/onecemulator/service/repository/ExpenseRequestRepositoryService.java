package org.pes.onecemulator.service.repository;

import org.pes.onecemulator.entity.ExpenseRequest;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestRepositoryService {
    ExpenseRequest findById(UUID id);
    ExpenseRequest findByNumber(String number);
    List<ExpenseRequest> findAll();
    ExpenseRequest create(ExpenseRequest expenseRequest) throws Exception;
    ExpenseRequest update(ExpenseRequest expenseRequest) throws Exception;
    ExpenseRequest delete(UUID id) throws Exception;
}
