package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.service.repository.InvoiceRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceRepositoryServiceImpl implements InvoiceRepositoryService {

    @Resource
    private InvoiceRepository invoiceRepository;

    @Override
    @Transactional
    public Invoice findById(UUID id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    @Transactional
    public Invoice create(Invoice invoice) throws Exception {
        if (!invoiceRepository.exists(invoice.getId())) {
            return invoiceRepository.saveAndFlush(invoice);
        } else {
            throw new Exception("Entity: " + invoice.toString() + " exist at database");
        }
    }

    @Override
    @Transactional
    public Invoice update(Invoice invoice) throws Exception {
        if (invoiceRepository.exists(invoice.getId())) {
            return invoiceRepository.saveAndFlush(invoice);
        } else {
            throw new Exception("Entity " + invoice.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        if (invoiceRepository.exists(id)) {
            invoiceRepository.delete(id);
        } else {
            throw new Exception("Entity with id: " + id + " not exist at database");
        }
    }
}
