package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.SourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceServiceImpl.class);

    @Autowired
    private SourceRepository sourceRepository;

    @Transactional
    @Override
    public SourceModel getById(UUID id) throws NotFoundException {
        Source source = sourceRepository.findById(id).orElseThrow(() -> new NotFoundException(Source.class, id));
        return getModel(source);
    }

    @Transactional
    @Override
    public SourceModel getByName(String name) throws NotFoundException {
        Source source = sourceRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(Source.class, "name:" + name));
        return getModel(source);
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
    public List<PayerModel> getPayerList(String name) throws NotFoundException {
        Source source = sourceRepository.findByName(name)
                .orElseThrow(() ->  new NotFoundException(Source.class, "name:" + name));
        return source.getPayers().stream().map(this::getModel).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SourceModel create(SourceModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getName() == null) {
            throw new ValidationException("Name is null.");
        }

        if (model.getName().isEmpty()) {
            throw new ValidationException("Name is empty.");
        }

        Source source = new Source();
        source.setName(model.getName());
        source = sourceRepository.save(source);

        return getModel(source);
    }

    @Transactional
    @Override
    public SourceModel update(SourceModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        if (model.getName() == null) {
            throw new ValidationException("Name is null.");
        }

        if (model.getName().isEmpty()) {
            throw new ValidationException("Name is empty.");
        }

        Source source = sourceRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Source.class, model.getId()));
        source.setName(model.getName());
        source = sourceRepository.save(source);

        return getModel(source);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        sourceRepository.deleteById(id);
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
