package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.PayerSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.repository.PayerSourceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.PayerSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PayerSourceServiceImpl implements PayerSourceService {

    private final PayerSourceRepository payerSourceRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public PayerSourceServiceImpl(final PayerSourceRepository payerSourceRepository,
                                  final SourceRepository sourceRepository) {
        this.payerSourceRepository = payerSourceRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public void add(final Payer payer, final Set<String> sources) throws ValidationException {
        if (sources == null || sources.isEmpty()) {
            throw new ValidationException("Source list is null or empty.");
        }

        final List<PayerSource> payerSources = new ArrayList<>();

        for (String sourceName : sources) {
            final PayerSource payerSource = new PayerSource();
            payerSource.setPayer(payer);

            final Source source = sourceRepository.findByName(sourceName)
                    .orElseGet(() -> {
                       final Source newSource = new Source();
                       newSource.setName(sourceName);
                       return newSource;
                    });
            payerSource.setSource(source);
            payerSources.add(payerSource);
        }

        payerSourceRepository.saveAll(payerSources);
    }
}
