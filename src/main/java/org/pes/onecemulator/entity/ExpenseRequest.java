package org.pes.onecemulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expense_request")
public class ExpenseRequest extends AbstractEntity {

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "is_confirm")
    private Boolean isConfirm;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "number",nullable = false)
    private String number;

    @Column(name = "sum", nullable = false)
    private String sum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_id")
    private Source source;

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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
