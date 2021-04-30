package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.Department;
import com.crud.app.util.DButil;

public class DepartmentDAOImpl implements DepartmentDAO {

	Connection connection = null;

	public DepartmentDAOImpl() throws FileNotFoundException, IOException {

		try {
			connection = DButil.getConnection();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
				| IOException e) {
			e.printStackTrace();
		}

		System.out.println("connection");
	}

	@Override
	public void insertDepartment(Department department) {

		try {

			String sql = "INSERT INTO M_DEPARTMENT(CUST_CODE,DEPT_CODE,DEPT_NAME1,DEPT_NAME2,POST_CODE,ADDRESS1,ADDRESS2,ADDRESS3,TEL,CHARGE_NAME,MAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, department.getCustCode());
			pst.setString(2, department.getDeptCode());
			pst.setString(3, department.getDeptName1());
			pst.setString(4, department.getDeptName2());
			pst.setString(5, department.getPostCode());
			pst.setString(6, department.getAddress1());
			pst.setString(7, department.getAddress2());
			pst.setString(8, department.getAddress3());
			pst.setString(9, department.getTel());
			pst.setString(10, department.getChargeName());
			pst.setString(11, department.getMail());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("入力完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDepartment(Department department) {

		try {
			String sql = "UPDATE M_DEPARTMENT SET DEPT_CODE = ? ,DEPT_NAME1 = ? ,DEPT_NAME2 = ?,POST_CODE = ? ,ADDRESS1 = ?,ADDRESS2 = ?,ADDRESS3 = ?,TEL = ?,CHARGE_NAME = ?,MAIL = ? WHERE CUST_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1,department.getDeptCode());
			pst.setString(2, department.getDeptName1());
			pst.setString(3, department.getDeptName2());
			pst.setString(4, department.getPostCode());
			pst.setString(5, department.getAddress1());
			pst.setString(6, department.getAddress2());
			pst.setString(7, department.getAddress3());
			pst.setString(8, department.getTel());
			pst.setString(9, department.getChargeName());
			pst.setString(10, department.getMail());
			pst.setString(11, department.getCustCode());


			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("更新成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDepartment(Department department) {

		try {

			String sql = "DELETE FROM M_DEPARTMENT WHERE CUST_CODE = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, department.getCustCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("削除完了");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Department> getAllDepartments() {

		List<Department> departments = new ArrayList<Department>();

		try {

			String sql = "SELECT * FROM M_DEPARTMENT ";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Department department = new Department();
				//インデクス番号、値
				department.setCustCode(rs.getString(1));
				department.setDeptCode(rs.getString(2));
				department.setDeptName1(rs.getString(3));
				department.setDeptName2(rs.getString(4));
				department.setPostCode(rs.getString(5));
				department.setAddress1(rs.getString(6));
				department.setAddress1(rs.getString(7));
				department.setAddress3(rs.getString(8));
				department.setTel(rs.getString(9));
				department.setChargeName(rs.getString(10));
				department.setMail(rs.getString(11));

				departments.add(department);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return departments;
	}

	@Override
	public Department getDepartmentByCode(String cust_code2) {

		Department department = null;

		try {

			String sql = "SELECT * FROM M_DEPARTMENT WHERE CUST_CODE = ? ";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, cust_code2);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				department = new Department();
				department.setCustCode(rs.getString(1));
				department.setDeptCode(rs.getString(2));
				department.setDeptName1(rs.getString(3));
				department.setDeptName2(rs.getString(4));
				department.setPostCode(rs.getString(5));
				department.setAddress1(rs.getString(6));
				department.setAddress1(rs.getString(7));
				department.setAddress3(rs.getString(8));
				department.setTel(rs.getString(9));
				department.setChargeName(rs.getString(10));
				department.setMail(rs.getString(11));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return department;
	}

}