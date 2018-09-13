package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private LocalDate birthday;

    @JsonProperty(value = "startDate")
    private LocalDate startDate;

    @JsonProperty(value = "endDate")
    private LocalDate endDate;

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
    private Set<String> sources = new HashSet<>();

    public boolean containsWithIgnoreCase(String text) {
        return externalId.toLowerCase().contains(text)
                || fizId.toLowerCase().contains(text)
                || fullName.toLowerCase().contains(text)
                || sources.stream().anyMatch(s -> s.toLowerCase().contains(text));
    }
}
