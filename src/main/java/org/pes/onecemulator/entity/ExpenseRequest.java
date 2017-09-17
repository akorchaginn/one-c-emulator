package org.pes.onecemulator.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expense_request")
public class ExpenseRequest extends AbstractObject {

    @Column(name = "currency")
    private String currency;

    @Column(name = "is_confirm")
    private Boolean isConfirm;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "sum")
    private BigDecimal sum;

    @OneToMany(mappedBy = "expenseRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AccountingEntry> accountingEntries = new HashSet<>();

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

    public Set<AccountingEntry> getAccountingEntries() {
        return accountingEntries;
    }

    public void setAccountingEntries(Set<AccountingEntry> accountingEntries) {
        this.accountingEntries = accountingEntries;
    }
}
