package ru.yadoma_realty.dataBase.dao;

import jakarta.persistence.Tuple;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.enums.EntityType;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FavoriteDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static Map<Integer, String> collectEntityIdAndUpdatedAtToMapWithSetParameters(int userId, EntityType entityType) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = """
                select f.primaryKey.entityId as entityId, DATE_FORMAT(f.updatedAt, '%d.%m.%Y') as updatedAt  from FavoriteEntity f 
                where f.primaryKey.userId=:userId and f.primaryKey.entityType=:entityType""";

        Map<Integer, String> resultMap = session.createQuery(query, Tuple.class)
                .setParameter("userId", userId)
                .setParameter("entityType", entityType.getValue())
                .getResultStream()
                .collect(Collectors.toMap(
                        tuple -> ((Integer) tuple.get("entityId")),
                        tuple -> (tuple.get("updatedAt").toString()))
                );

        session.getTransaction().commit();

        return resultMap;
    }

    public static List<Integer> collectBuildingIdToListWithSetParameters(int userId, EntityType entityType) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "SELECT f.primaryKey.entityId FROM FavoriteEntity f where f.primaryKey.userId=:userId and f.primaryKey.entityType=:entityType";

        List<Integer> resultList = session.createQuery(query, Integer.class)
                .setParameter("userId", userId)
                .setParameter("entityType", entityType.getValue())
                .getResultList();

        session.getTransaction().commit();

        return resultList;
    }

}
