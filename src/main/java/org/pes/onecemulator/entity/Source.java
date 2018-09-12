package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "source")
public class Source extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "sources", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payer> payers;

    @ManyToMany(mappedBy = "sources", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Payer> getPayers() {
        if (payers == null) {
            payers = new HashSet<>();
        }
        return payers;
    }

    public void setPayers(Set<Payer> payers) {
        this.payers = payers;
    }

    public Set<Employee> getEmployees() {
        if (employees == null) {
            employees = new HashSet<>();
        }
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
