package com.pvt.app.dao;

import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;
import com.pvt.app.util.SFUtil;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatHibernateDao {
    private static Session session = null;
    static {
        if (session == null) {
            session = SFUtil.getSession();
        }
    }
    public Cat create(Cat cat) {
        session.getTransaction().begin();
        session.save(cat);
        session.getTransaction().commit();
        return cat;
    }
    public Cat readGet(Long id) {
        return session.get(Cat.class, id);
    }
    public Cat readLoad(Long id) {
        Cat catEntity = null;
        try {
            catEntity = session.load(Cat.class, id);
        } catch (ObjectNotFoundException he) {
            System.out.println("Error loading cat from DB " + he);
        } finally {
            return catEntity;
        }
    }
    public void update(Cat cat) {
        session.getTransaction().begin();
        session.update(cat);
        session.getTransaction().commit();
    }
    public void delete(Long id) {
        session.getTransaction().begin();
        Cat tempCat = session.get(Cat.class, id);
        session.delete(tempCat);
        session.getTransaction().commit();
    }
}
