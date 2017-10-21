package com.pvt.app;

import com.pvt.app.pojos.*;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson5OneToOneTest extends PrepareTest {
    @Test
    public void persistCascadeTest() {
        em.getTransaction().begin();
        Cat cat = new Cat(null, 1.5d, true, null);
        Animal animal = new Animal(null, 5, "NewAnimal", null);
        animal.setCat(cat);
        cat.setAnimal(animal);
        em.persist(animal);
        em.getTransaction().commit();
        em.clear();

        Animal animalFromDB = em.find(Animal.class, animal.getId());
        Assert.assertEquals(animal.getAge(), animalFromDB.getAge());
    }
    @Test
    public void mergeCascadeTest() {
        em.getTransaction().begin();
        Cat cat = new Cat(null, 1.5d, true, null);
        Animal animal = new Animal(null, 5, "NewAnimal", null);
        animal.setCat(cat);
        cat.setAnimal(animal);
        em.persist(animal);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Animal animalFromDB = em.find(Animal.class, animal.getId());
        Cat catFromDB = em.find(Cat.class, animal.getId());
        animalFromDB.setName("AnimalAfterMerge");
        catFromDB.setJumpHeight(5d);
        animal = em.merge(animalFromDB);
        Assert.assertEquals(animal.getName(), "AnimalAfterMerge");
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        catFromDB.setCanCatchMice(false);
        animalFromDB.setName("CatMerged");
        cat = em.merge(catFromDB);
        em.getTransaction().commit();
    }

    @Test
    public void removeCascadeTest() {
        em.getTransaction().begin();
        Cat cat = new Cat(null, 1.5d, true, null);
        Animal animal = new Animal(null, 5, "NewAnimal", null);
        Cat cat2 = new Cat(null, 2.5d, true, null);
        Animal animal2 = new Animal(null, 4, "NewAnimal2", null);
        animal.setCat(cat);
        animal2.setCat(cat2);
        cat.setAnimal(animal);
        cat2.setAnimal(animal2);
        em.persist(animal);
        em.persist(animal2);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Animal animalFromDB = em.find(Animal.class, animal.getId());
        em.remove(animalFromDB);
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Cat catFromDB = em.find(Cat.class, cat2.getId());
        em.remove(catFromDB);
        em.getTransaction().commit();
    }
}
