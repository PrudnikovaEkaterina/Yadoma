package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.List;
import java.util.Optional;

import static ru.yadoma_realty.dataBase.exeption.NoEntityException.noEntityException;

public class MarketcallBundleDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static List<String> collectTitleToListNotDeletedAt() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT m.title from MarketcallBundleEntity m where m.deletedAt is null";
        var result = session.createQuery(query, String.class).list();

        session.getTransaction().commit();

        return result;
    }

    public static String getTitleNotDeletedAtAndSetParameterExternalId(int externalId) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT m.title from MarketcallBundleEntity m where m.deletedAt is null and m.externalId=?1";
        var result = Optional.ofNullable(session.createQuery(query, String.class)
                        .setParameter(1, externalId)
                        .uniqueResult())
                .orElseThrow(noEntityException("Title from MarketcallBundleEntity where deletedAt is null and externalId= {0} НЕ НАЙДЕН.", externalId));

        session.getTransaction().commit();

        return result;
    }

    public static List<Integer> collectExternalIdToListNotDeletedAt (){
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT m.externalId from MarketcallBundleEntity m where m.deletedAt is null";
        var result =session.createQuery(query, Integer.class).list();

        session.getTransaction().commit();

        return result;
    }

}