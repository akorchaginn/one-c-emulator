package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestModelConverter implements Converter<ExpenseRequest, ExpenseRequestModel> {

    @Override
    public ExpenseRequestModel convert(ExpenseRequest entity) {
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
