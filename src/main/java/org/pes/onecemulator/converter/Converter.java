package org.pes.onecemulator.converter;

public interface Converter<I, O> {

    O convert(I input);
}
