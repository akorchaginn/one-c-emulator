package org.pes.onecemulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "accounting_entry")
public class AccountingEntry extends AbstractEntity {

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "sum")
    private BigDecimal sum;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_request_id")
    private ExpenseRequest expenseRequest;

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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public ExpenseRequest getExpenseRequest() {
        return expenseRequest;
    }

    public void setExpenseRequest(ExpenseRequest expenseRequest) {
        this.expenseRequest = expenseRequest;
    }
}
