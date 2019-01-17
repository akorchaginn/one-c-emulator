package org.pes.onecemulator.model.onec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.onec.api.LocalDateDeserializer;
import org.pes.onecemulator.model.onec.api.LocalDateSerializer;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentModel {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty("nom")
    private String number;

    @JsonProperty("NomOQ")
    private String numberOq;

    @JsonProperty("name")
    private String payerName;

    @JsonProperty("sum")
    private String invoiceSum;

    @JsonProperty("Sum_rub")
    private String invoiceSumRUB;

    @JsonProperty("currency_S")
    private String invoiceCurrency;

    @JsonProperty("dataOplat")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    @JsonProperty("sumOplat_currency_P")
    private String paymentSum;

    @JsonProperty("sumOplat")
    private String paymentSumRUB;

    @JsonProperty("currency_P")
    private String paymentCurrency;

    @JsonProperty("date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @JsonProperty("status")
    private String status;

    @JsonProperty(value = "uuid")
    private String externalId;
}
