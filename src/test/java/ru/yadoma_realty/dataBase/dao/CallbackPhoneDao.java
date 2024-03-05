package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.hibernate.HibernateSession;
import ru.yadoma_realty.hibernate.HibernateUtil;
import ru.yadoma_realty.dataBase.entities.CallbackPhoneEntity;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static ru.yadoma_realty.dataBase.exeption.NoEntityException.noEntityException;

@Slf4j
public class CallbackPhoneDao {
    private static final SessionFactory sessionFactory = HibernateUtil.INSTANCE.buildSessionFactory();

    public static String getPhoneNumberFromLastEntry() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);

        var query = "FROM CallbackPhoneEntity c WHERE c.id=(SELECT max(ce.id) FROM CallbackPhoneEntity ce)";
        var result = Optional.ofNullable(session.createQuery(query, CallbackPhoneEntity.class).uniqueResult())
                .map(CallbackPhoneEntity::getPhone)
                .orElseThrow(noEntityException("Телефонный номер не найден"));

        session.getTransaction().commit();

        return result;
    }

    public static String getLinkFromLastEntry() {
        @Cleanup
        var session = HibernateSession.getSession(sessionFactory);
        var query = "FROM CallbackPhoneEntity c WHERE c.id=(SELECT max(ce.id) FROM CallbackPhoneEntity ce)";

        var result = Optional.ofNullable(session.createQuery(query, CallbackPhoneEntity.class).uniqueResult())
                .map(CallbackPhoneEntity::getLink)
                .orElseThrow(noEntityException("Ссылка не найдена"));

        session.getTransaction().commit();

        return result;
    }

}
