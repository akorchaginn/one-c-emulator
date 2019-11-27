package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice extends AbstractEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_sum")
    private String paymentSum;

    @Column(name = "payment_sum_invoice_currency")
    private String paymentSumInvoiceCurrency;

    @Column(name = "payment_currency")
    private String paymentCurrency;

    @Column(name = "status")
    private String status;

    @Column(name = "sum")
    private String sum;

    @Column(name = "currency")
    private String currency;

    @Column(name = "external_id", nullable = false)
    private String externalId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

    @OneToMany(mappedBy = "invoice", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InvoiceItem> items;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getPaymentSumInvoiceCurrency() {
        return paymentSumInvoiceCurrency;
    }

    public void setPaymentSumInvoiceCurrency(String paymentSumInvoiceCurrency) {
        this.paymentSumInvoiceCurrency = paymentSumInvoiceCurrency;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public List<InvoiceItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void addItem(InvoiceItem item) {
        this.items.add(item);
    }

}
