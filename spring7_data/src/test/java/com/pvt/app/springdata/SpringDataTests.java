package com.pvt.app.springdata;

import com.pvt.app.entities.Department;
import com.pvt.app.entities.Employee;
import com.pvt.app.repository.DepartmentRepository;
import com.pvt.app.repository.EmployeePagingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-data.xml")
public class SpringDataTests {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeePagingRepository employeeRepository;

    @Before
    public void init() {
        Department qa = new Department(null, "QA", null);
        Department ba = new Department(null, "BA", null);
        Department hr = new Department(null, "HR", null);
        Department devOps = new Department(null, "DevOps", null);
        Department pm = new Department(null, "PM", null);
        Department al = new Department(null, "AL", null);
        Department buch = new Department(null, "Бухгалтерия", null);
        departmentRepository.save(qa);
        departmentRepository.save(ba);
        departmentRepository.save(hr);
        departmentRepository.save(devOps);
        departmentRepository.save(pm);
        departmentRepository.save(al);
        departmentRepository.save(buch);

        employeeRepository.save(new Employee(null, "Mohammad", "Salek", 25, qa));
        employeeRepository.save(new Employee(null, "Lilia", "Worjec", 31, qa));
        employeeRepository.save(new Employee(null, "Yana", "Kozhedub", 29, hr));
        employeeRepository.save(new Employee(null, "Yuliya", "Samohina", 24, hr));
        employeeRepository.save(new Employee(null, "Gena", "RGB", 28, devOps));
        employeeRepository.save(new Employee(null, "Sasha", "Shi", 45, pm));
        employeeRepository.save(new Employee(null, "Alex", "Trump", 45, al));
        employeeRepository.save(new Employee(null, "Maria", "Shklyar", 37, buch));
        employeeRepository.save(new Employee(null, "Sasha", "Rabushka", 30, ba));
        employeeRepository.save(new Employee(null, "Sasha", "Tverdohlebova", 29, ba));
    }

    @Test
	public void queryTest() {
        departmentRepository.getByJoinCondition("Sasha").forEach(System.out::println);
    }
    @Test
	public void queryParamTest() {
        departmentRepository.getByEmployeesLastName("Shi").forEach(System.out::println);
    }
    @Test
	public void nativeQueryTest() {
        departmentRepository.getByJoinConditionNative("Yana").forEach(System.out::println);
    }
    @Test
	public void maxAgeTest() {
        departmentRepository.getByMaxAge().forEach(System.out::println);
    }

    @Test
	public void pageableTest() {
        List<Long> ids = Stream.of(2L, 3L, 4L).collect(Collectors.toList());
        Page<Employee> employeesPage = employeeRepository.findByDepartmentIdIn(ids, PageRequest.of(1, 3, Sort.Direction.DESC, "age"));
        employeesPage.getContent().forEach(System.out::println);
        employeesPage = employeeRepository.findByFirstName("Sasha", PageRequest.of(0, 2, Sort.Direction.DESC, "age"));
        employeesPage.getContent().forEach(System.out::println);
        employeesPage = employeeRepository.findAll(PageRequest.of(0, 2, Sort.Direction.DESC, "age"));
        employeesPage.getContent().forEach(System.out::println);
        employeesPage = employeeRepository.findByDepartmentId(2L, PageRequest.of(0, 2, Sort.Direction.DESC, "age"));
        employeesPage.getContent().forEach(System.out::println);

        employeeRepository.findAll(new Sort(Sort.Direction.DESC, "department.name")).forEach(System.out::println);
    }

    @Test
	public void findByExampleTest() {
        System.out.println(departmentRepository.findOne(Example.of(new Department(null, "Бухгалтерия", null))));
        List<Department> departments = departmentRepository.findAll(
                Example.of(new Department(null, "терия", null), ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.ENDING)));
        departments.forEach(System.out::print);
    }
}
