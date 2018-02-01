package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PayerModel extends ApiError {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("code")
    private String code;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("kpp")
    private String kpp;

    @JsonProperty("name")
    private String name;

    @JsonProperty("source")
    private Set<String> source = new HashSet<>();

    public PayerModel() {
        super();
    }

    public PayerModel(String error) {
        super(error);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Set<String> getSource() {
        return source;
    }

    public void setSource(Set<String> source) {
        this.source = source;
    }
}
