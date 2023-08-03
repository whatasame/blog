package com.github.whatasame.springdatajpa.attributeconverter.converter;

import com.github.whatasame.springdatajpa.attributeconverter.entity.MemberAge;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MemberAgeConverter implements AttributeConverter<MemberAge, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final MemberAge attribute) {
        return attribute.getValue();
    }

    @Override
    public MemberAge convertToEntityAttribute(final Integer dbData) {
        return new MemberAge(dbData);
    }
}
