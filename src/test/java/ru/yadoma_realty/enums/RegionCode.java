package ru.yadoma_realty.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum RegionCode {
     LENINGRADREGION (47), MOSCOWREGION (50), ROSTOVREGION (61), MOSCOW (77), SAINTPETERSBURG (78);

     private final int regionCode;

    RegionCode(int regionCode) {
        this.regionCode = regionCode;
    }

    public static List<Integer> getMskMoCodes() {
        return List.of(MOSCOWREGION.getRegionCode(), MOSCOW.getRegionCode());
    }

}
