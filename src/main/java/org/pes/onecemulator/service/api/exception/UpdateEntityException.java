package org.pes.onecemulator.service.api.exception;

public class UpdateEntityException extends ApiException {

    private int code;

    public UpdateEntityException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
