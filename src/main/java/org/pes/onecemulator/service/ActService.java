package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Act;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ActModel;

import java.util.List;
import java.util.UUID;

public interface ActService {

    Act getById(UUID id) throws NotFoundException;

    List<Act> list();

    Act create(ActModel model) throws NotFoundException, ValidationException;

    Act update(ActModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
