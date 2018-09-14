package org.pes.onecemulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "accounting_entry")
public class AccountingEntry extends AbstractEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "sum", nullable = false)
    private String sum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "expense_request_id")
    private ExpenseRequest expenseRequest;

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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public ExpenseRequest getExpenseRequest() {
        return expenseRequest;
    }

    public void setExpenseRequest(ExpenseRequest expenseRequest) {
        this.expenseRequest = expenseRequest;
    }
}
