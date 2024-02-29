package ru.yadoma_realty.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.yadoma_realty.dataBase.entities.CallbackPhoneEntity;

@Slf4j
public class HibernateSession {
    public static Session getSession (SessionFactory sessionFactory){
        var session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.createNativeQuery("SET TRANSACTION READ ONLY", CallbackPhoneEntity.class).executeUpdate();
        return session;
    }
}
