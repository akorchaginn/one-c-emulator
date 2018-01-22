package org.pes.onecemulator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ExpenseRequestDto extends AbstractObjectDto {

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
    private BigDecimal sum;

    @JsonIgnore
    private Set<AccountingEntryDto> accountingEntries = new HashSet<>();

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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Set<AccountingEntryDto> getAccountingEntries() {
        return accountingEntries;
    }

    public void setAccountingEntries(Set<AccountingEntryDto> accountingEntries) {
        this.accountingEntries = accountingEntries;
    }
}
