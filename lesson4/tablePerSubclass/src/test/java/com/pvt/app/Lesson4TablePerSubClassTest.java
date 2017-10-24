package com.pvt.app;

import com.pvt.app.pojos.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson4TablePerSubClassTest extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson4TablePerSubClassTest.class);

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

    @Test
    public void polymorphismTest(){
        Animal animal1 = new Animal(null, 5, "NewAnimal1");
        Animal animal2 = new Animal(null, 7, "NewAnimal2");
        Cat cat1 = new Cat(null, 3, "NewCat1", 1.6d, true);
        Cat cat2 = new Cat(null, 2, "NewCat2", 1.5d, true);
        Dog dog1 = new Dog(null, 2, "NewDog1",3.5d,"Barbos");
        Dog dog2 = new Dog(null, 4, "NewDog2",3.2d,"Barbos");
        Hamster hamster1 = new Hamster(null, 2, "NewHamster1");
        Hamster hamster2 = new Hamster(null, 3, "NewHamster2");
        Parrot parrot1 = new Parrot(null, 1, "NewParrot1");
        Parrot parrot2 = new Parrot(null, 4, "NewParrot2");

        em.getTransaction().begin();
        em.persist(animal1);
        em.persist(animal2);
        em.persist(cat1);
        em.persist(cat2);
        em.persist(dog1);
        em.persist(dog2);
        em.persist(hamster1);
        em.persist(hamster2);
        em.persist(parrot1);
        em.persist(parrot2);
        em.getTransaction().commit();

        List<Mammal> mammals = em.createQuery("select m from com.pvt.app.pojos.Mammal m").getResultList();
        for (Mammal m : mammals) {
            logger.error(m);
        }

//        Assert.assertEquals(mammals.size(), 4);
    }

}
