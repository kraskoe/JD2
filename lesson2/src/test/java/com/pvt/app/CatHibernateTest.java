package com.pvt.app;

import com.pvt.app.dao.CatHibernateDao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.SFUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yauheni Krasko on 14.10.2017.
 */
public class CatHibernateTest extends HibernateTest {
    public static final CatHibernateDao catHibDao = new CatHibernateDao();

    @Test
    public void saveTest() {
        Cat cat = new Cat(null, 2, "Murzik");

        cat = catHibDao.create(cat);
        session.clear();
        Cat catFromDB = session.get(Cat.class, cat.getId());
        Assert.assertEquals(cat, catFromDB);
    }
    //noinspection Duplicates
    @Test
    public void getGetTest() {
        Cat cat = new Cat(null, 4, "Nimfa");
        String catName = null;
        cat = catHibDao.create(cat);

        if (catHibDao.readGet(cat.getId()) != null) {
            catName = catHibDao.readGet(cat.getId()).getName();
        }
        assertTrue(catName == cat.getName());
    }
    //noinspection Duplicates
    @Test
    public void getLoadTest() {
        Cat cat = new Cat(null, 3, "Afina");
        String catName = null;
        cat = catHibDao.create(cat);

        if (catHibDao.readLoad(cat.getId()) != null) {
            catName = catHibDao.readLoad(cat.getId()).getName();
        }
        assertTrue(catName == cat.getName());
    }
    @Test
    public void getGetNullTest() {
        Cat cat = new Cat(null, 3, "Afina");
        String catName = null;
        cat = catHibDao.create(cat);

        if (catHibDao.readGet(3L) != null) {
            catName = catHibDao.readGet(3L).getName();
            assertTrue(catName == cat.getName());
        } else {
            assertTrue(catName == null);
        }
    }
    //!!!
    @Test
    public void getLoadNullTest() {
        session.getTransaction().begin();
        Cat cat = new Cat(null, 3, "Afina");
        String catName = null;
        cat = catHibDao.create(cat);
        Cat catFromDB = session.load(Cat.class, 58L);
        session.getTransaction().commit();
//        System.out.println(session.load(Cat.class, 3L));

//        if (catHibDao.readLoad(3L) != null) {
//            catName = catHibDao.readLoad(3L).getName();
//            assertTrue(catName == cat.getName());
//        } else {
//            assertTrue(catName == null);
//        }
    }
    @Test
    public void updateTest() {
        Cat cat = new Cat(null, 5, "Afina");

        cat = catHibDao.create(cat);
        session.detach(cat);
        cat.setAge(3);
        catHibDao.update(cat);
        assertEquals(catHibDao.readGet(cat.getId()).getAge(), cat.getAge());
    }
    @Test
    public void deleteTest() {
        Cat cat = new Cat(null, 1, "Kudablya");

        cat = catHibDao.create(cat);
        catHibDao.delete(cat.getId());
        assertTrue(catHibDao.readGet(cat.getId()) == null);
    }

}
