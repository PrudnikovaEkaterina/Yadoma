package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.yadoma_realty.dataBase.entities.buildingEntity.BuildingEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "gar_ADDRESSOBJECTS")
public class GarAddressObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OBJECTID")
    private Long ObjectId;

    @Column(name = "region_code")
    private Integer regionCode;

    @OneToMany (mappedBy = "garAddressObject") // 1 garAddress может принадлежать многим ЖК
    @Transient
    private List<BuildingEntity> buildingEntityList;
}
