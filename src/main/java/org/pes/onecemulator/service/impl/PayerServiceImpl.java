package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.PayerModelConverter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.PayerSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.PayerService;
import org.pes.onecemulator.service.PayerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerServiceImpl implements PayerService {

    private static final PayerModelConverter PAYER_MODEL_CONVERTER = new PayerModelConverter();

    private final PayerRepository payerRepository;

    private final SourceRepository sourceRepository;

    private final PayerSourceService payerSourceService;

    @Autowired
    public PayerServiceImpl(final PayerRepository payerRepository,
                            final SourceRepository sourceRepository,
                            final PayerSourceService payerSourceService) {
        this.payerRepository = payerRepository;
        this.sourceRepository = sourceRepository;
        this.payerSourceService = payerSourceService;
    }

    @Override
    public PayerModel getById(final UUID id) throws NotFoundException {
        return payerRepository.findById(id)
                .map(PAYER_MODEL_CONVERTER::convert)
                .orElseThrow(() -> new NotFoundException(Payer.class, id));
    }

    @Override
    public List<PayerModel> list() {
        return payerRepository.findAll()
                .stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayerModel> listBySource(final String source) {
        return sourceRepository.findByName(source)
                .map(Source::getPayerSources)
                .orElse(new ArrayList<>())
                .stream()
                .map(PayerSource::getPayer)
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PayerModel create(final PayerModel model) throws ValidationException {
        validateModel(model);

        return PAYER_MODEL_CONVERTER.convert(updateOrCreate(model, new Payer()));
    }

    @Transactional
    @Override
    public List<PayerModel> create(final List<PayerModel> models) throws ValidationException {
        final List<PayerModel> result = new ArrayList<>();
        for (PayerModel model : models) {
            result.add(create(model));
        }

        return result;
    }

    @Transactional
    @Override
    public PayerModel update(final PayerModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final Payer payer = payerRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Payer.class, model.getId()));

        return PAYER_MODEL_CONVERTER.convert(updateOrCreate(model, payer));
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        payerRepository.deleteById(id);
    }

    private Payer updateOrCreate(final PayerModel model, final Payer payer) throws ValidationException {
        payer.setCode(model.getCode());
        payer.setName(model.getName());
        payer.setFullName(model.getFullName());
        payer.setInn(model.getInn());
        payer.setKpp(model.getKpp());
        payer.setAddress(model.getAddress());

        payerSourceService.add(payer, model.getSources());

        return payerRepository.save(payer);
    }

    private void validateModel(final PayerModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSources() == null) {
            throw new ValidationException("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            throw new ValidationException("Source list is empty.");
        }
    }
}
