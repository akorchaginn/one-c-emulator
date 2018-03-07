package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.PayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerServiceImpl implements PayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayerServiceImpl.class);

    @Autowired
    private PayerRepository payerRepository;

    @Autowired
    private SourceRepository sourceRepository;

    @Transactional
    @Override
    public PayerModel getById(UUID id) throws NotFoundException {
        Payer payer = payerRepository.findById(id).orElseThrow(() -> new NotFoundException(Payer.class, id));
        return getModel(payer);
    }

    @Transactional
    @Override
    public List<PayerModel> list() {
        List<Payer> payers = payerRepository.findAll();
        return payers
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PayerModel create(PayerModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSources() == null) {
            throw new ValidationException("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            throw new ValidationException("Source list is empty.");
        }

        Set<Source> newSources = new HashSet<>();
        model.getSources().forEach(s -> {
            Source source = sourceRepository.findByName(s).orElseGet(() ->
            {
                Source newSource = new Source();
                newSource.setName(s);
                return newSource;
            });
            newSources.add(source);
        });

        if (newSources.isEmpty()) {
            throw new ValidationException("New source list is empty.");
        }

        Payer payer = new Payer();
        payer.setCode(model.getCode());
        payer.setName(model.getName());
        payer.setFullName(model.getFullName());
        payer.setInn(model.getInn());
        payer.setKpp(model.getKpp());
        payer.setAddress(model.getAddress());
        payer.setSources(newSources);
        payer = payerRepository.save(payer);

        return getModel(payer);
    }

    @Transactional
    @Override
    public PayerModel update(PayerModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        if (model.getSources() == null) {
            throw new ValidationException("Source list is null.");
        }

        if (model.getSources().isEmpty()) {
            throw new ValidationException("Source list is empty.");
        }

        Payer payer = payerRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Payer.class, model.getId()));

        Set<Source> newSources = new HashSet<>();
        model.getSources().forEach(s -> {
            Source source = sourceRepository.findByName(s).orElseGet(() ->
            {
                Source newSource = new Source();
                newSource.setName(s);
                return newSource;
            });
            newSources.add(source);
        });

        if (newSources.isEmpty()) {
            throw new ValidationException("New source list is empty.");
        }

        payer.setCode(model.getCode());
        payer.setName(model.getName());
        payer.setFullName(model.getFullName());
        payer.setInn(model.getInn());
        payer.setKpp(model.getKpp());
        payer.setAddress(model.getAddress());
        payer.setSources(newSources);
        payer = payerRepository.save(payer);

        return getModel(payer);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        payerRepository.deleteById(id);
    }

    private PayerModel getModel(Payer entity) {
        PayerModel model = new PayerModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());
        Set<String> sources = entity.getSources().stream().map(Source::getName).collect(Collectors.toSet());
        model.setSources(sources);

        return model;
    }
}
