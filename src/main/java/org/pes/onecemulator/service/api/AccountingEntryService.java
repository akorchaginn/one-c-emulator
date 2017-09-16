package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.repository.AccountingEntryRepositoryService;
import org.pes.onecemulator.service.repository.ExpenseRequestRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryService {

    private static final Logger log = LoggerFactory.getLogger(AccountingEntryService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private ExpenseRequestService expenseRequestService;

    @Autowired
    private AccountingEntryRepositoryService accountingEntryRepositoryService;

    public AccountingEntryDto getAccountingEntryById(UUID id) throws NotFoundEntityException {
        AccountingEntry accountingEntry = accountingEntryRepositoryService.findById(id);
        if (accountingEntry != null) {
            log.info("AccountingEntry entity with id: " + id.toString() + " found");
            return convertToDto(accountingEntry);
        }
        log.warn("AccountingEntry entity with id: " + id.toString() + " not found");
        throw new NotFoundEntityException(404, "Entity with id: " + id + " not found at database");
    }

    public List<AccountingEntryDto> listAccountingEntry() throws NotFoundEntityException {
        List<AccountingEntry> accountingEntries = accountingEntryRepositoryService.findAll();
        if (accountingEntries.size() > 0) {
            log.info("AccountingEntry entity list count: " + accountingEntries.size());
            return convertToDto(accountingEntries);
        }
        log.warn("AccountingEntry entity list count = 0");
        throw new NotFoundEntityException(404, "AccountingEntry entity list count = 0");
    }

    public AccountingEntryDto createAccountingEntry(AccountingEntryDto accountingEntryDto) throws CreateEntityException {
        try {
            if (accountingEntryDto != null && accountingEntryDto.getExpenseNumber() != null) {
                ExpenseRequestDto expenseRequestDto = expenseRequestService.getExpenseRequestByNumber(accountingEntryDto.getExpenseNumber());
                if (expenseRequestDto != null) {
                    accountingEntryDto.setExpenseRequest(expenseRequestDto);
                    log.info("ExpenseRequest: " + expenseRequestDto.toString());
                    AccountingEntry accountingEntry = accountingEntryRepositoryService.create(convertToEntity(accountingEntryDto));
                    AccountingEntryDto accountingEntryDtoCreated = convertToDto(accountingEntry);
                    log.info("AccountingEntry created: " + accountingEntryDtoCreated.toString());

                    return accountingEntryDtoCreated;
                }
            }
        } catch (Exception e) {
            log.error("AccountingEntry create error: " + e.getMessage());
            throw new CreateEntityException(500, e.getMessage());
        }
        log.error("AccountingEntry entity is null or has not expenseNumber value");
        throw new CreateEntityException(500, "AccountingEntry entity is null or has not expenseNumber value");
    }

    private AccountingEntryDto convertToDto(AccountingEntry accountingEntry) {
        return mapperFactoryService.getMapper().map(accountingEntry, AccountingEntryDto.class);
    }

    private List<AccountingEntryDto> convertToDto(List<AccountingEntry> accountingEntries) {
        return accountingEntries.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AccountingEntry convertToEntity(AccountingEntryDto accountingEntryDto) {
        return mapperFactoryService.getMapper().map(accountingEntryDto, AccountingEntry.class);
    }
}
