package org.pes.onecemulator.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    @JsonProperty("error")
    private String error;

    @JsonProperty("cause")
    private String cause;

    ApiError(Exception e) {
        this.error = e.getLocalizedMessage();
        this.cause = e.getCause().getLocalizedMessage();
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        return cause;
    }
}
