package org.pes.onecemulator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Calendar;

public class InvoiceDto extends AbstractObjectDto {

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar date;

    @JsonProperty("number")
    private String number;

    @JsonProperty("number_oq")
    private String numberOq;

    @JsonProperty("payer_code")
    private String payerCode;

    @JsonIgnore
    private PayerDto payer;

    @JsonProperty("payment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar paymentDate;

    @JsonProperty("payment_sum")
    private BigDecimal paymentSum;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sum")
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

    public PayerDto getPayer() {
        return payer;
    }

    public void setPayer(PayerDto payer) {
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
