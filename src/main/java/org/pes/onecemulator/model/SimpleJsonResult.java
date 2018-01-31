package org.pes.onecemulator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleJsonResult {

    @JsonProperty("result")
    private Object result;

    @JsonProperty("message")
    private String message;

    public SimpleJsonResult (Object result) {
        this.result = result;
        this.message = null;
    }

    public SimpleJsonResult (String error) {
        this.result = null;
        this.message = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
