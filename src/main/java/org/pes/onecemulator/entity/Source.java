package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "source")
public class Source extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PayerSource> payerSources;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeSource> employeeSources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PayerSource> getPayerSources() {
        if (payerSources == null) {
            payerSources = new ArrayList<>();
        }
        return payerSources;
    }

    public void setPayerSources(List<PayerSource> payerSources) {
        this.payerSources = payerSources;
    }

    public List<EmployeeSource> getEmployeeSources() {
        if (employeeSources == null) {
            employeeSources = new ArrayList<>();
        }
        return employeeSources;
    }

    public void setEmployeeSources(List<EmployeeSource> employeeSources) {
        this.employeeSources = employeeSources;
    }
}
