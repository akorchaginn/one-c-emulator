package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class ExpenseRequestModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("source")
    @NotNull
    private String source;

    @JsonProperty("currency")
    @NotNull
    private String currency;

    @JsonProperty("isConfirm")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    private Boolean isConfirm;

    @JsonProperty("isPaid")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    private Boolean isPaid;

    @JsonProperty("number")
    @NotNull
    private String number;

    @JsonProperty("sum")
    @NotNull
    private String sum;

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
        ExpenseRequestModel that = (ExpenseRequestModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(source, that.source) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(isConfirm, that.isConfirm) &&
                Objects.equals(isPaid, that.isPaid) &&
                Objects.equals(number, that.number) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, currency, isConfirm, isPaid, number, sum);
    }
}
