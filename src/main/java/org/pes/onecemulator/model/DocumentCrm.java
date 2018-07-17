package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.pes.onecemulator.model.api.LocalDateDeserializer;
import org.pes.onecemulator.model.api.LocalDateSerializer;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentCrm {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty("nom")
    private String number;

    @JsonProperty("NomOQ")
    private String numberOq;

    @JsonProperty("name")
    private String payerName;

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

    @JsonProperty("date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("dataOplat")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    @JsonProperty("sumOplat")
    private String paymentSum;

    @JsonProperty(value = "uuid")
    private String externalId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getInvoiceSum() {
        return invoiceSum;
    }

    public void setInvoiceSum(String invoiceSum) {
        this.invoiceSum = invoiceSum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
