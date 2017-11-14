package com.pvt.app.rest.dao;

import com.pvt.app.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {
    List<Employee> getEmployee();
}
