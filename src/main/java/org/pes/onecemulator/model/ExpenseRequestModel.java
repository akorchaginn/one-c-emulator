package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private String source;

    @JsonProperty("currency")
    @NotNull
    private String currency;

    @JsonProperty("isConfirm")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    private boolean isConfirm;

    @JsonProperty("isPaid")
    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    @NotNull
    private boolean isPaid;

    @JsonProperty("number")
    @NotNull
    private String number;

    @JsonProperty("sum")
    @NotNull
    private String sum;

    public boolean containsWithIgnoreCase(String text) {
        return number.toLowerCase().contains(text)
                || sum.toLowerCase().contains(text);
    }
}
