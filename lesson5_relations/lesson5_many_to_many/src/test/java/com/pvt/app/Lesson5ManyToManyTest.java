package com.pvt.app;

import com.pvt.app.pojos.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson5ManyToManyTest extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson5ManyToManyTest.class);

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
        Animal a1 = new Animal(null, 1, "Animal#1", null, zoo1, new ArrayList<>());
        Animal a2 = new Animal(null, 2, "Animal#2", null, zoo1, new ArrayList<>());
        Animal a3 = new Animal(null, 3, "Animal#3", null, zoo2, new ArrayList<>());
        Animal a4 = new Animal(null, 4, "Animal#4", null, zoo2, new ArrayList<>());
        Animal a5 = new Animal(null, 5, "Animal#5", null, zoo2, new ArrayList<>());
        Animal a6 = new Animal(null, 6, "Animal#6", null, zoo3, new ArrayList<>());
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(a4);
        em.persist(a5);
        em.persist(a6);
        Procedure proc1 = new Procedure("bath");
        Procedure proc2 = new Procedure("massage");
        a1.getProcedures().add(proc1);
        a3.getProcedures().add(proc2);
        a4.getProcedures().add(proc2);
        a5.getProcedures().add(proc1);
        a5.getProcedures().add(proc2);
        a6.getProcedures().add(proc1);
        proc1.getAnimals().add(a1);
        proc1.getAnimals().add(a5);
        proc1.getAnimals().add(a6);
        proc2.getAnimals().add(a3);
        proc2.getAnimals().add(a4);
        proc2.getAnimals().add(a5);
        em.persist(proc1);
        em.persist(proc2);
        em.getTransaction().commit();
    }

    @Test
    public void saveTest() {
        em.getTransaction().begin();
        Procedure proc3 = new Procedure("banana-eating");
        Animal a7 = new Animal(null, 7, "Animal#7", null, null, new ArrayList<>());
        proc3.getAnimals().add(a7);
        a7.getProcedures().add(proc3);
        em.persist(a7);
        em.getTransaction().commit();
    }

    @Test
    public void deletetest(){
        em.getTransaction().begin();
        Procedure proc3 = new Procedure("banana-eating");
        Animal a7 = new Animal(null, 7, "Animal#7", null, null, new ArrayList<>());
        proc3.getAnimals().add(a7);
        a7.getProcedures().add(proc3);
        em.persist(a7);
        for (Procedure p:a7.getProcedures()) {
            p.getAnimals().remove(a7);
        }
        em.remove(a7);
        em.getTransaction().commit();
    }
}
