package ru.yadoma_realty.api.models.building_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BuildingData(
        List<Building> data
) {
}
