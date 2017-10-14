package com.pvt.app;

import com.pvt.app.util.EMUtil;
import com.pvt.app.util.SFUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;


/**
 * Created by Yauheni Krasko on 14.10.2017.
 */
public class HibernateTest {
    protected static org.hibernate.Session session;

    @BeforeClass
    public static void init() {
        session = SFUtil.getSession();
    }

    @AfterClass
    public static void tearDown() {
        session.clear();
        session.close();
        SFUtil.closeSessionFactory();
    }
}
