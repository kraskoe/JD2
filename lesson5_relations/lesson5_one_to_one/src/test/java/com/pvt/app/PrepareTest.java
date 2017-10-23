package com.pvt.app;

import com.pvt.app.util.EMUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */
public class PrepareTest {
    protected static EntityManager em;

    @BeforeClass
    public static void init() {
        em = EMUtil.getEntityManager();
    }

    @AfterClass
    public static void tearDown() {
        em.clear();
        em.close();
        EMUtil.close();
    }
}
