package org.pes.onecemulator.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "invoice")
public class Invoice extends AbstractObject {

    @Column(name = "date")
    private Calendar date;

    @Column(name = "number")
    private String number;

    @Column(name = "number_oq")
    private String number_oq;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;

    @Column(name = "payment_date")
    private Calendar paymentDate;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    @Column(name = "status")
    private String status;

    @Column(name = "sum")
    private BigDecimal sum;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber_oq() {
        return number_oq;
    }

    public void setNumber_oq(String number_oq) {
        this.number_oq = number_oq;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Calendar getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Calendar paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(BigDecimal paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
