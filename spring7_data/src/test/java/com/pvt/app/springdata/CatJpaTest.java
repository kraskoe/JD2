package com.pvt.app.springdata;

import com.pvt.app.entities.Cat;
import com.pvt.app.repository.CatJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-data.xml")
public class CatJpaTest {
    @Autowired
    CatJpaRepository catRepository;
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
	public void jpaRepositoryTest() {
        System.out.println(catRepository.existsById(1L));
        Cat cat = catRepository.getOne(1L);
        System.out.println(cat);
        cat.setName("New name" +cat.getName());
        catRepository.saveAndFlush(cat);
        Cat newCat = catRepository.getOne(1L);
        System.out.println(newCat);
    }
}
