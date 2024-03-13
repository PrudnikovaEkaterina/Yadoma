package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.yadoma_realty.dataBase.entities.buildingEntity.BuildingEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "flats")
public class FlatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private BuildingEntity building;

    @Column(name = "house_id")
    private Long houseId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "finishing")
    private Integer finishing;

    @Column(name = "price_total")
    private Long priceTotal;

    @Column(name = "area_total")
    private Double areaTotal;

    @Embedded
    private FlatPaymentMethod paymentMethod;
}
