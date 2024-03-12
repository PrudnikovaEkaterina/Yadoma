package ru.yadoma_realty.dataBase.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.yadoma_realty.enums.RoleEnum;



@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<RoleEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleEnum value) {
        if (value == null)
            return null;

        return switch (value) {
            case USER -> 1;
            case MANAGER -> 2;
            case ADMIN -> 99;
            default -> throw new IllegalArgumentException(value + " not supported.");
        };

    }

    @Override
    public RoleEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return switch (dbData) {
            case 1 -> RoleEnum.USER;
            case 2 -> RoleEnum.MANAGER;
            case 99 -> RoleEnum.ADMIN;
            default -> throw new IllegalArgumentException(dbData + " not supported.");
        };
    }

}

