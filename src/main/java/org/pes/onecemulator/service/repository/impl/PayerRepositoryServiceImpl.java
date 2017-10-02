package org.pes.onecemulator.service.repository.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.service.repository.PayerRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerRepositoryServiceImpl implements PayerRepositoryService {

    private static final Logger log = LoggerFactory.getLogger(PayerRepositoryService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PayerRepository payerRepository;

    @Override
    @Transactional
    public Payer findById(UUID id) {
        Payer payer = payerRepository.findOne(id);
        if (payer != null && !payer.getDeleted()) {
            log.info("Payer with id: " + id + " found.");
            return payer;
        }
        log.info("Payer with id: " + id + " not found.");
        return null;
    }

    @Override
    @Transactional
    public Payer findByCode(String code) {
        Payer payer = payerRepository.findByCode(code);
        if (payer != null && !payer.getDeleted()) {
            log.info("Payer with code: " + code + " found.");
            return payer;
        }
        log.info("Payer with code: " + code + " not found.");
        return null;
    }

    @Override
    @Transactional
    public List<Payer> findAll() {
        List<Payer> payers = payerRepository.findAll()
                                .parallelStream()
                                .filter(payer -> !payer.getDeleted())
                                .collect(Collectors.toList());
        if (payers.size() > 0) {
            log.info("Payers count: " + payers.size() + ".");
            return payers;
        }
        log.info("Payers count: 0.");
        return null;
    }

    @Override
    @Transactional
    public Payer create(Payer payer) {
        if (payer.getId() != null) {
            log.warn("Payer entity has id: " + payer.getId() + ".");
            return null;
        }

        if (payer.getCode() != null && payerRepository.findByCode(payer.getCode()) != null) {
            log.warn("Payer entity with code: " + payer.getCode() + " exist at database or payer code is null.");
            return null;
        }

        payer.setId(UUID.randomUUID());

        try {
            Payer p = payerRepository.saveAndFlush(payer);
            if (p != null) {
                log.info("Payer " + p.toString() + " created.");
                return p;
            } else {
                log.warn("Payer not created. See debug payerRepository.saveAndFlush(Payer payer)");
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    @Transactional
    public Payer update(Payer payer) {
        if (payerRepository.exists(payer.getId())) {
            log.warn("Payer with id: " + payer.getId() + " exist at database.");
            return null;
        }

        try {
            return entityManager.merge(payer);
        } catch (Exception e) {
            log.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    @Transactional
    public Payer delete(UUID id) {
        if (id == null) {
            log.warn("Delete method argument is null.");
            return null;
        }

        Payer payer = payerRepository.findOne(id);

        if (payer == null) {
            log.warn("Payer entity with id: " + id + " not found.");
            return null;
        }

        payer.setDeleted(true);

        try {
            return entityManager.merge(payer);
        } catch (Exception e) {
            log.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
