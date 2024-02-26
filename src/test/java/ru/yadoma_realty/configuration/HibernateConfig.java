package ru.yadoma_realty.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.yadoma_realty.dataBase.entities.CallbackPhonesEntity;

public class HibernateConfig {
    public static SessionFactory buildSessionFactory(){
        Configuration cfg = new Configuration()
                .addAnnotatedClass(CallbackPhonesEntity.class);
        return cfg.buildSessionFactory();
    }
}
