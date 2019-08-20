package org.pes.onecemulator.facade.impl;

import org.pes.onecemulator.converter.internal.PayerModelConverter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.facade.PayerFacade;
import org.pes.onecemulator.model.internal.PayerModel;
import org.pes.onecemulator.service.PayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerFacadeImpl implements PayerFacade {

    private static final PayerModelConverter PAYER_MODEL_CONVERTER = new PayerModelConverter();

    private final PayerService payerService;

    @Autowired
    public PayerFacadeImpl(final PayerService payerService) {
        this.payerService = payerService;
    }

    @Override
    public List<PayerModel> list() {
        return payerService.list().stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listCodeBySource(final String source) {
        return payerService.listBySource(source).stream()
                .map(Payer::getCode)
                .collect(Collectors.toList());
    }

    @Override
    public PayerModel create(final PayerModel model) throws ValidationException {
        return PAYER_MODEL_CONVERTER.convert(payerService.create(model));
    }

    @Override
    public PayerModel update(final PayerModel model) throws NotFoundException, ValidationException {
        return PAYER_MODEL_CONVERTER.convert(payerService.update(model));
    }

    @Override
    public void delete(final UUID id) {
        payerService.delete(id);
    }
}
