package org.pes.onecemulator.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "act")
public class Act extends AbstractEntity {

    @Column(name = "accept_date")
    private LocalDate acceptDate;

    @Column(name = "status")
    private String status;

    @Column(name = "number", nullable = false)
    private String number;

    @OneToMany(mappedBy = "act", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    public LocalDate getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Invoice> getInvoices() {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
