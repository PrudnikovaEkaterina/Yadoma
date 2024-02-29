package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

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

        var query = "select count(m.primaryKey.buildingId) from MarketcallBundleBuildingEntity m where m.deletedAt is null";
        var result = session.createQuery(query, Long.class).getSingleResult().intValue();

        session.getTransaction().commit();

        return result;
    }
}
