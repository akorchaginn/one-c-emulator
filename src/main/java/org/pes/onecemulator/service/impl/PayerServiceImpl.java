package org.pes.onecemulator.service.impl;

import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.service.PayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerServiceImpl implements PayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayerServiceImpl.class);

    @Autowired
    private PayerRepository payerRepository;

    public PayerDto getById(UUID id) throws Exception {
        Payer payer = payerRepository.findOne(id);
        if (payer != null) {
            return convertToDto(payer);
        }

        throw new Exception("Payer with id: " + id + " not found at database.");
    }

    public PayerDto getByCode(String code) throws Exception {
        Payer payer = payerRepository.findByCode(code);
        if (payer != null) {
            return convertToDto(payer);
        }

        throw new Exception("Payer with code: " + code + " not found at database.");
    }

    public List<PayerDto> list() {
        return convertToDto(payerRepository.findAll());
    }

    public PayerDto create(PayerDto payerDto) throws Exception {
        if (payerDto != null) {
            Payer payer = convertToEntity(payerDto);
            payer = payerRepository.save(payer);
            return convertToDto(payer);
        }

        throw new Exception("Payer is null.");
    }

    public PayerDto update(PayerDto payerDto) throws Exception {
        if (payerDto != null && payerDto.getId() != null && payerDto.getCode() != null) {
            Payer payer = payerRepository.findOne(payerDto.getId());
            if(payer != null) {
                payer.setAddress(payerDto.getAddress());
                payer.setCode(payerDto.getCode());
                payer.setFullName(payerDto.getFullName());
                payer.setInn(payerDto.getInn());
                payer.setKpp(payerDto.getKpp());
                payer.setName(payerDto.getName());
                payer = payerRepository.save(payer);
                return convertToDto(payer);
            }

            throw new Exception("Payer with id: " + payerDto.getId() + " not found at database.");
        }

        throw new Exception("Payer is null or id is null or code is null.");
    }

    public void delete(UUID id) {
        payerRepository.delete(id);
    }

    private PayerDto convertToDto(Payer payer) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(payer, PayerDto.class);
    }

    private List<PayerDto> convertToDto(List<Payer> payers) {
        return payers.parallelStream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Payer convertToEntity(PayerDto payerDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(payerDto, Payer.class);
    }

    private List<Payer> convertToEntity(List<PayerDto> payerDtos) {
        return payerDtos.parallelStream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
