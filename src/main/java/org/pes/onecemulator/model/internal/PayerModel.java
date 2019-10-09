package org.pes.onecemulator.model.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class PayerModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("inn")
    private String inn;

    @JsonProperty("kpp")
    private String kpp;

    @JsonProperty("address")
    private String address;

    @JsonProperty("customer")
    private boolean customer;

    @JsonProperty("subcontractor")
    private boolean subcontractor;

    @JsonProperty("source")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Setter(value = AccessLevel.NONE)
    private Set<String> sources = new HashSet<>();

    public boolean containsWithIgnoreCase(String text) {
        return code.toLowerCase().contains(text)
                || name.toLowerCase().contains(text)
                || sources.stream().anyMatch(s -> s.toLowerCase().contains(text));
    }
}
