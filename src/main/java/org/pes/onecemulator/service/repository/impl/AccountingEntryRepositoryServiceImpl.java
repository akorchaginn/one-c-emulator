package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
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
        return accountingEntry.getDeleted() ? null : accountingEntry;
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
    public AccountingEntry update(AccountingEntry accountingEntry) throws Exception {
        if (accountingEntry.getId() != null && exists(accountingEntry.getId())) {
            return entityManager.merge(accountingEntry);
        }

        throw new Exception("Entity " + accountingEntry.toString() + " not exist at database");
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        if (id != null && exists(id)) {
            AccountingEntry accountingEntry = accountingEntryRepository.findOne(id);
            accountingEntry.setDeleted(true);
            accountingEntryRepository.saveAndFlush(accountingEntry);
        }

        throw new Exception("Entity with id: " + id + " not exist at database");
    }

    @Override
    @Transactional
    public Boolean exists(UUID id) {
        return accountingEntryRepository.exists(id) && !accountingEntryRepository.findOne(id).getDeleted();
    }
}
