package ru.yadoma_realty.enums;

import lombok.Getter;

@Getter
public enum EntityType {
    BUILDING(1), FLAT(2), USER(3);

    private final int value;

    EntityType(int value) {
        this.value = value;
    }
}
