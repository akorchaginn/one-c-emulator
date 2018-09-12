package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payer")
public class Payer extends AbstractEntity {

    @Column(name = "address")
    private String address;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "inn")
    private String inn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "payer_source",
            joinColumns = { @JoinColumn(name = "payer_id") },
            inverseJoinColumns = { @JoinColumn(name = "source_id") }
    )
    private Set<Source> sources;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Source> getSources() {
        if (sources == null) {
            sources = new HashSet<>();
        }
        return sources;
    }

    public void setSources(Set<Source> sources) {
        this.sources = sources;
    }
}
