package com.pvt.app;

import com.pvt.app.pojos.Cat;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson3Test extends PrepareTest {
    @Test
    public void saveCatsTest(){
        Cat cat1 = new Cat(null, 5, "Kudablya", null);
        Cat cat2 = new Cat(null, 2, "Murzik", null);
        Cat cat3 = new Cat(null, 7, "Rizhik", null);
        Cat cat4 = new Cat(null, 3, "Zhirik", null);
        sf.getTransaction().begin();
        sf.save(cat1);
        sf.save(cat2);
        sf.save(cat3);
        sf.save(cat4);
        sf.getTransaction().commit();
    }

}
