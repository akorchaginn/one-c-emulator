package org.pes.onecemulator.converter;

import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.model.ERequestModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseRequestConverter implements Converter<ExpenseRequestDto, ERequestModel> {

    @Override
    public ERequestModel convertToModel(ExpenseRequestDto dto) {
        ERequestModel model = new ERequestModel();
        model.setId(dto.getId());
        model.setConfirm(dto.getConfirm());
        model.setCurrency(dto.getCurrency());
        model.setNumber(dto.getNumber());
        model.setPaid(dto.getPaid());
        model.setSource(dto.getSource());
        model.setSum(dto.getSum());

        return model;
    }

    @Override
    public List<ERequestModel> convertToModel(List<ExpenseRequestDto> expenseRequestDtos) {
        return expenseRequestDtos.stream().map(this::convertToModel).collect(Collectors.toList());
    }


    @Override
    public ExpenseRequestDto convertToDto(ERequestModel model) {
        ExpenseRequestDto dto = new ExpenseRequestDto();
        dto.setId(model.getId());
        dto.setConfirm(model.getConfirm());
        dto.setCurrency(model.getCurrency());
        dto.setNumber(model.getNumber());
        dto.setPaid(model.getPaid());
        dto.setSource(model.getSource());
        dto.setSum(model.getSum());

        return dto;
    }

    @Override
    public List<ExpenseRequestDto> convertToDto(List<ERequestModel> eRequestModels) {
        return null;
    }
}
