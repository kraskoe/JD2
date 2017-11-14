package com.pvt.app.services.impl;

import com.pvt.app.rest.dao.Dao;
import com.pvt.app.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class BaseService<T> implements IService<T> {
    @Autowired
    private Dao<T> baseDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T add(T t) {
        return baseDao.add(t);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T update(T t) {
        return null;
    }
    @Override
    @Transactional(
            propagation = Propagation.SUPPORTS,
            readOnly = true,
            timeout = 60
    )
    public T get(Serializable id) {
        return baseDao.get(id);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}
