package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class ExpenseRequestModel extends ApiError {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("source")
    private String source;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("isConfirm")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private Boolean isConfirm;

    @JsonProperty("isPaid")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private Boolean isPaid;

    @JsonProperty("number")
    private String number;

    @JsonProperty("sum")
    private String sum;

    public ExpenseRequestModel() {
        super();
    }

    public ExpenseRequestModel(String error) {
        super(error);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getConfirm() {
        return isConfirm;
    }

    public void setConfirm(Boolean confirm) {
        isConfirm = confirm;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public boolean containsWithIgnoreCase(String text) {
        return number.toLowerCase().contains(text)
                || sum.toLowerCase().contains(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpenseRequestModel)) return false;
        if (!super.equals(o)) return false;
        ExpenseRequestModel model = (ExpenseRequestModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(source, model.source) &&
                Objects.equals(currency, model.currency) &&
                Objects.equals(isConfirm, model.isConfirm) &&
                Objects.equals(isPaid, model.isPaid) &&
                Objects.equals(number, model.number) &&
                Objects.equals(sum, model.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, source, currency, isConfirm, isPaid, number, sum);
    }
}
