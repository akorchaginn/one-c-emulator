package org.pes.onecemulator.service.impl;

import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.AccountingEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryServiceImpl implements AccountingEntryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingEntryServiceImpl.class);

    @Autowired
    private ExpenseRequestRepository expenseRequestRepository;

    @Autowired
    private AccountingEntryRepository accountingEntryRepository;

    public AccountingEntryDto getById(UUID id) throws Exception {
        AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
        if (accountingEntry != null) {
            return convertToDto(accountingEntry);
        }

        throw new Exception("AccountingEntry with id: " + id + " not found at database.");
    }

    public List<AccountingEntryDto> list() {
        List<AccountingEntry> accountingEntries = accountingEntryRepository.findAll();
        return convertToDto(accountingEntries);
    }

    public AccountingEntryDto create(AccountingEntryDto dto) throws Exception {
        if (dto != null && dto.getExpenseNumber() != null) {
            ExpenseRequest expenseRequest = expenseRequestRepository.findByNumber(dto.getExpenseNumber());
            if (expenseRequest != null) {
                AccountingEntry accountingEntry = convertToEntity(dto);
                if (accountingEntry != null) {
                    accountingEntry.setExpenseRequest(expenseRequest);
                    accountingEntry = accountingEntryRepository.save(accountingEntry);
                    return convertToDto(accountingEntry);
                }
            }

            throw new Exception("Expense request with number: " + dto.getExpenseNumber() + " not found at database.");
        }

        throw new Exception("Accounting entry dto is null or expense number is null.");
    }

    public AccountingEntryDto update(AccountingEntryDto dto) throws Exception {
        if (dto != null && dto.getId() != null && dto.getExpenseNumber() != null) {
            ExpenseRequest expenseRequest =
                    expenseRequestRepository.findByNumber(dto.getExpenseNumber());
            if (expenseRequest != null) {
                AccountingEntry accountingEntry = accountingEntryRepository.findOne(dto.getId());
                if (accountingEntry != null) {
                    accountingEntry.setCode(dto.getCode());
                    accountingEntry.setDate(dto.getDate());
                    accountingEntry.setDocumentName(dto.getDocumentName());
                    accountingEntry.setSum(dto.getSum());
                    accountingEntry = accountingEntryRepository.save(accountingEntry);
                    return convertToDto(accountingEntry);
                }
            }

            throw new Exception("Expense request with number: " + dto.getExpenseNumber() + " not found at database.");
        }

        throw new Exception("Accounting entry dto is null or id is null or expense number is null.");
    }

    public void delete(UUID id) {
       accountingEntryRepository.delete(id);
    }

    private AccountingEntryDto convertToDto(AccountingEntry accountingEntry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accountingEntry, AccountingEntryDto.class);
    }

    private List<AccountingEntryDto> convertToDto(List<AccountingEntry> accountingEntries) {
        return accountingEntries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AccountingEntry convertToEntity(AccountingEntryDto accountingEntryDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accountingEntryDto, AccountingEntry.class);
    }
}
