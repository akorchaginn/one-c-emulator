package org.pes.onecemulator.converter;

import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.model.AEntryModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountingEntryConverter implements Converter<AccountingEntryDto, AEntryModel> {

    @Override
    public AEntryModel convertToModel(AccountingEntryDto dto) {
        AEntryModel model = new AEntryModel();
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setDate(dto.getDate());
        model.setDocumentName(dto.getDocumentName());
        model.setExpenseNumber(dto.getExpenseNumber());
        model.setExpenseRequest(dto.getExpenseRequest());
        model.setSum(dto.getSum());

        return model;
    }

    @Override
    public List<AEntryModel> convertToModel(List<AccountingEntryDto> accountingEntryDtos) {
        return accountingEntryDtos.stream().map(this::convertToModel).collect(Collectors.toList());
    }


    @Override
    public AccountingEntryDto convertToDto(AEntryModel model) {
        AccountingEntryDto dto = new AccountingEntryDto();
        dto.setId(model.getId());
        dto.setCode(model.getCode());
        dto.setDate(model.getDate());
        dto.setDocumentName(model.getDocumentName());
        dto.setExpenseNumber(model.getExpenseNumber());
        dto.setExpenseRequest(model.getExpenseRequest());
        dto.setSum(model.getSum());

        return dto;
    }

    @Override
    public List<AccountingEntryDto> convertToDto(List<AEntryModel> aEntryModels) {
        return null;
    }
}
