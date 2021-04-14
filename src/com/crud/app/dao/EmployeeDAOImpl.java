package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.Employee;
import com.crud.app.util.DButil;

public class EmployeeDAOImpl implements EmployeeDAO {

	Connection connection = null;

	public EmployeeDAOImpl() throws FileNotFoundException, IOException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			e.printStackTrace();
		}

		System.out.println("connectionnnection");
	}

	@Override
	public void addEmployee(Employee employee) {

		try {

			String sql = "INSERT INTO Employee(name) values ( ?)";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, employee.getEmployeeName());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("入力完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee employee) {

		try {
			String sql = "UPDATE Employee SET NAME = ? WHERE id = ?";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, employee.getEmployeeName());
			pst.setLong(2, employee.getEmployeeId());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("更新成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(Employee employee) {

		try {

			String sql = "DELETE FROM Employee WHERE id = ? ";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1, employee.getEmployeeId());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("削除完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<Employee>();

		try {

			String sql = "SELECT * FROM Employee ";

			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Employee employee = new Employee();
				//インデクス番号、値
				employee.setEmployeeId(rs.getLong(1));
				employee.setEmployeeName(rs.getString(2));

				employees.add(employee);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(long id) {

		Employee employee = null;

		try {

			String sql = "SELECT * FROM Employee WHERE id = ? ";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				employee = new Employee();
				employee.setEmployeeId(rs.getLong(1));
				employee.setEmployeeName(rs.getString(2));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

}