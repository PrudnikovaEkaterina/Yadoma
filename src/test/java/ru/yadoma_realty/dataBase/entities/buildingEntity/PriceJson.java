package ru.yadoma_realty.dataBase.entities.buildingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceJson implements Serializable {

    private String title;

    private String slug;

    private String total;

    @JsonProperty("per_unit")
    private String perUnit;

    @JsonProperty("price_min")
    private Long priceMin;

    @JsonProperty("price_max")
    private Long priceMax;

    @JsonProperty("unit_price_min")
    private Long unitPriceMin;

    @JsonProperty("unit_price_max")
    private Long unitPriceMax;

    @JsonProperty("area_min")
    private Long areaMin;

    @JsonProperty("area_max")
    private Long areaMax;
}
