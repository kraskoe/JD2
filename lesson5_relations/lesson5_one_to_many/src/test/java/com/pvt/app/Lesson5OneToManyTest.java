package com.pvt.app;

import com.pvt.app.pojos.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson5OneToManyTest extends PrepareTest {
    @Before
    public void initTest() {
        em.getTransaction().begin();
        Zoo zoo1 = new Zoo();
        zoo1.setName("Zoo#1");
        Zoo zoo2 = new Zoo();
        zoo2.setName("Zoo#2");
        Zoo zoo3 = new Zoo();
        zoo3.setName("Zoo#3");
        em.persist(zoo1);
        em.persist(zoo2);
        em.persist(zoo3);
        em.persist(new Animal(null, 1, "Animal#1", null, zoo1));
        em.persist(new Animal(null, 2, "Animal#2", null, zoo1));
        em.persist(new Animal(null, 3, "Animal#3", null, zoo2));
        em.persist(new Animal(null, 4, "Animal#4", null, zoo2));
        em.persist(new Animal(null, 5, "Animal#5", null, zoo2));
        em.persist(new Animal(null, 6, "Animal#6", null, zoo3));
        em.getTransaction().commit();
    }

    @Test
    public void persistTest() {
        em.getTransaction().begin();
        Zoo zoo4 = new Zoo(null, "Zoo#4", null);
        em.persist(zoo4);
        Animal animal7 = new Animal(null, 7, "Animal#7", null, zoo4);
        Cat cat1 = new Cat(null, 2.3d, true, null);
        animal7.setCat(cat1);
        cat1.setAnimal(animal7);
        Animal animal8 = new Animal(null, 8, "Animal#8", null, zoo4);
        Cat cat2 = new Cat(null, 1.3d, true, null);
        animal8.setCat(cat2);
        cat2.setAnimal(animal8);
        Animal animal9 = new Animal(null, 9, "Animal#9", null, null);
        Cat cat3 = new Cat(null, 3.3d, true, null);
        animal9.setCat(cat3);
        cat3.setAnimal(animal9);
        em.persist(animal7);
        em.persist(animal8);
        em.persist(animal9);
        animal9.setZoo(zoo4);
        em.getTransaction().commit();
    }
}
