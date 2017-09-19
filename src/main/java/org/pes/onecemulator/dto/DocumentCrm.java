package org.pes.onecemulator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Calendar;

public class DocumentCrm {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty("nom")
    private String number;

    @JsonProperty("NomOQ")
    private String numberOq;

    @JsonProperty("name")
    private String payerName;

    @JsonProperty("sum")
    private BigDecimal sum;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("dataOplat")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Calendar paymentDate;

    @JsonProperty("sumOplat")
    private BigDecimal paymentSum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
