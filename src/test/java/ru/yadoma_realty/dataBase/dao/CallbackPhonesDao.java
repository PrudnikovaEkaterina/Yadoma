package ru.yadoma_realty.dataBase.dao;

import lombok.Cleanup;
import ru.yadoma_realty.configuration.HibernateConfig;
import ru.yadoma_realty.dataBase.entities.CallbackPhonesEntity;


public class CallbackPhonesDao {

    public static void getLastEntryFromCallbackPhonesTables() {
        @Cleanup
        var sessionFactory = HibernateConfig.buildSessionFactory();
        @Cleanup
        var session = sessionFactory.openSession();

        session.getTransaction().begin();

        session.createNativeQuery("SET TRANSACTION READ ONLY", CallbackPhonesEntity.class).executeUpdate();
//        "SELECT * FROM callback_phones WHERE id=(SELECT max(id) FROM callback_phones);"

        CallbackPhonesEntity callbackPhonesEntity = session.createQuery("from CallbackPhonesEntity as c WHERE c.id = (SELECT max(ce.id) FROM CallbackPhonesEntity as ce);",
                CallbackPhonesEntity.class).getSingleResult();
        System.out.println(callbackPhonesEntity.getPhone());

        session.getTransaction().commit();
    }

}
