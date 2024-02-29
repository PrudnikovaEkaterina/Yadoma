package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class MarketcallBundleBuildingPrimaryKey {
    @Column(name = "bundle_id")
    private int bundleId;

    @Column(name = "building_id")
    private int buildingId;

}
