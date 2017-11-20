package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.InvoiceRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceRepositoryServiceImpl implements InvoiceRepositoryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Invoice findById(UUID id) {
        Invoice invoice = invoiceRepository.findOne(id);
        if (invoice != null && !invoice.getDeleted())
            return invoice;
        return null;
    }

    @Override
    @Transactional
    public Invoice findByExternalId(UUID externalId) {
        Invoice invoice = invoiceRepository.findByExternalId(externalId);
        if (invoice != null && !invoice.getDeleted())
            return invoice;
        return null;
    }

    @Override
    @Transactional
    public List<Invoice> findAll() {
        return invoiceRepository.findAll()
                .stream()
                .filter(invoice -> !invoice.getDeleted())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Invoice create(Invoice invoice) throws CreateEntityException {
        try {
            boolean idIsNull = invoice.getId() == null;
            if (idIsNull) {
                invoice.setId(UUID.randomUUID());

                return invoiceRepository.saveAndFlush(invoice);
            } else {
                throw new CreateEntityException(500, "ExpenseRequest entity with number: " + invoice.getNumber() + " exist at database");
            }
        } catch (Exception e) {
            throw new CreateEntityException(500, e.getMessage());
        }
    }

    @Override
    @Transactional
    public Invoice update(Invoice invoice) throws UpdateEntityException {
        if (invoiceRepository.exists(invoice.getId())) {
            return entityManager.merge(invoice);
        } else {
            throw new UpdateEntityException(500, "Entity " + invoice.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public Invoice delete(UUID id) throws DeleteEntityException {
        if (id != null) {
            Invoice invoice = invoiceRepository.findOne(id);
            if (invoice != null) {
                invoice.setDeleted(true);

                return entityManager.merge(invoice);
            }
            throw new DeleteEntityException(500, "Entity with id: " + id + " not exist at database");
        }
        throw new DeleteEntityException(500, "Id is null");
    }
}
