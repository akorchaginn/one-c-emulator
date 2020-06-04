package org.pes.onecemulator.model.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.internal.api.LocalDateDeserializer;
import org.pes.onecemulator.model.internal.api.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ActModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("acceptDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate acceptDate;

    @JsonProperty("nom")
    @NotNull
    private String number;

    @JsonProperty("invoices")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Set<UUID> invoiceIds = new HashSet<>();
}
