package ru.yadoma_realty.dataBase.entities.marketcallBundleBuildingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.yadoma_realty.dataBase.entities.MarketcallBundleEntity;
import ru.yadoma_realty.dataBase.entities.buildingEntity.BuildingEntity;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class MarketcallBundleBuildingPrimaryKey implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "bundle_id", referencedColumnName = "id")
    private MarketcallBundleEntity marketcallBundle;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private BuildingEntity building;

}
