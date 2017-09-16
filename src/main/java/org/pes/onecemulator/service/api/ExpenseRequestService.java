package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseRequestService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseRequestService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private ExpenseRequestRepositoryService expenseRequestRepositoryService;

    public ExpenseRequestDto getExpenseRequestByNumber(String number) throws NotFoundEntityException {
        ExpenseRequest expenseRequest = expenseRequestRepositoryService.findByNumber(number);
        if (expenseRequest != null) {
            log.info("ExpenseRequest entity with number: " + number + " found");
            return convertToDto(expenseRequest);
        }
        log.info("ExpenseRequest entity with number: " + number + " not found");
        throw new NotFoundEntityException(404, "Entity with number: " + number + " not found at database");
    }

    private ExpenseRequestDto convertToDto(ExpenseRequest expenseRequest) {
        return mapperFactoryService.getMapper().map(expenseRequest, ExpenseRequestDto.class);
    }
}
