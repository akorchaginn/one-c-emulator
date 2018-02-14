package org.pes.onecemulator.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import java.util.UUID;

public class SourceModel extends ApiError {

    private UUID id;

    @NotEmpty
    private String name;

    public SourceModel() {}

    public SourceModel(String error) {
        super(error);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean containsWithIgnoreCase(String text) {
        return name.toLowerCase().contains(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SourceModel)) return false;
        if (!super.equals(o)) return false;
        SourceModel that = (SourceModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name);
    }
}
