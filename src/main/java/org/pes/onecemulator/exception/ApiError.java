package org.pes.onecemulator.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.exception.ExceptionUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    @JsonProperty("error")
    private String error;

    @JsonProperty("cause")
    private String cause;

    ApiError(Exception e) {
        this.error = ExceptionUtils.getMessage(e);
        this.cause =
                !ExceptionUtils.getMessage(e).equals(ExceptionUtils.getRootCauseMessage(e))
                        ? ExceptionUtils.getRootCauseMessage(e)
                        : null;
    }

    public String getError() {
        return error;
    }

    public String getCause() {
        return cause;
    }
}
