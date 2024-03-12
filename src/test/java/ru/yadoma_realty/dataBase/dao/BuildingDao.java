package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.dataBase.entities.buildingEntity.*;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.*;

import static java.util.stream.Collectors.toList;


public class BuildingDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static Optional<ArrayList<String>> collectBuildingRoomTypeValuesToList(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";

        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getProperties)
                .map(PropertyJson::getTypeFlats)
                .map(TypeFlatsJson::getValues)
                .map(Map::values)
                .map(ArrayList::new);

        session.getTransaction().commit();

        return result;
    }

    public static Optional<List<String>> collectBuildingPricesTitleToList(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";
        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getPricesList)
                .map(list -> list.stream().map(PriceJson::getTitle).collect(toList()));

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

    public static List<String> collectDistinctBuildingTitleEngWithSetRegionCodeAndFlatsStatusToList() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT DISTINCT b.titleEng from BuildingEntity b inner join b.flatEntity f WHERE b.garAddressObject.regionCode in (50, 77) and f.status=1";
        var result = session.createQuery(query, String.class).list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Long> collectDistinctBuildingGarObjectIdWithSetRegionCodeAndFlatsStatusToList() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT DISTINCT b.garAddressObject.ObjectId from BuildingEntity b inner join b.flatEntity f WHERE b.garAddressObject.regionCode in (50, 77) and f.status=1";
        var result = session.createQuery(query, Long.class).list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetRegionCodeAndFlatsStatusToList() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT DISTINCT b.id from BuildingEntity b inner join b.flatEntity f WHERE b.garAddressObject.regionCode in (50, 77) and f.status=1";
        var result = session.createQuery(query, Integer.class).list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectBuildingIdWithoutFlatsWithSetRegionCodeAndExistPricesAndNullParentId() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.id from BuildingEntity b where b.garAddressObject.regionCode in (50, 77) and " +
                "not exists (select 1 from FlatEntity f where f.building.id = b.id and f.status=1) and " +
                "JSON_VALUE (b.dataJson, \"$.prices[*].*\") is not null and b.parentId is null";
        var result = session.createQuery(query, Integer.class)
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static Optional<String> getTitleEngWithSetBuildingId(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.titleEng FROM BuildingEntity b WHERE b.id=?1";
        var result = session.createQuery(query, String.class)
                .setParameter(1, buildingId).uniqueResultOptional();

        session.getTransaction().commit();

        return result;
    }

    public static Optional<Long> getBuildingPriceMinWithSetBuildingIdAndPricesTitle(int buildingId, String title) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select b.dataJson FROM BuildingEntity b WHERE b.id=?1";
        var result = session.createQuery(query, BuildingDataJson.class)
                .setParameter(1, buildingId)
                .uniqueResultOptional()
                .map(BuildingDataJson::getPricesList)
                .flatMap(list -> list.stream().filter(e -> e.getTitle().equals(title)).map(PriceJson::getPriceMin).toList().stream().findFirst());

        session.getTransaction().commit();

        return result;
    }
}
