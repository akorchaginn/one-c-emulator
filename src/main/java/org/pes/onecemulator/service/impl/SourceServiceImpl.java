package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    @Autowired
    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public SourceModel getById(final UUID id) throws NotFoundException {
        Source source = sourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Source.class, id));
        return getModel(source);
    }

    @Transactional
    @Override
    public SourceModel getByName(final String name) throws NotFoundException {
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
    public SourceModel create(final SourceModel model) throws Exception {

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
    public SourceModel update(final SourceModel model) throws Exception {

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
    public void delete(final UUID id) {
        sourceRepository.deleteById(id);
    }

    private SourceModel getModel(final Source entity) {
        final SourceModel model = new SourceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());

        return model;
    }
}
