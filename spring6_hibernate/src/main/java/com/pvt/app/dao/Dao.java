package com.pvt.app.dao;

import java.io.Serializable;

/**
 * Created by Yauheni Krasko on 14.11.2017.
 */
public interface Dao<T> {
    T add(T t);

    T update(T t);

    T get(Serializable id);

    void delete(Serializable id);

}
