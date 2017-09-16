package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.service.repository.PayerRepositoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class PayerRepositoryServiceImpl implements PayerRepositoryService {

    @Resource
    private PayerRepository payerRepository;

    @Override
    @Transactional
    public Payer findById(UUID id) {
        return payerRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<Payer> findAll() {
        return payerRepository.findAll();
    }

    @Override
    @Transactional
    public Payer create(Payer payer) throws Exception {
        if (!payerRepository.exists(payer.getId())) {
            return payerRepository.saveAndFlush(payer);
        } else {
            throw new Exception("Entity: " + payer.toString() + " exist at database");
        }
    }

    @Override
    @Transactional
    public Payer update(Payer payer) throws Exception {
        if (payerRepository.exists(payer.getId())) {
            return payerRepository.saveAndFlush(payer);
        } else {
            throw new Exception("Entity " + payer.toString() + " not exist at database");
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) throws Exception {
        if (payerRepository.exists(id)) {
            payerRepository.delete(id);
        } else {
            throw new Exception("Entity with id: " + id + " not exist at database");
        }
    }
}
