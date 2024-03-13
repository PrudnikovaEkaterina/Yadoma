package ru.yadoma_realty.dataBase.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.yadoma_realty.enums.UserRoles;



@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<UserRoles, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRoles value) {
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
    public UserRoles convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        return switch (dbData) {
            case 1 -> UserRoles.USER;
            case 2 -> UserRoles.MANAGER;
            case 99 -> UserRoles.ADMIN;
            default -> throw new IllegalArgumentException(dbData + " not supported.");
        };
    }

}

