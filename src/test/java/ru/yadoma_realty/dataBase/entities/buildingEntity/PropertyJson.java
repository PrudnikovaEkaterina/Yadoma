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
public class PropertyJson implements Serializable {

    @JsonProperty("202")
    private TypeFlatsJson typeFlats;

    @JsonProperty("241")
    private ReleaseYearJson releaseYear;

    @JsonProperty("242")
    private ReleaseQuarterJson releaseQuarter;

}
