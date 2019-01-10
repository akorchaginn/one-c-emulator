package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

public class ExpenseRequestModelConverter implements Converter<ExpenseRequest, ExpenseRequestModel> {

    @Override
    public ExpenseRequestModel convert(final ExpenseRequest entity) {
        final ExpenseRequestModel model = new ExpenseRequestModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setCurrency(entity.getCurrency());
        model.setConfirm(Boolean.TRUE.equals(entity.getConfirm()));
        model.setPaid(Boolean.TRUE.equals(entity.getPaid()));
        model.setNumber(entity.getNumber());
        model.setSum(entity.getSum());

        return model;
    }
}
