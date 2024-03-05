package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.dataBase.entities.buildingEntity.BuildingDataJson;
import ru.yadoma_realty.dataBase.entities.buildingEntity.PriceJson;
import ru.yadoma_realty.dataBase.entities.buildingEntity.PropertyJson;
import ru.yadoma_realty.dataBase.entities.buildingEntity.TypeFlatsJson;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.*;
import java.util.stream.Collectors;

import static ru.yadoma_realty.dataBase.exeption.NoEntityException.noEntityException;

public class BuildingDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static Collection<String> collectBuildingRoomTypeValues(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";
        var result = Optional.ofNullable(session.createQuery(query, BuildingDataJson.class)
                        .setParameter(1, buildingId)
                        .uniqueResult())
                .map(BuildingDataJson::getProperties)
                .map(PropertyJson::getTypeFlats)
                .map(TypeFlatsJson::getValues)
                .map(Map::values)
                .orElseThrow(noEntityException("JSON_EXTRACT (data_json, \"$.properties.202.values.*\") для ЖК {0} не найден.", buildingId));

        session.getTransaction().commit();

        return result;
    }

    public static List<String> collectBuildingPricesTitleToList(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";
        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getPricesList)
                .map(list -> list.stream().map(PriceJson::getTitle).collect(Collectors.toList()))
                .orElseThrow(noEntityException("JSON_EXTRACT (data_json, \"$.prices[*].title\")для ЖК {0} не найден.", buildingId));

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithoutFlatsWithSetRegionCodeAndExistUnitPriceMin() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select DISTINCT b.id from BuildingEntity b where b.garAddressObject.regionCode in (50, 77) and " +
                "not exists (select 1 from FlatEntity f where f.building.id = b.id and f.status=1) and " +
                "JSON_VALUE (b.dataJson, \"$.prices[*].unit_price_min\") is not null";
        var result = session.createQuery(query, Integer.class)
                .setMaxResults(5)
                .list();

        session.getTransaction().commit();

        return result;
    }
}
