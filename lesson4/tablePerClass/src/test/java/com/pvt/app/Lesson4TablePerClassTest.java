package com.pvt.app;

import com.pvt.app.pojos.*;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson4TablePerClassTest extends PrepareTest {
    @SuppressWarnings("Duplicates")
    @Test
    public void CRUDTest(){
        Animal animal = new Animal(null, 5, "NewAnimal");
        Cat cat = new Cat(null, 3, "NewCat", 1.5d, true);
        Dog dog = new Dog(null, 2, "NewDog",3.2d,"Barbos");

        em.getTransaction().begin();
        em.persist(animal);
        em.persist(cat);
        em.persist(dog);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Cat catFromDB = em.find(Cat.class, cat.getId());
        catFromDB.setJumpHeight(2.1);
        cat = em.merge(catFromDB);
        System.out.println(cat);
        Dog newDog = new Dog(null, 3, "NotDeletedDog",4.2d,"Dalmatian");
        em.persist(newDog);
        em.remove(dog);
        em.getTransaction().commit();
    }


}
