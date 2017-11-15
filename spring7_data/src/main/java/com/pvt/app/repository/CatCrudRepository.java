package com.pvt.app.repository;

import com.pvt.app.entities.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatCrudRepository extends CrudRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByAgeBetweenAndNameEndingWith(int arg1, int arg2, String nameEndWith);
    List<Cat> findByOrderByNameDesc();
    List<Cat> findByNameContaining(String pattern);
    List<Cat> findByNameLike(String pattern);
}
