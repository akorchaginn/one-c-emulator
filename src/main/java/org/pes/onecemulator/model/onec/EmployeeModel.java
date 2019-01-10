package org.pes.onecemulator.model.onec;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.pes.onecemulator.model.api.LocalDateDeserializer;
import org.pes.onecemulator.model.api.LocalDateSerializer;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class EmployeeModel {

    @JsonProperty(value = "id")
    private String externalId;

    @JsonProperty(value = "name")
    private String fullName;

    @JsonProperty(value = "sex")
    private String gender;

    @JsonProperty(value = "birthday")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @JsonProperty(value = "work_begin")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonProperty(value = "dismiss_date")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
