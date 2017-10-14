package com.pvt.app.loader;

import com.pvt.app.dao.CatJPADao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatLoader {
    public static void main(String[] args) {
        Cat cat = new Cat(null, 2, "Murzik");
        CatJPADao catJPADao = new CatJPADao();
        EntityManager em = EMUtil.getEntityManager();
        catJPADao.create(cat);
        System.out.println("    1 create test: " + catJPADao.read(1L));
        cat.setAge(3);
        catJPADao.update(cat);
        System.out.println("    2 update test: " + catJPADao.read(1L));
        catJPADao.delete(1L);
        System.out.println("    3 delete test: " + catJPADao.read(1L));
        EMUtil.close();
    }
}
