package com.pvt.app;

import com.pvt.app.entity.Person;
import com.pvt.app.entity.Pupil;
import com.pvt.app.entity.Student;
import com.pvt.app.entity.User;
import com.pvt.app.util.EMUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.RollbackException;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson8JPATransactions extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson8JPATransactions.class.getName());

    @SuppressWarnings("Duplicates")
    @Before
    public void initTest() {
        em.getTransaction().begin();
        User user1 = new User(null, "Vasya", "Vasev", 30, 1.8, null);
        em.persist(user1);
        em.getTransaction().commit();
    }

    @Test
    public void optimisticLockOptimisticWriteTest(){
        em.getTransaction().begin();
        User user = em.find(User.class, 1L);
//        User user = em.find(User.class, 1L, LockModeType.OPTIMISTIC);
//        User user = em.find(User.class, 1L);
//        em.lock(user, LockModeType.OPTIMISTIC);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();
            em.lock(user, LockModeType.OPTIMISTIC);
            user.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
//                entityManager.lock(userFromDB, LockModeType.OPTIMISTIC);
                userFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(User.class, user.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, user.getId()));
        }
    }

//    Version doesn't change after second unsuccessful update                                                           !!!
    @Test
    public void optimisticLockOptimisticForceIncrementWriteTest(){
        em.getTransaction().begin();
        User user = em.find(User.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();
            user.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                userFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            em.clear();
            System.out.println("-----Entity after second change: " + em.find(User.class, user.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, user.getId()));
        }
    }

//    Version doesn't change after reading transaction                                                                  !!!
    @Test
    public void optimisticLockOptimisticForceIncrementReadTest(){
        em.getTransaction().begin();
        User user = em.find(User.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();
            user.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + userFromDB);
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            em.clear();
            System.out.println("-----Entity after second change: " + em.find(User.class, user.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, user.getId()));
        }
    }

//    Don't forget to delete @Version before running the above tests!
    @Test
    public void pessimisticLockPessimisticReadTest(){
//        em.getTransaction().begin();
//        User user = em.find(User.class, 1L);
//        em.getTransaction().commit();
//        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();

            User user = em.find(User.class, 1L, LockModeType.PESSIMISTIC_READ);
            System.out.println("-----Entity after persist: " + user);
            user.setHeight(1.9);
//            em.flush();

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after concurrent reading: " + userFromDB);
            }).start();

            new Thread(() -> {
                try {
                    EntityManager entityManager = EMUtil.getEntityManager();
                    entityManager.getTransaction().begin();
                    User userFromDB = entityManager.find(User.class, user.getId());
                    userFromDB.setAge(50);
                    entityManager.getTransaction().commit();
                    System.out.println("-----Entity after first change: " + userFromDB);
                } catch (Exception re) {
                    re.printStackTrace();
                }
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
//            user.setName("NewUser");
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(User.class, 1L));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, 1L));
        }
    }

    @Test
    public void pessimisticLockPessimisticWriteTest(){
        em.getTransaction().begin();
        User user = em.find(User.class, 1L);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();
            em.find(User.class, 1L, LockModeType.PESSIMISTIC_WRITE);
            user.setHeight(1.9);
            em.flush();

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                userFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after concurrent reading: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(User.class, user.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, user.getId()));
        }
    }

//    Default locking doesn't work?                                                                                     !!!
    @Test
    public void defaultLockingTest(){
        em.getTransaction().begin();
        User user = em.find(User.class, 1L);
        em.getTransaction().commit();
        System.out.println("-----Entity after persist: " + user);

        try {
            em.getTransaction().begin();
//            em.setProperty("javax.persistence.lock.timeout", 0);
//            em.find(User.class, 1L);
            user.setHeight(1.9);

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                userFromDB.setAge(50);
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after first change: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }

            new Thread(() -> {
                EntityManager entityManager = EMUtil.getEntityManager();
                entityManager.getTransaction().begin();
                User userFromDB = entityManager.find(User.class, user.getId());
                entityManager.getTransaction().commit();
                System.out.println("-----Entity after concurrent reading: " + entityManager.find(User.class, user.getId()));
            }).start();

            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("IE!");
            }
            em.getTransaction().commit();
            System.out.println("-----Entity after second change: " + em.find(User.class, user.getId()));
        } catch (RollbackException re) {
            re.printStackTrace();
        } finally {
            System.out.println(em.find(User.class, user.getId()));
        }
    }
}
