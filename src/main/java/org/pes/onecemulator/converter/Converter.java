package org.pes.onecemulator.converter;

interface Converter<I, O> {

    O convert(I input);
}
