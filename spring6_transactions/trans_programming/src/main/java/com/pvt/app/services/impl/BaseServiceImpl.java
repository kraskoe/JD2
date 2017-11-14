package com.pvt.app.services.impl;

import com.pvt.app.rest.dao.Dao;
import com.pvt.app.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.io.Serializable;

@Service
public class BaseServiceImpl<T> implements IService<T> {
    @Autowired
    private Dao<T> baseDao;
    @Autowired
    TransactionTemplate transactionTemplate;
    @Override
    public T add(T t) {
        return transactionTemplate.execute(new TransactionCallback<T>() {
            public T doInTransaction(TransactionStatus transactionStatus) {
                try {
                    return baseDao.add(t);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }
    @Override
    public T update(T t) {
        return transactionTemplate.execute(new TransactionCallback<T>() {
            public T doInTransaction(TransactionStatus transactionStatus) {
                try {
                    return baseDao.update(t);
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return null;
            }
        });
    }
    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED)
    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}
