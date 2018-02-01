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

    public PayerModel getById(UUID id) {
        Payer payer = payerRepository.findOne(id);
        if (payer != null) {
            return getModel(payer);
        }

        return new PayerModel("Payer with id: " + id + " not found at database.");
    }

    public PayerModel getByCode(String code) {
        Payer payer = payerRepository.findByCode(code);
        if (payer != null) {
            return getModel(payer);
        }

        return new PayerModel("Payer with code: " + code + " not found at database.");
    }

    public List<PayerModel> list() {
        List<Payer> payers = payerRepository.findAll();
        return payers
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    public PayerModel create(PayerModel model) {
        if (model != null && model.getSource() != null && !model.getSource().isEmpty()) {

            Set<Source> newSources = new HashSet<>();
            model.getSource().forEach(s -> {
                Source source = sourceRepository.findByName(s);
                if (source != null) {
                    newSources.add(source);
                } else {
                    Source newSource = new Source();
                    newSource.setName(s);
                    newSources.add(newSource);
                }
            });

            if (!newSources.isEmpty()) {
                Payer payer = new Payer();
                payer.setCode(model.getCode());
                payer.setName(model.getName());
                payer.setFullName(model.getFullName());
                payer.setAddress(model.getAddress());
                payer.setInn(model.getInn());
                payer.setKpp(model.getKpp());
                payer.setSources(newSources);
                payer = payerRepository.save(payer);

                return getModel(payer);
            }

            return new PayerModel("New source list is empty.");
        }

        return new PayerModel(
                model == null
                    ? "Model is null."
                    : model.getSource() == null
                        ? "Sources list is null."
                        : "Source list is empty."
        );
    }

    public PayerModel update(PayerModel model) {
        if (model != null && model.getId() != null &&
                model.getCode() != null && model.getSource() != null && !model.getSource().isEmpty()) {

            Payer payer = payerRepository.findOne(model.getId());

            if (payer != null) {

                Set<Source> newSources = new HashSet<>();
                model.getSource().forEach(s -> {
                    Source source = sourceRepository.findByName(s);
                    if (source != null) {
                        newSources.add(source);
                    } else {
                        Source newSource = new Source();
                        newSource.setName(s);
                        sourceRepository.save(newSource);
                    }
                });

                Set<Source> oldSources = new HashSet<>();

                payer.getSources().forEach(s -> {
                    if (!newSources.contains(s)) {
                        oldSources.remove(s);
                    } else {
                        oldSources.add(s);
                    }
                });

                payer.setAddress(model.getAddress());
                payer.setCode(model.getCode());
                payer.setFullName(model.getFullName());
                payer.setInn(model.getInn());
                payer.setKpp(model.getKpp());
                payer.setName(model.getName());
                payer.setSources(oldSources);
                payer = payerRepository.save(payer);

                return getModel(payer);
            }

            return new PayerModel("Payer with id: " + model.getId() + " not found at database.");
        }

        return new PayerModel("Payer is null or id is null or code is null.");
    }

    public void delete(UUID id) {
        payerRepository.delete(id);
    }

    private PayerModel getModel(Payer entity) {
        PayerModel model = new PayerModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setAddress(entity.getAddress());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setSource(entity.getSources().stream().map(Source::getName).collect(Collectors.toSet()));

        return model;
    }
}
