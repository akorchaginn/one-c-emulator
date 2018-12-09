package org.pes.onecemulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "fiz_id")
    private String fizId;

    @Column(name = "position")
    private String position;

    @Column(name = "unit")
    private String unit;

    @Column(name = "period")
    private String period;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<EmployeeSource> employeeSources;

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

    public String getFizId() {
        return fizId;
    }

    public void setFizId(String fizId) {
        this.fizId = fizId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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
