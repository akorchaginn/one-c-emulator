package org.pes.onecemulator.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class InvoiceItemModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("content")
    private String content;
}
