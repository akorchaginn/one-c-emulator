package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "source",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = "name",
                        name = "uk_source_name"
                )
        }
)
public class Source extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "sources")
    private Set<Payer> payers = new HashSet<>();

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExpenseRequest> expenseRequests = new HashSet<>();

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Invoice> invoices = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Payer> getPayers() {
        return payers;
    }

    public void setPayers(Set<Payer> payers) {
        this.payers = payers;
    }

    public Set<ExpenseRequest> getExpenseRequests() {
        return expenseRequests;
    }

    public void setExpenseRequests(Set<ExpenseRequest> expenseRequests) {
        this.expenseRequests = expenseRequests;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }
}
