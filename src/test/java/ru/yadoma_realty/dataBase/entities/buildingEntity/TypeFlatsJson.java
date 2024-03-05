package ru.yadoma_realty.dataBase.entities.buildingEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeFlatsJson implements Serializable {
    public String title;
    public Map<String, String> values;
}
