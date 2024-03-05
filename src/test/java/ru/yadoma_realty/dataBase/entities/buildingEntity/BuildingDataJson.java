package ru.yadoma_realty.dataBase.entities.buildingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingDataJson implements Serializable {
    private Long id;

    @JsonProperty("title_eng")
    private String titleEng;

    @JsonProperty("parent_id")
    private String parentId;

    private PropertyJson properties;

    @JsonProperty("prices")
    private List<PriceJson> pricesList;

}
