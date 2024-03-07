package ru.yadoma_realty.dataBase.entities.buildingEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.yadoma_realty.dataBase.entities.FlatEntity;
import ru.yadoma_realty.dataBase.entities.GarAddressObjectEntity;
import ru.yadoma_realty.dataBase.entities.MarketcallBundleBuildingPrimaryKey;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString(exclude = {"garAddressObject", "dataJson", "marketcallBundleBuildingPrimaryKeyList", "flatEntity"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "buildings")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "title_eng")
    private String titleEng;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "gar_object_id", referencedColumnName = "OBJECTID")
    private GarAddressObjectEntity garAddressObject;

    @Column(name = "developer_id")
    private Long developerId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "blurhash")
    private String blurhash;

    @Column(name = "data_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private BuildingDataJson dataJson;

    @OneToMany(mappedBy = "building")
    @Transient //поле игнорируется при запросе users из базы
    private List<MarketcallBundleBuildingPrimaryKey> marketcallBundleBuildingPrimaryKey;

    @OneToMany(mappedBy = "building")
//  @Transient
    private List<FlatEntity> flatEntity;

}
