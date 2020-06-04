package org.pes.onecemulator.model.onec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.internal.EmployeeSourceModel;
import org.pes.onecemulator.model.internal.InvoiceModel;
import org.pes.onecemulator.model.onec.api.LocalDateDeserializer;
import org.pes.onecemulator.model.onec.api.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentModel {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty("nom")
    private String number;

    @JsonProperty("name")
    private String payerName;

    @JsonProperty("sum")
    private String invoiceSum;

    @JsonProperty("currency_S")
    private String invoiceCurrency;

    @JsonProperty("dataOplat")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate paymentDate;

    @JsonProperty("sumOplat_currency_P")
    private String paymentSum;

    @JsonProperty("sumOplat")
    private String paymentSumInvoiceCurrency;

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

    @JsonProperty("goods")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Set<InvoiceItemModel> invoiceItems = new HashSet<>();

    @JsonProperty("Act_id")
    private UUID actId;

    @JsonProperty("Act_status")
    private String actStatus;

    @JsonProperty("Act_date_accept")
    @JsonSerialize(using = org.pes.onecemulator.model.internal.api.LocalDateSerializer.class)
    @JsonDeserialize(using = org.pes.onecemulator.model.internal.api.LocalDateDeserializer.class)
    @NotNull
    private LocalDate actAcceptDate;

    @JsonProperty("Act_nom")
    @NotNull
    private String actNumber;

    @JsonProperty("invoices")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Set<InvoiceModel> Invoices = new HashSet<>();
}
