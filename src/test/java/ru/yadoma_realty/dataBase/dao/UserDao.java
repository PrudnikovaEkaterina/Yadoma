package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;

@Slf4j
public class UserDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static Long getUserId (String userPhoneNumber) {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "select u.id from UserEntity u where u.phone=?1";

        var result = session.createQuery(query, Long.class)
                .setParameter(1, userPhoneNumber).uniqueResult();

        session.getTransaction().commit();

        return result;
    }

}
