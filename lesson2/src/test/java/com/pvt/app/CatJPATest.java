package com.pvt.app;

import com.pvt.app.dao.CatJPADao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatJPATest extends JPATest {
    public static final CatJPADao catJPADao = new CatJPADao();

    @Test
    public void saveTest() {
        Cat cat = new Cat(null, 2, "Murzik");

        cat = catJPADao.create(cat);
        em.clear();
        Cat catFromDB = em.find(Cat.class, cat.getId());
        Assert.assertEquals(cat, catFromDB);
    }
    @Test
    public void getTest() {
        Cat cat = new Cat(null, 4, "Nimfa");

        cat = catJPADao.create(cat);
        assertTrue(catJPADao.read(cat.getId()).getName() == cat.getName());
    }
    @Test
    public void updateTest() {
        Cat cat = new Cat(null, 5, "Afina");

        cat = catJPADao.create(cat);
        em.detach(cat);
        cat.setAge(3);
        catJPADao.update(cat);
        assertEquals(catJPADao.read(cat.getId()).getAge(), cat.getAge());
    }
    @Test
    public void deleteTest() {
        Cat cat = new Cat(null, 1, "Kudablya");

        cat = catJPADao.create(cat);
        catJPADao.delete(cat.getId());
        assertTrue(catJPADao.read(cat.getId()) == null);
    }

    @Test
    public void flushAutoJPQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Cat(null, 7, "Kitten"));
        entityManager.createQuery( "select age, name from Cat" ).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushAutoNativeSqlTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        System.out.println(entityManager.createNativeQuery( "select count(*) from Cat" ).getSingleResult());
        entityManager.persist(new Cat(null, 5, "Kudablya"));
        System.out.println(entityManager.createNativeQuery( "select count(*) from Cat" ).getSingleResult());
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushCommitJPQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Cat cat = new Cat(null, 4, "Kudablya");
        entityManager.persist(cat);
        cat.setAge(7);
        entityManager.merge(cat);
        entityManager.createQuery( "select name from Cat c" )
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
        entityManager.createQuery( "select age from Cat c" )
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushAutoWithoutMergeJPQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Cat cat = new Cat(null, 4, "Kudablya");
        entityManager.persist(cat);
        cat.setAge(7);
        entityManager.createQuery( "select age from Cat c" )
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void flushCommitNativeSQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Cat(null, 4, "Kudablya"));
        entityManager.persist(new Cat(null, 3, "kitten"));
        entityManager.createNativeQuery( "select * from Cat" )
                .setFlushMode(FlushModeType.COMMIT)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushAutoNativeSQLTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Cat(null, 4, "Kudablya"));
        entityManager.persist(new Cat(null, 3, "kitten"));
        entityManager.createNativeQuery( "select * from Cat" )
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    @Ignore
    public void flushManualTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Cat(null, 4, "Kudablya"));
        entityManager.flush();
        Session session = entityManager.unwrap( Session.class);
        session.setHibernateFlushMode( FlushMode.MANUAL );
        assertTrue(((Number) entityManager
                .createQuery("select count(id) from Cat ")
                .getSingleResult()).intValue() == 1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    @Test
    public void flushOperationOrderTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new Cat(null, 2, "Kudablya"));
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Cat cat = entityManager.find( Cat.class, 1L);
        entityManager.remove(cat);

        entityManager.persist( new Cat(null, 3, "Kudablya") );
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
