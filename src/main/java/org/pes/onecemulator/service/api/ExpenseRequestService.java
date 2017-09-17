package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseRequestService {

    private static final Logger log = LoggerFactory.getLogger(ExpenseRequestService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private ExpenseRequestRepositoryService expenseRequestRepositoryService;

    public ExpenseRequestDto getExpenseRequestById(UUID id) throws NotFoundEntityException {
        ExpenseRequest expenseRequest = expenseRequestRepositoryService.findById(id);
        if (expenseRequest != null) {
            log.info("ExpenseRequest entity with id: " + id + " found");
            return convertToDto(expenseRequest);
        }
        log.info("ExpenseRequest entity with id: " + id + " not found");
        throw new NotFoundEntityException(404, "Entity with id: " + id + " not found at database");
    }

    public List<ExpenseRequestDto> listExpenseRequest() throws NotFoundEntityException {
        List<ExpenseRequest> expenseRequests = expenseRequestRepositoryService.findAll();
        if (expenseRequests.size() > 0) {
            log.info("ExpenseRequest entity list count: " + expenseRequests.size());
            return convertToDto(expenseRequests);
        }
        log.warn("ExpenseRequest entity list count = 0");
        throw new NotFoundEntityException(404, "ExpenseRequest entity list count = 0");
    }

    public ExpenseRequestDto createExpenseRequest(ExpenseRequestDto expenseRequestDto) throws CreateEntityException {
        try {
            if (expenseRequestDto != null && expenseRequestDto.getNumber() != null) {
                log.info("ExpenseRequest: " + expenseRequestDto.toString());
                ExpenseRequestDto result = convertToDto(expenseRequestRepositoryService.create(convertToEntity(expenseRequestDto)));
                log.info("ExpenseRequest created: " + result.toString());

                return result;
            }
        } catch (Exception e) {
            log.error("ExpenseRequest create error: " + e.getMessage());
            throw new CreateEntityException(500, e.getMessage());
        }
        log.error("ExpenseRequest entity is null or has not expenseNumber value");
        throw new CreateEntityException(500, "ExpenseRequest entity is null or has not expenseNumber value");
    }

    public ExpenseRequestDto updateExpenseRequest(ExpenseRequestDto expenseRequestDto) throws UpdateEntityException {
        try {
            if (expenseRequestDto != null && expenseRequestDto.getId() != null && expenseRequestDto.getNumber() != null) {
                ExpenseRequestDto tmp = convertToDto(expenseRequestRepositoryService.findById(expenseRequestDto.getId()));
                if(tmp != null) {
                    log.info("ExpenseRequest: " + expenseRequestDto.toString());
                    tmp.setConfirm(expenseRequestDto.getConfirm());
                    tmp.setCurrency(expenseRequestDto.getCurrency());
                    tmp.setPaid(expenseRequestDto.getPaid());
                    tmp.setSum(expenseRequestDto.getSum());
                    ExpenseRequestDto result = convertToDto(expenseRequestRepositoryService.update(convertToEntity(tmp)));
                    log.info("ExpenseRequest updated: " + result.toString());

                    return result;
                }
            }
        } catch (Exception e) {
            throw new UpdateEntityException(500, e.getMessage());
        }
        throw new UpdateEntityException(500, "ExpenseRequest entity is null");
    }

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

    private List<ExpenseRequestDto> convertToDto(List<ExpenseRequest> accountingEntries) {
        return accountingEntries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ExpenseRequest convertToEntity(ExpenseRequestDto accountingEntryDto) {
        return mapperFactoryService.getMapper().map(accountingEntryDto, ExpenseRequest.class);
    }
}
