package org.pes.onecemulator.mapping;

import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.entity.AccountingEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperFactoryService {

    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public AccountingEntryDto convertToDto(AccountingEntry accountingEntry) {
        return getEntityToDtoMapper().map(accountingEntry, AccountingEntryDto.class);
    }

    public List<AccountingEntryDto> convertToDto(List<AccountingEntry> accountingEntry) {
        return accountingEntry.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AccountingEntry convertToEntity(AccountingEntryDto accountingEntryDto) {
        return getDtoToEntityMapper().map(accountingEntryDto, AccountingEntry.class);
    }

    public List<AccountingEntry> convertToEntity(List<AccountingEntryDto> accountingEntryDto) {
        return accountingEntryDto.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    private ModelMapper getEntityToDtoMapper(){
        final ModelMapper modelMapper = new ModelMapper();
        /*modelMapper.addMappings(new PropertyMap<AccountingEntry, AccountingEntryDto>() {
            @Override
            protected void configure() {
                map().setExpenseRequest(String.valueOf(source.getExpenseRequest().getNumber()));
            }
        });*/

        return modelMapper;
    }

    private ModelMapper getDtoToEntityMapper(){
        final ModelMapper modelMapper = new ModelMapper();
        /*modelMapper.addMappings(new PropertyMap<AccountingEntryDto, AccountingEntry>() {
            @Override
            protected void configure() {
                map().setExpenseRequest(expenseRequestRepositoryService.findByNumber(source.getExpenseRequest()));
            }
        });*/

        return modelMapper;
    }
}
