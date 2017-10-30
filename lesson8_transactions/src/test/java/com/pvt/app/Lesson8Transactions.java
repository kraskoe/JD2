package com.pvt.app;

import com.pvt.app.entity.Student;
import com.pvt.app.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson8Transactions extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson8Transactions.class.getName());

    @SuppressWarnings("Duplicates")
    @Before
    public void initTest() {
        em.getTransaction().begin();
        User user1 = new User(null, "Vasya", "Vasev", 30, 1.8);
        User user2 = new User(null, "Ilya", "Solovyev", 20, 1.6);
        User user3 = new User(null, "Vasya", "Pupkin", 43, 1.9);
        User user4 = new User(null, "Sergey", "Shmatko", 32, 1.6);
        User user5 = new User(null, "Artem", "Kirzachev", 27, 1.7);
        User user6 = new User(null, "Nikolay", "Popov", 15, 1.8);
        User user7 = new User(null, "Sasha", "Popov", 24, 1.7);
        User user8 = new User(null, "Sergey", "Salin", 36, 1.8);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.persist(user6);
        em.persist(user7);
        em.persist(user8);

        em.getTransaction().commit();
    }

    @Test
    public void optimisticLockingVersionTest(){
        em.getTransaction().begin();
        Student student1 = new Student(null, "Vasya", "Vasev", 30, 1.8, null);
        Student student2 = new Student(null, "Ilya", "Solovyev", 20, 1.6, null);
        Student student3 = new Student(null, "Vasya", "Pupkin", 43, 1.9, null);
        em.persist(student1);
        em.persist(student2);
        em.persist(student3);
        em.getTransaction().commit();

    }

}
