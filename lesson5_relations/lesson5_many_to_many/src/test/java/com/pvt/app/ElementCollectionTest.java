package com.pvt.app;

import com.pvt.app.entity.User;
import com.pvt.app.util.EMUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class ElementCollectionTest extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(ElementCollectionTest.class);
    @Test
    public void saveTest() {
        em.getTransaction().begin();
        User user = new User(null, "Tim", Stream.of("Cat", "Dog", "Hamster").collect(Collectors.toList()));
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
        User userFromDB = em.find(User.class, user.getId());
        List<String> pets = userFromDB.getPets();
        for (String p:pets) {
            logger.error(p);
        }
    }
}
