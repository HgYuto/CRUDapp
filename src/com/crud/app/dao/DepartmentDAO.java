package com.crud.app.dao;

import java.util.List;

import com.crud.app.model.Department;

public interface DepartmentDAO {

	void insertDepartment(Department department);

	void updateDepartment(Department department);

	void deleteDepartment(Department department);

	List<Department> getAllDepartments();

	Department getDepartmentByCode(String cust_code2);

}