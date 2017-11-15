package com.pvt.app.repository;

import com.pvt.app.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeePagingRepository extends PagingAndSortingRepository<Employee, Long> {
    Page<Employee> findByDepartmentIdIn(List<Long> ids, Pageable pageable);
    Page<Employee> findByDepartmentId(Long id, Pageable pageable);
    Page<Employee> findByFirstName(String name, Pageable pageable);
}
