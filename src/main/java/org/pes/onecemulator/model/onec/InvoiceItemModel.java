package org.pes.onecemulator.model.onec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceItemModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("content")
    private String content;
}
