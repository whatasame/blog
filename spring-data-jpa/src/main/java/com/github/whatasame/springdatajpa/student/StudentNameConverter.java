package com.github.whatasame.springdatajpa.student;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StudentNameConverter implements AttributeConverter<StudentName, String> {

    @Override
    public String convertToDatabaseColumn(final StudentName name) {
        return name.getName();
    }

    @Override
    public StudentName convertToEntityAttribute(final String dbData) {
        return new StudentName(dbData);
    }
}
