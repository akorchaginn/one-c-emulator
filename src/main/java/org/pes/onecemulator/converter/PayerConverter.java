package org.pes.onecemulator.converter;

import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.model.PayerModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PayerConverter implements Converter<PayerDto, PayerModel> {

    @Override
    public PayerModel convertToModel(PayerDto dto) {
        PayerModel model = new PayerModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCode(dto.getCode());
        model.setFullName(dto.getFullName());
        model.setAddress(dto.getAddress());
        model.setInn(dto.getInn());
        model.setKpp(dto.getKpp());

        return model;
    }

    @Override
    public List<PayerModel> convertToModel(List<PayerDto> payerDtos) {
        return payerDtos.stream().map(this::convertToModel).collect(Collectors.toList());
    }


    @Override
    public PayerDto convertToDto(PayerModel model) {
        PayerDto dto = new PayerDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCode(model.getCode());
        dto.setFullName(model.getFullName());
        dto.setAddress(model.getAddress());
        dto.setInn(model.getInn());
        dto.setKpp(model.getKpp());

        return dto;
    }

    @Override
    public List<PayerDto> convertToDto(List<PayerModel> modelList) {
        return modelList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
