package org.pes.onecemulator.model.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.api.LocalDateDeserializer;
import org.pes.onecemulator.model.api.LocalDateSerializer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeModel {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty(value = "externalId")
    private String externalId;

    @JsonProperty(value = "fullName")
    private String fullName;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonProperty(value = "birthday")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @JsonProperty(value = "fizId")
    private String fizId;

    @JsonProperty(value = "position")
    private String position;

    @JsonProperty(value = "unit")
    private String unit;

    @JsonProperty(value = "period")
    private String period;

    @JsonProperty("source")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @Setter(value = AccessLevel.NONE)
    private Set<EmployeeSourceModel> employeeSources = new HashSet<>();

    public boolean containsWithIgnoreCase(String text) {
        return externalId.toLowerCase().contains(text)
                || fizId.toLowerCase().contains(text)
                || fullName.toLowerCase().contains(text);
    }
}
