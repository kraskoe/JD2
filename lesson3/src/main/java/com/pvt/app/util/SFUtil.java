package com.pvt.app.util;

import com.pvt.app.pojos.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */


public class SFUtil {
    private static final SessionFactory sessionFactory;

    /*
        SessionFactory initialization
     */
    static {
//        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//        // Hibernate settings equivalent to hibernate.cfg.xml's properties
//        Map<String, String> settings = new HashMap<>();
//        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
//        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/jpadb");
//        settings.put(Environment.USER, "root");
//        settings.put(Environment.PASS, "root");
//        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
//        settings.put(Environment.SHOW_SQL, "true");
//        settings.put(Environment.ISOLATION, "2");
//        settings.put(Environment.STORAGE_ENGINE, "innodb");
//        // Apply settings
//        serviceRegistryBuilder.applySettings(settings);
//        // Create registry
//        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//        // Create MetadataSources
//        MetadataSources sources = new MetadataSources(serviceRegistry);
//        sources.addAnnotatedClass(Cat.class);
//        // Create Metadata
//        Metadata metadata = sources.getMetadataBuilder().build();
//        // Create SessionFactory
//        sessionFactory = metadata.getSessionFactoryBuilder().build();

//        Configuration configuration = new Configuration();
//        configuration.setNamingStrategy(new CatNamingStrategy());
//        sessionFactory = configuration.configure().buildSessionFactory();

        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CatNamingStrategy());
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
