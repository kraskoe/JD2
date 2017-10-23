package com.pvt.app;

import com.pvt.app.pojos.*;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson4MappedSuperClassTest extends PrepareTest {
    @Test
    public void CRUDTest(){
        Cat cat1 = new Cat(5, "Kudablya");
        Cat cat2 = new Cat(2, "Murzik");
        Cat cat3 = new Cat(7, "Rizhik");
        Cat cat4 = new Cat(3, "Zhirik");
        Dog dog = new Dog(5, "NewDog");
        Hamster hamster = new Hamster(1, "NewHamster");
        em.getTransaction().begin();
        em.persist(cat1);
        em.persist(cat2);
        em.persist(cat3);
        em.persist(cat4);
        em.persist(dog);
        cat1.setName("NewCat");
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.detach(dog);
        dog.setAge(6);
        em.merge(dog);
        Cat catFromDB = em.find(Cat.class, cat3.getId());
        catFromDB.setName("CatFromDB");
        cat3 = em.merge(catFromDB);
        em.remove(cat4);
        em.getTransaction().commit();
    }
}
