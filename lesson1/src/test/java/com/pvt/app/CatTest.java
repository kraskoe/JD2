package com.pvt.app;

import com.pvt.app.dao.CatDao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatTest {
    Cat cat = new Cat(null, 2, "Murzik");
    CatDao catDao = new CatDao();

    @Before
    public void init() {
        EntityManager em = EMUtil.getEntityManager();
    }
    @Test
    public void saveTest() {
        catDao.save(cat);
    }
    @Test
    public void getTest() {
        catDao.get(1L);
        assertTrue(catDao.get(1L).getName() == cat.getName());
    }
    @Test
    public void updateTest() {
        cat.setAge(3);
        catDao.update(cat);
//        assertEquals(catDao.get(1L).getAge(), cat.getAge());
    }
    @Test
    public void deleteTest() {
        catDao.delete(1L);
        assertTrue(catDao.get(1L) == null);
    }
//    @After
//    public void close() {
//        EMUtil.close();
//    }
}
