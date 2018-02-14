package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class AccountingEntryModel extends ApiError {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("operationCode")
    private String code;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("docName")
    private String documentName;

    @JsonProperty("expenseNumber")
    private String expenseNumber;

    @JsonProperty("sum")
    private String sum;

    public AccountingEntryModel() {
        super();
    }

    public AccountingEntryModel(String error) {
        super(error);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getExpenseNumber() {
        return expenseNumber;
    }

    public void setExpenseNumber(String expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public boolean containsWithIgnoreCase(String text) {
        return code.toLowerCase().contains(text)
                || expenseNumber.toLowerCase().contains(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountingEntryModel)) return false;
        if (!super.equals(o)) return false;
        AccountingEntryModel that = (AccountingEntryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(date, that.date) &&
                Objects.equals(documentName, that.documentName) &&
                Objects.equals(expenseNumber, that.expenseNumber) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, code, date, documentName, expenseNumber, sum);
    }
}
