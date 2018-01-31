package org.pes.onecemulator.converter;

import java.util.List;

public interface Converter<DTO, MODEL> {

    MODEL convertToModel(DTO dto);

    List<MODEL> convertToModel(List<DTO> dtoList);

    DTO convertToDto(MODEL model) throws Exception;

    List<DTO> convertToDto(List<MODEL> modelList);
}
