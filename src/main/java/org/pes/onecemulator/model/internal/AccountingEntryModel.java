package org.pes.onecemulator.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.internal.api.LocalDateDeserializer;
import org.pes.onecemulator.model.internal.api.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class AccountingEntryModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("operationCode")
    @NotNull
    @NotEmpty
    private String code;

    @JsonProperty("date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate date;

    @JsonProperty("docName")
    @NotNull
    @NotEmpty
    private String documentName;

    @JsonProperty("expenseNumber")
    @NotNull
    @NotEmpty
    private String expenseNumber;

    @JsonProperty("sum")
    @NotNull
    @NotEmpty
    private String sum;

    public boolean containsWithIgnoreCase(String text) {
        return code.toLowerCase().contains(text)
                || expenseNumber.toLowerCase().contains(text);
    }
}
