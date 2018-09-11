package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
