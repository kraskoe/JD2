package com.pvt.app;

import com.pvt.app.entity.Person;
import com.pvt.app.entity.Pupil;
import com.pvt.app.entity.Student;
import com.pvt.app.entity.User;
import com.pvt.app.util.EMUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson8OptimisticJDBCTransactions extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson8OptimisticJDBCTransactions.class.getName());

    @Test
    public void saveVersionTest(){
        User userFromDB = em.find(User.class, 3L);
        System.out.println(userFromDB.getVersion());
        Assert.assertNotNull(userFromDB.getVersion());
    }

//    @Source don't work? Version is included in query                                                                  !!!
    @Test
    public void saveTimestampVersionTest(){
        em.getTransaction().begin();
        Student student = new Student(null, "Vasya", "Vasev", 30, 1.8, 100, null);
        em.persist(student);
        em.getTransaction().commit();
        System.out.println(student.getVersion());
        Assert.assertNotNull(student.getVersion());
    }

//    Version Timestamp update works only with Source = DB, but all batch operations have the same timestamp            !!!
    @Test
    public void lockExcludedTest(){
        em.getTransaction().begin();
        Student student = new Student(null, "Vasya", "Vasev", 30, 1.8, 100, null);
        em.persist(student);
        em.getTransaction().commit();
        System.out.println("-----Version after persist: " + student.getVersion());
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            System.out.println("IE!");
        }
//        em.getTransaction().begin();
//        student.setNumberOfLikes(101);
//        em.getTransaction().commit();
//        System.out.println("-----Version after lockExcluded change: " + student.getVersion());
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ie) {
//            System.out.println("IE!");
//        }
        em.getTransaction().begin();
        student.setHeight(1.9);
        em.getTransaction().commit();
        System.out.println("-----Version after change: " + student.getVersion());
    }

    @Test
    public void optimisticLockVersionLockExcludedTest(){
        em.getTransaction().begin();
        Student student = new Student(null, "Vasya", "Vasev", 30, 1.8, 100, null);
        em.persist(student);
        em.getTransaction().commit();
        System.out.println("-----Version after persist: " + student.getVersion());

        em.getTransaction().begin();
        student.setHeight(1.9);

        new Thread(() -> {
            EntityManager entityManager = EMUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Student studentfromDB = entityManager.find(Student.class, student.getId());
            studentfromDB.setNumberOfLikes(101);
            entityManager.getTransaction().commit();
            System.out.println("-----Version after lockExcluded change: " + studentfromDB.getVersion());
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            System.out.println("IE!");
        }
        em.getTransaction().commit();
        System.out.println("-----Version after change: " + student.getVersion());
    }

    @Test
    public void optimisticLockVersionTest(){
        em.getTransaction().begin();
        Student student = new Student(null, "Vasya", "Vasev", 30, 1.8, 100, null);
        em.persist(student);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + student);
        try {
            em.getTransaction().begin();
            student.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                Student studentfromDB = entityManager.find(Student.class, student.getId());
                studentfromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(Student.class, student.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(Student.class, student.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(Student.class, student.getId()));
        }
    }

    @Test
    public void optimisticLockAllWriteTest(){
        em.getTransaction().begin();
        Person person = new Person(null, "Vasya", "Vasev", 30, 1.8, 100);
        em.persist(person);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + person);

        try {
            em.getTransaction().begin();
            person.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                Person personFromDB = entityManager.find(Person.class, person.getId());
                personFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(Person.class, person.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(Person.class, person.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(Person.class, person.getId()));
        }
    }

    @Test
    public void optimisticLockAllReadTest(){
        em.getTransaction().begin();
        Person person = new Person(null, "Vasya", "Vasev", 30, 1.8, 100);
        em.persist(person);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + person);

        try {
            em.getTransaction().begin();
            Person personFDB = em.find(Person.class, 1L);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                Person personFromDB = entityManager.find(Person.class, person.getId());
                personFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after change: " + entityManager.find(Person.class, 1L));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            em.clear();
            System.out.println("-----Entity after second change: " + em.find(Person.class, 1L));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(Person.class, person.getId()));
        }
    }

    @Test
    public void optimisticLockDirtyWriteTest(){
        em.getTransaction().begin();
        Pupil person = new Pupil(null, "Vasya", "Vasev", 30, 1.8, 100);
        em.persist(person);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + person);

        try {
            em.getTransaction().begin();
            person.setAge(40);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                Pupil personFromDB = entityManager.find(Pupil.class, person.getId());
                personFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(Pupil.class, person.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(Pupil.class, person.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(Pupil.class, person.getId()));
        }
    }

    @Test
    public void optimisticLockDirtyWriteCorrectTest(){
        em.getTransaction().begin();
        Pupil person = new Pupil(null, "Vasya", "Vasev", 30, 1.8, 100);
        em.persist(person);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + person);

        try {
            em.getTransaction().begin();
            person.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                Pupil personFromDB = entityManager.find(Pupil.class, person.getId());
                personFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(Pupil.class, person.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(Pupil.class, person.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(Pupil.class, person.getId()));
        }
    }
}
