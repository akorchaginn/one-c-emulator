package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.SourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceServiceImpl.class);

    private SourceRepository sourceRepository;

    @Autowired
    SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public SourceModel getById(UUID id) {
        Source source = sourceRepository.findOne(id);
        if (source != null) {
            return getModel(source);
        }

        return new SourceModel("Source with id:" + id + " not found at database.");
    }

    @Transactional
    @Override
    public SourceModel getByName(String name) {
        Source source = sourceRepository.findByName(name);
        if (source != null) {
            return getModel(source);
        }

        return new SourceModel("Source with name:" + name + " not found at database.");
    }

    @Transactional
    @Override
    public List<SourceModel> list() {
        return sourceRepository.findAll()
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<PayerModel> getPayerList(String name) {
        Source source = sourceRepository.findByName(name);
        if (source != null) {
            return source.getPayers().stream().map(this::getModel).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Transactional
    @Override
    public SourceModel create(SourceModel model) {

        if (model == null) {
            return new SourceModel("Model is null.");
        }

        if (model.getName() == null) {
            return new SourceModel("Model name is null.");
        }

        if (model.getName().isEmpty()) {
            return new SourceModel("Model name is empty.");
        }

        Source source = new Source();
        source.setName(model.getName());
        source = sourceRepository.save(source);

        return getModel(source);
    }

    @Transactional
    @Override
    public SourceModel update(SourceModel model) {

        if (model == null) {
            return new SourceModel("Model is null.");
        }

        if (model.getId() == null) {
            return new SourceModel("Id is null.");
        }

        if (model.getName() == null) {
            return new SourceModel("Model name is null.");
        }

        if (model.getName().isEmpty()) {
            return new SourceModel("Model name is empty.");
        }

        Source source = sourceRepository.findOne(model.getId());

        if (source == null) {
            return new SourceModel("Source with id: " + model.getId() + " not found at database.");
        }

        source.setName(model.getName());
        source = sourceRepository.save(source);

        return getModel(source);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        sourceRepository.delete(id);
    }

    private SourceModel getModel(Source entity) {
        SourceModel model = new SourceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());

        return model;
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
