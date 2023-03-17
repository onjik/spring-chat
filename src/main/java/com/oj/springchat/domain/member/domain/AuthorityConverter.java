package com.oj.springchat.domain.member.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorityConverter implements AttributeConverter<Authority,String> {
    @Override
    public String convertToDatabaseColumn(Authority attribute) {
        return attribute.name();
    }

    @Override
    public Authority convertToEntityAttribute(String dbData) {
        return Authority.valueOf(dbData);
    }
}
