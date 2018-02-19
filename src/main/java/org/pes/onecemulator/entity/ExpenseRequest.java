package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "expense_request")
public class ExpenseRequest extends AbstractEntity {

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "is_confirm")
    private Boolean isConfirm;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_id")
    private Source source;

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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Set<AccountingEntry> getAccountingEntries() {
        return accountingEntries;
    }

    public void setAccountingEntries(Set<AccountingEntry> accountingEntries) {
        this.accountingEntries = accountingEntries;
    }
}
