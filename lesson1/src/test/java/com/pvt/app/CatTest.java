package com.pvt.app;

import com.pvt.app.dao.CatDao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatTest {
    EntityManager em = null;
    Cat cat = new Cat(null, 2, "Murzik");
    CatDao catDao = new CatDao();
    @Before
    public void init() {
        em = EMUtil.getEntityManager();
    }
    @Test
    public void saveTest() {
        cat = catDao.save(cat);
        em.close();
    }
    @Test
    public void getTest() {
        cat = catDao.save(cat);
        assertTrue(catDao.get(cat.getId()).getName() == cat.getName());
        em.close();
    }
    @Test
    public void updateTest() {
        cat = catDao.save(cat);
//        em.detach(cat);
        cat.setAge(3);
        catDao.update(cat);
        assertEquals(catDao.get(cat.getId()).getAge(), cat.getAge());
        em.close();
    }
    @Test
    public void deleteTest() {
        cat = catDao.save(cat);
        catDao.delete(cat.getId());
        assertTrue(catDao.get(cat.getId()) == null);
        em.close();
    }
    @AfterClass
    public static void close() {
        EMUtil.close();
    }
}
