package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.yadoma_realty.dataBase.entities.marketcallBundleBuildingEntity.MarketcallBundleBuildingPrimaryKey;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "marketcall_bundles")
public class MarketcallBundleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "state")
    private int state;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @OneToMany(mappedBy="marketcallBundle")
    @Transient //поле игнорируется при запросе users из базы
    private List<MarketcallBundleBuildingPrimaryKey> marketcallBundleBuildingPrimaryKeyList;

}
