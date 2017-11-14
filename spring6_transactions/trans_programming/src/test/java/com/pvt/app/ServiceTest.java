package com.pvt.app;

import com.pvt.app.rest.entity.Employee;
import com.pvt.app.services.impl.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-service.xml")
public class ServiceTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    public void saveTest() {
        Employee e = new Employee();
        e.setFirstName("Yulij");
        e.setLastName("Slabko");
        e = employeeService.add(e);

        Assert.assertEquals("Yulij", employeeService.get(e.getId()).getFirstName());
    }

    @Test
    public void updateTest() {
        Employee e = new Employee();
        e.setFirstName("NewEmployee");
        e.setLastName("NewSurname");
        e = employeeService.add(e);
        e.setFirstName("SomeEmployee");
        e = employeeService.update(e);

        Assert.assertEquals("SomeEmployee", employeeService.get(e.getId()).getFirstName());
    }

    @Test
    public void getTest() {
        Employee e = new Employee();
        e.setFirstName("NewEmployee");
        e.setLastName("NewSurname");
        e = employeeService.add(e);
        System.out.println(employeeService.get(e.getId()));
    }

    @Test
    public void deleteTest() {
        Employee e = new Employee();
        e.setFirstName("NewEmployee");
        e.setLastName("NewSurname");
        e = employeeService.add(e);
        employeeService.delete(e.getId());
    }
}
