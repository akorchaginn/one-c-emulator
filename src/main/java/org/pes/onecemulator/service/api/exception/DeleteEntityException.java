package org.pes.onecemulator.service.api.exception;

public class DeleteEntityException extends ApiException {

    private int code;

    public DeleteEntityException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
