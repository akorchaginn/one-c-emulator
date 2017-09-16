package org.pes.onecemulator.service.api.exception;

public class CreateEntityException extends ApiException {

    private int code;

    public CreateEntityException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
