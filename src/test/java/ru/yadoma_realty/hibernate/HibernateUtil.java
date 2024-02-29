package ru.yadoma_realty.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yadoma_realty.dataBase.entities.CallbackPhoneEntity;
import ru.yadoma_realty.dataBase.entities.FavoriteEntity;
import ru.yadoma_realty.dataBase.entities.MarketcallBundleBuildingEntity;
import ru.yadoma_realty.dataBase.entities.UserEntity;

@Slf4j
public enum HibernateUtil {
    INSTANCE;
    private SessionFactory sessionFactory;

    public SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .addAnnotatedClass(CallbackPhoneEntity.class)
                        .addAnnotatedClass(FavoriteEntity.class)
                        .addAnnotatedClass(UserEntity.class)
                        .addAnnotatedClass(MarketcallBundleBuildingEntity.class)
                        .buildSessionFactory();

            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

}
