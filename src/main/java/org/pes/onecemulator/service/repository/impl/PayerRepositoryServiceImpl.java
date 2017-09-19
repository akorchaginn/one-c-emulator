package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.PayerRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerRepositoryServiceImpl implements PayerRepositoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PayerRepository payerRepository;

    @Override
    @Transactional
    public Payer findById(UUID id) {
        Payer payer = payerRepository.findOne(id);
        return !payer.getDeleted() ? payer : null;
    }

    @Override
    @Transactional
    public Payer findByCode(String code) {
        Payer payer = payerRepository.findByCode(code);
        return !payer.getDeleted() ? payer : null;
    }

    @Override
    @Transactional
    public List<Payer> findAll() {
        return payerRepository.findAll()
                .stream()
                .filter(payer -> !payer.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Payer create(Payer payer) throws CreateEntityException {
        try {
            boolean idIsNull = payer.getId() == null;
            if (idIsNull) {
                payer.setId(UUID.randomUUID());

                return payerRepository.saveAndFlush(payer);
            } else {
                throw new CreateEntityException(500, "ExpenseRequest entity with number: " + payer.getCode() + " exist at database");
            }
        } catch (Exception e) {
            throw new CreateEntityException(500, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Payer update(Payer payer) throws UpdateEntityException {
        if (payerRepository.exists(payer.getId())) {
            return entityManager.merge(payer);
        } else {
            throw new UpdateEntityException(500, "Entity " + payer.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public Payer delete(UUID id) throws DeleteEntityException {
        if (id != null) {
            Payer payer = payerRepository.findOne(id);
            if (payer != null) {
                payer.setDeleted(true);

                return entityManager.merge(payer);
            }
            throw new DeleteEntityException(500, "Entity with id: " + id + " not exist at database");
        }
        throw new DeleteEntityException(500, "Id is null");
    }
}
