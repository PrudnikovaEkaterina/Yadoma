package ru.yadoma_realty.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yadoma_realty.dataBase.entities.*;
import ru.yadoma_realty.dataBase.entities.buildingEntity.BuildingEntity;
import ru.yadoma_realty.dataBase.entities.FlatEntity;
import ru.yadoma_realty.dataBase.entities.favoriteEntity.FavoriteEntity;
import ru.yadoma_realty.dataBase.entities.marketcallBundleBuildingEntity.MarketcallBundleBuildingEntity;

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
                        .addAnnotatedClass(MarketcallBundleEntity.class)
                        .addAnnotatedClass(BuildingEntity.class)
                        .addAnnotatedClass(GarAddressObjectEntity.class)
                        .addAnnotatedClass(FlatEntity.class)
                        .buildSessionFactory();

            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

}
