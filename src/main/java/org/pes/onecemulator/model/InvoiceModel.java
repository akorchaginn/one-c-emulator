package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.pes.onecemulator.model.api.LocalDateDeserializer;
import org.pes.onecemulator.model.api.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class InvoiceModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("source")
    @NotNull
    private String source;

    @JsonProperty("date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate date;

    @JsonProperty("nom")
    @NotNull
    private String number;

    @JsonProperty("NomOQ")
    private String numberOq;

    @JsonProperty("payerCode")
    @NotNull
    private String payerCode;

    @JsonProperty("dataOplat")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate paymentDate;

    @JsonProperty("sumOplat")
    private String paymentSum;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sum")
    private String invoiceSum;

    @JsonProperty("Sum_rub")
    private String invoiceSumRUB;

    @JsonProperty("sumOplat_currency_P")
    private String paymentSumWithCurrencyPayment;

    @JsonProperty("currency_S")
    private String invoiceCurrency;

    @JsonProperty("currency_P")
    private String paymentCurrency;

    @JsonProperty("externalId")
    @NotNull
    private String externalId;

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

    public String getNumberOq() {
        return numberOq;
    }

    public void setNumberOq(String numberOq) {
        this.numberOq = numberOq;
    }

    public String getPayerCode() {
        return payerCode;
    }

    public void setPayerCode(String payerCode) {
        this.payerCode = payerCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceSum() {
        return invoiceSum;
    }

    public void setInvoiceSum(String invoiceSum) {
        this.invoiceSum = invoiceSum;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getInvoiceSumRUB() { return invoiceSumRUB; }

    public void setInvoiceSumRUB(String invoiceSumRUB) { this.invoiceSumRUB = invoiceSumRUB; }

    public String getPaymentSumWithCurrencyPayment() { return paymentSumWithCurrencyPayment; }

    public void setPaymentSumWithCurrencyPayment(String paymentSumWithCurrencyPayment) { this.paymentSumWithCurrencyPayment = paymentSumWithCurrencyPayment; }

    public String getInvoiceCurrency() { return invoiceCurrency; }

    public void setInvoiceCurrency(String invoiceCurrency) { this.invoiceCurrency = invoiceCurrency; }

    public String getPaymentCurrency() { return paymentCurrency; }

    public void setPaymentCurrency(String paymentCurrency) { this.paymentCurrency = paymentCurrency; }

    public boolean containsWithIgnoreCase(String text) {
        return number.toLowerCase().contains(text)
                || externalId.toLowerCase().contains(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceModel)) return false;
        InvoiceModel model = (InvoiceModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(source, model.source) &&
                Objects.equals(date, model.date) &&
                Objects.equals(number, model.number) &&
                Objects.equals(numberOq, model.numberOq) &&
                Objects.equals(payerCode, model.payerCode) &&
                Objects.equals(paymentDate, model.paymentDate) &&
                Objects.equals(paymentSum, model.paymentSum) &&
                Objects.equals(status, model.status) &&
                Objects.equals(invoiceSum, model.invoiceSum) &&
                Objects.equals(externalId, model.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, date, number, numberOq, payerCode, paymentDate, paymentSum, status, invoiceSum, externalId);
    }
}
