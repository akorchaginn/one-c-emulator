package org.pes.onecemulator.service;

import org.pes.onecemulator.dto.ExpenseRequestDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ExpenseRequestDto getById(UUID id) throws Exception;

    List<ExpenseRequestDto> list();

    ExpenseRequestDto create(ExpenseRequestDto expenseRequestDto) throws Exception;

    ExpenseRequestDto update(ExpenseRequestDto expenseRequestDto) throws Exception;

    ExpenseRequestDto getExpenseRequestByNumber(String number) throws Exception;

    void delete(UUID id);
}
