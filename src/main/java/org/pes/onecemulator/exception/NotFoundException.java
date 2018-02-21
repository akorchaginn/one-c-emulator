package org.pes.onecemulator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    public NotFoundException(Class clazz, UUID id) {
        super(clazz.getSimpleName() + " with id: " + id + " not found at database.");
    }

    public NotFoundException(Class clazz, String str) {
        super(clazz.getSimpleName() + " with " + str + " not found at database.");
    }
}
