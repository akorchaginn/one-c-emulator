package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
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

    private final PayerRepository payerRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    PayerServiceImpl(PayerRepository payerRepository, SourceRepository sourceRepository) {
        this.payerRepository = payerRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public PayerModel getById(UUID id) {
        Payer payer = payerRepository.findOne(id);
        if (payer != null) {
            return getModel(payer);
        }

        return new PayerModel("Payer with id: " + id + " not found at database.");
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
    public PayerModel create(PayerModel model) {

        if (model == null) {
            return new PayerModel("Model is null.");
        }

        if (model.getSources() == null) {
            return new PayerModel("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            return new PayerModel("Source list is empty.");
        }

        Set<Source> newSources = new HashSet<>();
        model.getSources().forEach(s -> {
            Source source = sourceRepository.findByName(s);
            if (source != null) {
                newSources.add(source);
            } else {
                Source newSource = new Source();
                newSource.setName(s);
                newSources.add(newSource);
            }
        });

        if (newSources.isEmpty()) {
            return new PayerModel("New source list is empty.");
        }

        try {
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
        } catch (Exception e) {
            return new PayerModel(e.getMessage());
        }
    }

    @Transactional
    @Override
    public PayerModel update(PayerModel model) {

        if (model == null) {
            return new PayerModel("Model is null.");
        }

        if (model.getId() == null) {
            return new PayerModel("Id is null.");
        }

        if (model.getSources() == null) {
            return new PayerModel("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            return new PayerModel("Source list is empty.");
        }

        Payer payer = payerRepository.findOne(model.getId());

        if (payer == null) {
            return new PayerModel("Payer with id: " + model.getId() + " not found at database.");
        }

        Set<Source> newSources = new HashSet<>();
        model.getSources().forEach(s -> {
            Source source = sourceRepository.findByName(s);
            if (source != null) {
                newSources.add(source);
            } else {
                Source newSource = new Source();
                newSource.setName(s);
                newSources.add(newSource);
            }
        });

        if (newSources.isEmpty()) {
            return new PayerModel("New source list is empty.");
        }

        try {
            payer.setCode(model.getCode());
            payer.setName(model.getName());
            payer.setFullName(model.getFullName());
            payer.setInn(model.getInn());
            payer.setKpp(model.getKpp());
            payer.setAddress(model.getAddress());
            payer.setSources(newSources);
            payer = payerRepository.save(payer);

            return getModel(payer);
        } catch (Exception e) {
            return new PayerModel(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        payerRepository.delete(id);
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
