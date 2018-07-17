package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    private Set<Payer> payers;

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
}
