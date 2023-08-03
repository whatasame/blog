package com.github.whatasame.springdatajpa.attributeconverter.converter;

import com.github.whatasame.springdatajpa.attributeconverter.entity.MemberName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MemberNameConverter implements AttributeConverter<MemberName, String> {

    @Override
    public String convertToDatabaseColumn(final MemberName attribute) {
        return attribute.getValue();
    }

    @Override
    public MemberName convertToEntityAttribute(final String dbData) {
        return new MemberName(dbData);
    }
}
