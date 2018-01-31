package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayerCrm {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "kod")
    private String code;

    @JsonProperty(value = "nom")
    private String name;

    @JsonProperty(value = "nomP")
    private String fullName;

    @JsonProperty(value = "inn")
    private String inn;

    @JsonProperty(value = "kpp")
    private String kpp;

    @JsonProperty(value = "ad")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
