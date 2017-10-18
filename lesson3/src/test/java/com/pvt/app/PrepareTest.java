package com.pvt.app;

import com.pvt.app.util.SFEMUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */
public class PrepareTest {
    protected static EntityManager em;
    protected static Session sf;

    @BeforeClass
    public static void init() {
        em = SFEMUtil.getEntityManager();
        sf = SFEMUtil.getSession();
    }

    @AfterClass
    public static void tearDown() {
        em.clear();
        em.close();
        sf.clear();
        sf.close();
        SFEMUtil.closeEMF();
        SFEMUtil.closeSF();
    }
}
