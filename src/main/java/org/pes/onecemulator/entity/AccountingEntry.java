package org.pes.onecemulator.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "accounting_entry")
public class AccountingEntry extends AbstractObject {

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "document_name")
    private String documentName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_request")
    private ExpenseRequest expenseRequest;

    @Column(name = "sum")
    private BigDecimal sum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public ExpenseRequest getExpenseRequest() {
        return expenseRequest;
    }

    public void setExpenseRequest(ExpenseRequest expenseRequest) {
        this.expenseRequest = expenseRequest;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
