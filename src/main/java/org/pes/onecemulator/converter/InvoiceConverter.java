package org.pes.onecemulator.converter;

import org.apache.commons.lang3.StringUtils;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.service.PayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceConverter implements Converter<InvoiceDto, InvoiceModel> {

    @Autowired
    private PayerService payerService;

    @Override
    public InvoiceModel convertToModel(InvoiceDto dto) {
        InvoiceModel model = new InvoiceModel();
        model.setId(dto.getId());
        model.setDate(dto.getDate());
        model.setExternalId(dto.getExternalId());
        model.setNumber(dto.getNumber());
        model.setNumberOq(dto.getNumberOq());
        model.setPayerCode(dto.getPayer() != null ? dto.getPayer().getCode() : StringUtils.EMPTY);
        model.setPaymentDate(dto.getPaymentDate());
        model.setPaymentSum(dto.getPaymentSum());
        model.setSource(dto.getSource());
        model.setStatus(dto.getStatus());
        model.setSum(dto.getSum());

        return model;
    }

    @Override
    public List<InvoiceModel> convertToModel(List<InvoiceDto> invoiceDtos) {
        return invoiceDtos.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public InvoiceDto convertToDto(InvoiceModel model) throws Exception {
        InvoiceDto dto = new InvoiceDto();
        dto.setId(model.getId());
        dto.setDate(model.getDate());
        dto.setExternalId(model.getExternalId());
        dto.setNumber(model.getNumber());
        dto.setNumberOq(model.getNumberOq());
        dto.setPayer(model.getPayerCode() != null ? payerService.getByCode(model.getPayerCode()) : null);
        dto.setPaymentDate(model.getPaymentDate());
        dto.setPaymentSum(model.getPaymentSum());
        dto.setSource(model.getSource());
        dto.setStatus(model.getStatus());
        dto.setSum(model.getSum());

        return dto;
    }

    @Override
    public List<InvoiceDto> convertToDto(List<InvoiceModel> invoiceModels) {
        return null;
    }
}
