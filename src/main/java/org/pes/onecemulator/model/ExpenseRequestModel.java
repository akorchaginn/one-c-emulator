package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class ExpenseRequestModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("source")
    @NotNull
    @NotEmpty
    private String source;

    @JsonProperty("currency")
    @NotNull
    @NotEmpty
    private String currency;

    @JsonProperty("isConfirm")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    @NotEmpty
    private boolean isConfirm;

    @JsonProperty("isPaid")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    private boolean isPaid;

    @JsonProperty("number")
    @NotNull
    @NotEmpty
    private String number;

    @JsonProperty("sum")
    @NotNull
    @NotEmpty
    private String sum;

    public boolean containsWithIgnoreCase(String text) {
        return number.toLowerCase().contains(text)
                || sum.toLowerCase().contains(text);
    }
}
