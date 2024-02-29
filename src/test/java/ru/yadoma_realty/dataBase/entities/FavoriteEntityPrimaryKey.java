package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class FavoriteEntityPrimaryKey implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "entity_type")
    private int entityType;

    @Column(name = "entity_id")
    private int entityId;

}
