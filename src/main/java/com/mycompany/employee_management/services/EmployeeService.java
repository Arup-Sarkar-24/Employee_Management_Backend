package com.mycompany.employee_management.services;

import com.mycompany.employee_management.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
}
