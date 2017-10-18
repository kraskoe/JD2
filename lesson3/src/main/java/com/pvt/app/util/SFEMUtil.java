package com.pvt.app.util;

import com.pvt.app.pojos.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */


public class SFEMUtil {
    private static final EntityManagerFactory emFactory;
    private static final SessionFactory sessionFactory;

    /*
        EntityManager initialization
     */
    static {
        emFactory = Persistence.createEntityManagerFactory("com.pvt.app");
    }
    /*
        SessionFactory initialization
     */
    static {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jpadb");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.HBM2DDL_AUTO, "create");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.STORAGE_ENGINE, "innodb");
        settings.put(Environment.PHYSICAL_NAMING_STRATEGY, "com.pvt.app.util.CatNamingStrategy");

        // Apply settings
        serviceRegistryBuilder.applySettings(settings);

        // Create registry
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        // Create MetadataSources
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(Cat.class);
        sources.addAnnotatedClass(Dog.class);
        sources.addAnnotatedClass(Parrot.class);
        sources.addAnnotatedClass(Hamster.class);
        sources.addAnnotatedClass(WhiteRat.class);

        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();


    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void closeEMF() {
        emFactory.close();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSF() {
        sessionFactory.close();
    }
}
