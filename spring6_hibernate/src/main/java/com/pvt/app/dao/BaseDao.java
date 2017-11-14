package com.pvt.app.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

/**
 * Created by Yauheni Krasko on 14.11.2017.
 */
public class BaseDao<T> implements Dao<T> {
    Class<T> clazz;
    ThreadLocal<EntityManager> em = new ThreadLocal<>();
    @Autowired
    private EntityManagerFactory factory;

    @Override
    public T add(T t) {
        begin();
        getEm().persist(t);
        commit();
        return t;
    }
    @Override
    public T get(Serializable id) {
        return getEm().find(clazz, id);
    }
    @Override
    public T update(T t) {
        begin();
        getEm().merge(t);
        commit();
        return t;
    }
    @Override
    public void delete(Serializable id) {
        begin();
        T t = getEm().find(clazz, id);
        getEm().remove(t);
        commit();
    }

    public EntityManager getEm() {
        if (em.get() == null) {
            em.set(factory.createEntityManager());
        }
        return em.get();
    }
    public void begin() {
        getEm().getTransaction().begin();
    }
    public void commit() {
        getEm().getTransaction().commit();
    }
}
