package com.crud.app.dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import com.crud.app.model.Department;

public interface DepartmentDAO {

	int findCount(Department department);

	void insertDepartment(Department department)throws SQLSyntaxErrorException,SQLException;

	void updateDepartment(Department department)throws SQLSyntaxErrorException,SQLException;

	void deleteDepartment(Department department)throws SQLSyntaxErrorException,SQLException;

	List<Department> getAllDepartments()throws SQLSyntaxErrorException,SQLException;

	String decisionWhere(String sql);

	List<Department> searchDepartments(Department department);

	Department getDepartmentByCode(String cust_code, String dept_code)throws SQLSyntaxErrorException,SQLException;

}