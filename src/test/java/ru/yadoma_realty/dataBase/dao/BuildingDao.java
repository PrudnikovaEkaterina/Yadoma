package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.dataBase.entities.buildingEntity.*;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.*;
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

    public static List<Integer> collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistUnitPriceMin() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.id from BuildingEntity b where b.garAddressObject.regionCode in (50, 77) and " +
                "not exists (select 1 from FlatEntity f where f.building.id = b.id and f.status=1) and " +
                "JSON_VALUE (b.dataJson, \"$.prices[*].unit_price_min\") is not null";
        var result = session.createQuery(query, Integer.class)
                .setMaxResults(5)
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectBuildingIdWithoutFlatsWithSetRegionCodeAndPricesExistAreaMin() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.id from BuildingEntity b where b.garAddressObject.regionCode in (50, 77) and " +
                "not exists (select 1 from FlatEntity f where f.building.id = b.id and f.status=1) and " +
                "JSON_VALUE (b.dataJson, \"$.prices[*].area_min\") is not null";
        var result = session.createQuery(query, Integer.class)
                .setMaxResults(5)
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectBuildingIdToListWithSetParentId(int parentId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.id FROM BuildingEntity b WHERE b.parentId=?1";
        var result = session.createQuery(query, Integer.class)
                .setParameter(1, parentId).list();
        session.getTransaction().commit();

        return result;
    }

    public static Optional<String> getBuildingReleaseYear(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";

        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getProperties)
                .map(PropertyJson::getReleaseYear)
                .map(ReleaseYearJson::getValues)
                .map(Map::values)
                .flatMap(el -> el.stream().findFirst());

        session.getTransaction().commit();

        return result;
    }

    public static Optional<String> getBuildingReleaseQuarter(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";

        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getProperties)
                .map(PropertyJson::getReleaseQuarter)
                .map(ReleaseQuarterJson::getValues)
                .map(Map::values)
                .flatMap(el -> el.stream().findFirst());

        session.getTransaction().commit();

        return result;
    }
}
