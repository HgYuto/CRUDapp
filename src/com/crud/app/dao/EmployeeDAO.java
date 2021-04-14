package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Employee;

public interface EmployeeDAO {

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long id);

}
