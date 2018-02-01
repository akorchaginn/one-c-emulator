package org.pes.onecemulator.service;

import org.pes.onecemulator.model.AEntryModel;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryService {

    AEntryModel getById(UUID id);

    List<AEntryModel> list();

    AEntryModel create(AEntryModel model);

    AEntryModel update(AEntryModel model);

    void delete(UUID id);
}
