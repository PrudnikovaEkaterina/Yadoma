package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;
import ru.yadoma_realty.dataBase.entities.CallbackPhoneEntity;
import java.util.Optional;

@Slf4j
public class CallbackPhoneDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static String getPhoneNumberFromLastEntryCallbackPhones() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "FROM CallbackPhoneEntity c WHERE c.id=(SELECT max(ce.id) FROM CallbackPhoneEntity ce)";
        var phoneNumber = Optional.ofNullable(session.createQuery(query, CallbackPhoneEntity.class).uniqueResult())
                .map(CallbackPhoneEntity::getPhone)
                .orElse(null);

        session.getTransaction().commit();

        return phoneNumber;
    }

    public static String getLinkFromLastEntryCallbackPhones() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "FROM CallbackPhoneEntity c WHERE c.id=(SELECT max(ce.id) FROM CallbackPhoneEntity ce)";

        var link = Optional.ofNullable(session.createQuery(query, CallbackPhoneEntity.class).uniqueResult())
                .map(CallbackPhoneEntity::getLink)
                .orElse(null);

        session.getTransaction().commit();

        return link;
    }

}
