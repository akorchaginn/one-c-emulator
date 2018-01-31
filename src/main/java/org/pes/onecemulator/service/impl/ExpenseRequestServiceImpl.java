package org.pes.onecemulator.service.impl;

import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
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

    public ExpenseRequestDto getById(UUID id) throws Exception {
        ExpenseRequest expenseRequest = expenseRequestRepository.findOne(id);
        if (expenseRequest != null) {
            return convertToDto(expenseRequest);
        }

        throw new Exception("Expense request with id: " + id + " not found at database.");
    }

    public List<ExpenseRequestDto> list() {
        return convertToDto(expenseRequestRepository.findAll());
    }

    public ExpenseRequestDto create(ExpenseRequestDto dto) throws Exception {
        if (dto != null && dto.getNumber() != null) {
            ExpenseRequest expenseRequest = convertToEntity(dto);
            expenseRequest = expenseRequestRepository.save(expenseRequest);
            return convertToDto(expenseRequest);
        }

        throw new Exception("Expense request is null or number is null.");
    }

    public ExpenseRequestDto update(ExpenseRequestDto dto) throws Exception {
        if (dto != null && dto.getId() != null && dto.getNumber() != null) {
            ExpenseRequest expenseRequest = expenseRequestRepository.findOne(dto.getId());
            if (expenseRequest != null) {
                expenseRequest.setConfirm(dto.getConfirm());
                expenseRequest.setCurrency(dto.getCurrency());
                expenseRequest.setPaid(dto.getPaid());
                expenseRequest.setSum(dto.getSum());
                expenseRequest = expenseRequestRepository.save(expenseRequest);
                return convertToDto(expenseRequest);
            }

            throw new Exception("Expense request with id: " + dto.getId() + " not found at database.");
        }

        throw new Exception("Expense request is null or id is null or number is null.");
    }

    public ExpenseRequestDto getExpenseRequestByNumber(String number) throws Exception {
        ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(number);
        if (expenseRequest != null) {
            return convertToDto(expenseRequest);
        }

        throw new Exception("Expense request with number: " + number + " not found at database.");
    }

    public void delete(UUID id) {
        expenseRequestRepository.delete(id);
    }

    private ExpenseRequestDto convertToDto(ExpenseRequest expenseRequest) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(expenseRequest, ExpenseRequestDto.class);
    }

    private List<ExpenseRequestDto> convertToDto(List<ExpenseRequest> accountingEntries) {
        return accountingEntries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ExpenseRequest convertToEntity(ExpenseRequestDto accountingEntryDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(accountingEntryDto, ExpenseRequest.class);
    }
}
