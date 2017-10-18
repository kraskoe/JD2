package com.pvt.app.dao;

import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatJPADao {
    private static EntityManager em = null;
    static {
        if (em == null) {
            em = EMUtil.getEntityManager();
        }
    }
    public Cat create(Cat cat) {
        em.getTransaction().begin();
        em.persist(cat);
        em.getTransaction().commit();
        return cat;
    }
    public Cat read(Long id) {
        return em.find(Cat.class, id);
    }
    public void update(Cat cat) {
        em.getTransaction().begin();
        em.merge(cat);
        em.getTransaction().commit();
    }
    public void delete(Long id) {
        em.getTransaction().begin();
        Cat tempCat = em.find(Cat.class, id);
        em.remove(tempCat);
        em.getTransaction().commit();
    }
    public void close() {
        em.clear();
        em.close();
    }
}
