package ru.yadoma_realty.dataBase.dao;


import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.yadoma_realty.configuration.HibernateConfig;

public class CallbackPhonesDao {
    @Cleanup
    SessionFactory sessionFactory = HibernateConfig.buildSessionFactory();

    Session session = sessionFactory.openSession();

//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {}

    Transaction transaction = session.beginTransaction();
//        session.createNativeQuery("SET TRANSACTION READ ONLY", City.class).executeUpdate();

}
