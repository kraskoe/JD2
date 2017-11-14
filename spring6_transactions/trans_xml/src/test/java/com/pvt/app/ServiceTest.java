package com.pvt.app;

import com.pvt.app.rest.entity.Employee;
import com.pvt.app.services.EmployeeService;
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
    private EmployeeService employeeService;

    @Test
    public void saveTest() {
        Employee e = new Employee();
        e.setFirstName("Yulij I");
        e.setLastName("Slabko");
        e = employeeService.add(e);

        Assert.assertEquals("Yulij I", employeeService.get(e.getId()).getFirstName());
    }
}
