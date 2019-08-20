package org.pes.onecemulator.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.pes.onecemulator.exception.util.ExceptionUtils;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    @JsonProperty("error")
    private String error;

    ApiError(final Throwable exception) {
        this.error = ExceptionUtils.getCause(exception).getMessage();
    }
}
