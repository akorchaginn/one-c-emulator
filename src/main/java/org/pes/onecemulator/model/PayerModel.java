package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class PayerModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("code")
    @NotEmpty
    private String code;

    @JsonProperty("name")
    @NotEmpty
    private String name;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("kpp")
    private String kpp;

    @JsonProperty("address")
    private String address;

    @JsonProperty("source")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @NotEmpty
    private Set<String> sources = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<String> getSources() {
        return sources;
    }

    public void setSources(Set<String> source) {
        this.sources = source;
    }

    public boolean containsWithIgnoreCase(String text) {
        return code.toLowerCase().contains(text)
                || name.toLowerCase().contains(text)
                || sources.stream().anyMatch(s -> s.toLowerCase().contains(text));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayerModel)) return false;
        PayerModel model = (PayerModel) o;
        return Objects.equals(id, model.id) &&
                Objects.equals(code, model.code) &&
                Objects.equals(name, model.name) &&
                Objects.equals(fullName, model.fullName) &&
                Objects.equals(inn, model.inn) &&
                Objects.equals(kpp, model.kpp) &&
                Objects.equals(address, model.address) &&
                Objects.equals(sources, model.sources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, fullName, inn, kpp, address, sources);
    }
}
