package org.pes.onecemulator.facade;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceFacade {

    List<SourceModel> list();

    List<String> listNames();

    SourceModel create(SourceModel sourceModel) throws ValidationException;

    SourceModel update(SourceModel sourceModel) throws ValidationException, NotFoundException;

    void delete(final UUID id);
}
