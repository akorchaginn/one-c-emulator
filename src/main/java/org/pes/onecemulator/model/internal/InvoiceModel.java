package org.pes.onecemulator.model.internal;

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
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class InvoiceModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("source")
    @NotNull
    private String source;

    @JsonProperty("date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate date;

    @JsonProperty("nom")
    @NotNull
    private String number;

    @JsonProperty("NomOQ")
    private String numberOq;

    @JsonProperty("payerCode")
    @NotNull
    private String payerCode;

    @JsonProperty("dataOplat")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull
    private LocalDate paymentDate;

    @JsonProperty("sumOplat")
    private String paymentSumRUB;

    @JsonProperty("sumOplat_currency_P")
    private String paymentSum;

    @JsonProperty("currency_P")
    private String paymentCurrency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("Sum_rub")
    private String sumRUB;

    @JsonProperty("currency_S")
    private String currency;

    @JsonProperty("externalId")
    @NotNull
    private String externalId;

    public boolean containsWithIgnoreCase(String text) {
        return number.toLowerCase().contains(text)
                || externalId.toLowerCase().contains(text);
    }
}
