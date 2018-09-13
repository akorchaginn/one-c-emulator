package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeCrm {

    @JsonProperty(value = "id")
    private String externalId;

    @JsonProperty(value = "name")
    private String fullName;

    @JsonProperty(value = "sex")
    private String gender;

    @JsonProperty(value = "birthday")
    private LocalDate birthday;

    @JsonProperty(value = "work_begin")
    private LocalDate startDate;

    @JsonProperty(value = "dismiss_date")
    private LocalDate endDate;

    @JsonProperty(value = "id_fiz")
    private String fizId;

    @JsonProperty(value = "position")
    private String position;

    @JsonProperty(value = "unit")
    private String unit;

    @JsonProperty(value = "Period")
    private String period;
}
