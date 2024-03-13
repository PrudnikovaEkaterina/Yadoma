package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.enums.FinishingTypes;
import ru.yadoma_realty.enums.FlatStatus;
import ru.yadoma_realty.enums.RegionCode;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlatDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static List<Integer> collectDistinctBuildingIdWithSetFloorAndStatus(int floor) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.floor>= :floor and f.status=:status";
        var result = session.createQuery(query, Integer.class)
                .setParameter("floor", floor)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetPaymentMethodAndStatus(String paymentMethod) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.paymentMethod." + paymentMethod + "=1 and f.status=:status";
        var result = session.createQuery(query, Integer.class)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetFinishingAndStatus(FinishingTypes finishingEnum) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.finishing = :finishing and f.status=:status";
        var result = session.createQuery(query, Integer.class)
                .setParameter("finishing", finishingEnum.getValue())
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Long> collectDistinctHouseIdWithSetBuildingIdAndStatus(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.houseId from FlatEntity f where f.building.id = :buildingId and f.status=:status";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .list()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        session.getTransaction().commit();

        return result;
    }

    public static List<Long> collectDistinctHouseIdWithSetRegionCodeAndStatus() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.building.garAddressObject.regionCode in :regionCode and f.status=:status";
        var result = session.createQuery(query, Long.class)
                .setParameterList("regionCode", RegionCode.getMskMoCodes())
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .list()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        session.getTransaction().commit();

        return result;
    }

    public static int countAllWithSetBuildingIdAndFlatStatusWithoutHouseId(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from FlatEntity f where f.building.id=:buildingId and f.status=:status and f.houseId is null";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .uniqueResult()
                .intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countAllWithSetBuildingIdAndFlatStatus(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from FlatEntity f where f.building.id=:buildingId and f.status=:status";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .uniqueResult()
                .intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countAllWithFilterPriceTotalAndSetBuildingIdAndFlatStatus(int buildingId, Long priceMin, Long priceMax) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from FlatEntity f where f.building.id=:buildingId and f.status=:status and f.priceTotal >= :priceMin and f.priceTotal <= :priceMax";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .setParameter("priceMin", priceMin)
                .setParameter("priceMax", priceMax)
                .uniqueResult()
                .intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countAllWithFilterAreaTotalAndSetBuildingIdAndFlatStatus(int buildingId, Double areaMin, Double areaMax) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from FlatEntity f where f.building.id=:buildingId and f.status=:status and f.areaTotal >= :areaMin and f.areaTotal <= :areaMax";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .setParameter("areaMin", areaMin)
                .setParameter("areaMax", areaMax)
                .uniqueResult()
                .intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countAllWithFilterFloorAndSetBuildingIdAndFlatStatus(int buildingId, int floorMin, int floorMax) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from FlatEntity f where f.building.id=:buildingId and f.status=:status and f.floor >= :floorMin and f.floor <= :floorMax";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("status", FlatStatus.PUBLISHED.getValue())
                .setParameter("floorMin", floorMin)
                .setParameter("floorMax", floorMax)
                .uniqueResult()
                .intValue();

        session.getTransaction().commit();

        return result;
    }
}
