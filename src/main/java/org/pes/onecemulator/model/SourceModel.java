package org.pes.onecemulator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class SourceModel {

    private UUID id;

    private String name;

    public boolean containsWithIgnoreCase(String text) {
        return name.toLowerCase().contains(text);
    }
}
