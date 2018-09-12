package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity {

    @Column(name = "external_id", nullable = false)
    private String externalId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "fiz_id")
    private String fizId;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "employee_source",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "source_id") }
    )
    private Set<Source> sources;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getFizId() {
        return fizId;
    }

    public void setFizId(String fizId) {
        this.fizId = fizId;
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
