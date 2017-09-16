package org.pes.onecemulator.service.api.exception;

public class NotFoundEntityException extends ApiException {

    private int code;

    public NotFoundEntityException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
