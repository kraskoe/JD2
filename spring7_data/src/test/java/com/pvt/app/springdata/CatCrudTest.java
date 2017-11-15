package com.pvt.app.springdata;

import com.pvt.app.entities.Cat;
import com.pvt.app.repository.CatCrudRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-data.xml")
public class CatCrudTest {
    @Autowired
    CatCrudRepository catRepository;

    @Before
    public void init() {
        catRepository.save(new Cat(null, "Matis", 11));
        catRepository.save(new Cat(null, "Proshka", 3));
        catRepository.save(new Cat(null, "Tomas", 4));
        catRepository.save(new Cat(null, "Geek", 1));
        catRepository.save(new Cat(null, "Grom", 2));
        catRepository.save(new Cat(null, "Basya", 7));
        catRepository.save(new Cat(null, "Masya", 2));
    }

    @Test
    public void queryMethodTest() {
        catRepository.findByName("Matis").forEach(System.out::println);
        catRepository.findByAgeBetweenAndNameEndingWith(3, 8, "a").forEach(System.out::println);
        catRepository.findByOrderByNameDesc().forEach(System.out::println);
        catRepository.findByNameContaining("Ma").forEach(System.out::println);
        catRepository.findByNameLike("Ma%").forEach(System.out::println);
    }

    @Test
	public void crudRepositoryTest() {
        System.out.println(catRepository.existsById(1L));
        Cat cat = catRepository.findById(1L).orElseGet(null);
        System.out.println(cat);
        cat.setAge(12);
        catRepository.save(cat);
        System.out.println(cat);
        Cat boris = catRepository.save(new Cat(null, "Boris", 3));
        catRepository.delete(boris);
        catRepository.findByName("Matis").forEach(System.out::println);
    }
}
