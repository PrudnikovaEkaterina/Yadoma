package ru.yadoma_realty.enums;

import lombok.Getter;

@Getter
public enum FlatStatus {
    INCOMPLETE(0), PUBLISHED(1), UNPUBLISHED(2), REJECTED(3), ERROR(4);

    /*
     * В процессе создания,  Опубликовано, Не опубликовано (устарело, в архиве), Отклонено, Некорректно;
    */

    private final int value;

    FlatStatus(int value) {
        this.value = value;
    }
}
