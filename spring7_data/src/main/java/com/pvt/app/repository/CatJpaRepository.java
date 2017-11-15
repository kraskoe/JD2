package com.pvt.app.repository;

import com.pvt.app.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatJpaRepository extends JpaRepository<Cat, Long> {
}
