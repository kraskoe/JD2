package com.pvt.app.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */


public class EMUtil {
    private static EntityManagerFactory emFactory = null;
    public static EntityManager getEntityManager (){
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory("com.pvt.app");
        }
        return emFactory.createEntityManager();
    }
    public static void close() {
        emFactory.close();
    }
}
