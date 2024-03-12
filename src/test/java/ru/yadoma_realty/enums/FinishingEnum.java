package ru.yadoma_realty.enums;

import lombok.Getter;

@Getter
public enum FinishingEnum {
    WITHOUT(1, "Без отделки"), CLEAR(2, "Чистовая"), BEFORECLEAR(3, "Предчистовая"),
    FURNITURE(4, "С мебелью"), REFURBISHED(5, "С ремонтом"), WITHOUTWALLS(6, "Без стен");

    private final int value;
    private final String name;

    FinishingEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
