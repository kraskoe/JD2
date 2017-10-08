package com.pvt.app.loader;

import com.pvt.app.dao.CatDao;
import com.pvt.app.pojos.Cat;
import com.pvt.app.util.EMUtil;

import javax.persistence.EntityManager;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class CatLoader {
    public static void main(String[] args) {
        Cat cat = new Cat(null, 2, "Murzik");
        CatDao catDao = new CatDao();
        EntityManager em = EMUtil.getEntityManager();
        catDao.save(cat);
        System.out.println("    1 save test: " + catDao.get(1L));
        cat.setAge(3);
        catDao.update(cat);
        System.out.println("    2 update test: " + catDao.get(1L));
        catDao.delete(1L);
        System.out.println("    3 delete test: " + catDao.get(1L));
        EMUtil.close();
    }
}
