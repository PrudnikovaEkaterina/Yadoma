package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.enums.FinishingEnum;
import ru.yadoma_realty.enums.RegionCodeEnum;
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

        var query = "select distinct f.building.id from FlatEntity f where f.floor>= :floor and f.status=1";
        var result = session.createQuery(query, Integer.class)
                .setParameter("floor", floor)
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetPaymentMethodAndStatus(String paymentMethod) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.paymentMethod." + paymentMethod + "=1 and f.status=1";
        var result = session.createQuery(query, Integer.class).list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetFinishingAndStatus(FinishingEnum finishingEnum) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.finishing = :finishing and f.status=1";
        var result = session.createQuery(query, Integer.class)
                .setParameter("finishing", finishingEnum.getValue())
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Long> collectDistinctHouseIdWithSetBuildingIdAndStatus(int buildingId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.houseId from FlatEntity f where f.building.id = :buildingId and f.status=1";
        var result = session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
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

        var query = "select distinct f.building.id from FlatEntity f where f.building.garAddressObject.regionCode in :regionCode and f.status=1";
        var result = session.createQuery(query, Long.class)
                .setParameterList("regionCode", RegionCodeEnum.getMskMoCodes())
                .list()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        session.getTransaction().commit();

        return result;
    }

}
