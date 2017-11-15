package com.pvt.app.repository;

import com.pvt.app.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select distinct(d) from Department d join d.employees e where e.firstName = ?1")
    List<Department> getByJoinCondition(String name);
    @Query("select d from Department d join d.employees e where e.age = (select max(e.age) from Employee e)")
    List<Department> getByMaxAge();
    @Query("select d from Department d join d.employees e where e.lastName = :lastName")
    List<Department> getByEmployeesLastName(@Param("lastName") String familyName);

    @Query(value = " select d.* from Department d" +
                   " left join Employee e on d.id = e.department_id" +
                   " where e.firstName = ?1", nativeQuery = true)
    List<Department> getByJoinConditionNative(String name);

}
