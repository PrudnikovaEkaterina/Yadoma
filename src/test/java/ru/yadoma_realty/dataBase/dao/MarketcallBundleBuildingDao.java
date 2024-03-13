package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.enums.RegionCode;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.List;

public class MarketcallBundleBuildingDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static int countAllNotDeletedAt() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(*) from MarketcallBundleBuildingEntity m where m.deletedAt is null";
        var result = session.createQuery(query, Long.class).getSingleResult().intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countDistinctBuildingIdNotDeletedAt() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(m.primaryKey.building.id) from MarketcallBundleBuildingEntity m where m.deletedAt is null";
        var result = session.createQuery(query, Long.class).getSingleResult().intValue();

        session.getTransaction().commit();

        return result;
    }

    public static int countDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(int external_id) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select count(m.primaryKey.building.id) from MarketcallBundleBuildingEntity m where m.deletedAt is null and m.primaryKey.building.garAddressObject.regionCode in(50, 77) and m.primaryKey.marketcallBundle.externalId =?1";
        var result = session.createQuery(query, Long.class)
                .setParameter(1, external_id)
                .getSingleResult().intValue();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWhereSetRegionCodeExternalIdNotDeletedAt(int externalId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = """
                select distinct m.primaryKey.building.id from MarketcallBundleBuildingEntity m 
                where m.deletedAt is null and m.primaryKey.building.garAddressObject.regionCode in :regionCode
                and m.primaryKey.marketcallBundle.externalId =:externalId""";
        var result = session.createQuery(query, Integer.class)
                .setParameter("externalId", externalId)
                .setParameterList("regionCode", RegionCode.getMskMoCodes())
                .list();

        session.getTransaction().commit();

        return result;
    }
}
