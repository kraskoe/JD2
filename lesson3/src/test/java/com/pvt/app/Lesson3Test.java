package com.pvt.app;

import com.pvt.app.pojos.*;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson3Test extends PrepareTest {
    @Test
    public void saveCatsTest(){
        Cat cat1 = new Cat(null, 5, "Kudablya", new VetVisit());
        Cat cat2 = new Cat(null, 2, "Murzik", new VetVisit());
        Cat cat3 = new Cat(null, 7, "Rizhik", new VetVisit());
        Cat cat4 = new Cat(null, 3, "Zhirik", new VetVisit());
        Dog dog = new Dog(null, 5, "NewDog", null, null);
        Hamster hamster = new Hamster(null, 1, "NewHamster");
        Parrot parrot = new Parrot(null, 1, "NewParrot");
        Parrot parrot2 = new Parrot(null, 1, "NewParrot2");
        Parrot parrot3 = new Parrot(null, 1, "NewParrot3");
        Parrot parrot4 = new Parrot(null, 1, "NewParrot4");
        WhiteRat whiteRat = new WhiteRat(null, 1, "NewWhiteRat");

//        sf.getTransaction().begin();
//        sf.save(cat1);
//        sf.save(cat2);
//        sf.save(cat3);
//        sf.save(cat4);
//        sf.getTransaction().commit();
        em.getTransaction().begin();
        em.persist(cat1);
        em.persist(cat2);
        em.persist(cat3);
        em.persist(cat4);
        em.persist(dog);
        em.persist(parrot);
        em.persist(parrot2);
        em.persist(parrot3);
        em.persist(parrot4);
        em.persist(hamster);
        em.persist(whiteRat);
        System.out.println("   <<<Cat1 ID: " + cat1.getId());
        System.out.println("   <<<Dog ID: " + dog.getId());
        System.out.println("   <<<Parrot ID: " + parrot.getId());
        System.out.println("   <<<Hamster ID: " + hamster.getId());
        System.out.println("   <<<WhiteRat ID: " + whiteRat.getId());
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.detach(dog);
        dog.setAge(6);
        em.merge(dog);
        em.getTransaction().commit();
    }
}
