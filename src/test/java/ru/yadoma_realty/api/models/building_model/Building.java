package ru.yadoma_realty.api.models.building_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Building(
        Integer id,
        @JsonProperty("title_eng")
        String titleEng,
        @JsonProperty("marked_at")
        String markedAt,
        @JsonProperty("parent_id")
        Integer parentId,
        @JsonProperty("release_date")
        String releaseDate,
        Integer apartments,
        List<String> square,
        @JsonProperty("floor_range")
        List<String> floorRange
) {
}
