package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.AccountingEntryRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryRepositoryServiceImpl implements AccountingEntryRepositoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private AccountingEntryRepository accountingEntryRepository;

    @Override
    @Transactional
    public AccountingEntry findById(UUID id) {
        AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
        if (accountingEntry != null && !accountingEntry.getDeleted())
            return accountingEntry;
        return null;
    }

    @Override
    @Transactional
    public List<AccountingEntry> findAll() {
        return accountingEntryRepository.findAll()
                .stream()
                .filter(accountingEntry -> !accountingEntry.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountingEntry create(AccountingEntry accountingEntry) throws CreateEntityException {
        try {
            if (accountingEntry.getId() == null && !accountingEntryRepository.exists(accountingEntry.getCode())) {
                accountingEntry.setId(UUID.randomUUID());

                return  accountingEntryRepository.saveAndFlush(accountingEntry);
            }
        } catch (Exception e) {
            throw new CreateEntityException(500, "AccountingEntry entity create error: \n" + e.getMessage());
        }
        throw new CreateEntityException(500, "AccountingEntry entity: " + accountingEntry.toString() + " has id = null at entity or value code at database");
    }

    @Override
    @Transactional
    public AccountingEntry update(AccountingEntry accountingEntry) throws UpdateEntityException {
        if (accountingEntry.getId() != null && accountingEntryRepository.exists(accountingEntry.getId())) {
            return entityManager.merge(accountingEntry);
        }

        throw new UpdateEntityException(500, "Entity " + accountingEntry.toString() + " not exist at database");
    }

    @Override
    @Transactional
    public AccountingEntry delete(UUID id) throws DeleteEntityException {
        if (id != null) {
            AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
            if (accountingEntry != null) {
                accountingEntry.setDeleted(true);
                return entityManager.merge(accountingEntry);
            }
            throw new DeleteEntityException(500, "AccountingEntry with id: "+ id.toString() + " not found at database");
        }
        throw new DeleteEntityException(500, "Delete id is null");
    }
}
