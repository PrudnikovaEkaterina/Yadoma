package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.List;

public class FlatDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static List<Integer> collectDistinctBuildingIdWithSetFloorAndStatus(int floor) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.floor>=?1 and f.status=1";
        var result = session.createQuery(query, Integer.class)
                .setParameter(1, floor)
                .list();

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectDistinctBuildingIdWithSetPaymentMethodAndStatus(String paymentMethod) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select distinct f.building.id from FlatEntity f where f.paymentMethod.?1=1 and f.status=1";
        var result = session.createQuery(query, Integer.class)
               .setParameter(1, paymentMethod)
                .list();

        session.getTransaction().commit();

        return result;
    }
}
