package com.crud.app.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import com.crud.app.model.Department;
import com.crud.app.util.DButil;

public class DepartmentDAOImpl implements DepartmentDAO {

	Connection connection = null;

	public DepartmentDAOImpl() throws FileNotFoundException, IOException, SQLException {

		try {
			connection = DButil.getConnection();

		}  catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
			System.out.println("unconnection");
		}
	}

	//取引先コードが重複しているか確認。
		@Override
		public int findCount(Department department) {
			try {
				String sql = "SELECT COUNT(*) FROM M_DEPARTMENT MD WHERE MD.CUST_CODE = ? AND MD.DEPT_CODE = ? ;";

				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setString(1, department.getCustCode());
				pst.setString(2, department.getDeptCode());
				ResultSet rs = pst.executeQuery();

				rs.next();
				return rs.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}

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

			if(findCount(department) > 0) {
				department.setErrResult("取引先コードが重複しています。再度見直してください。");
			}
			else if(findCount(department) < 0) {
				department.setErrResult("接続エラー：ネットワーク不良");
			}
			else {
				int res = pst.executeUpdate();
				if(res > 0) {
					department.setErrResult("");
					System.out.println("入力完了");
				}
			}
		}
		catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
			department.setErrResult("構文エラー：データベースへの問い合わせに、不正な構文が検知されました。");
		}
		catch (SQLException e) {
			e.printStackTrace();
			department.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	@Override
	public void updateDepartment(Department department) {

		try {
			String sql = "UPDATE M_DEPARTMENT SET DEPT_NAME1 = ? ,DEPT_NAME2 = ?,POST_CODE = ? ,ADDRESS1 = ?,ADDRESS2 = ?,ADDRESS3 = ?,TEL = ?,CHARGE_NAME = ?,MAIL = ? WHERE CUST_CODE = ? AND DEPT_CODE = ? ;";

			PreparedStatement pst = connection.prepareStatement(sql);
			//インデクス番号、値
			pst.setString(1, department.getDeptName1());
			pst.setString(2, department.getDeptName2());
			pst.setString(3, department.getPostCode());
			pst.setString(4, department.getAddress1());
			pst.setString(5, department.getAddress2());
			pst.setString(6, department.getAddress3());
			pst.setString(7, department.getTel());
			pst.setString(8, department.getChargeName());
			pst.setString(9, department.getMail());
			pst.setString(10, department.getCustCode());
			pst.setString(11, department.getDeptCode());


			int res = pst.executeUpdate();

			if (res > 0) {
				department.setErrResult("");
				System.out.println("更新成功");
			}

		}
		catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
			department.setErrResult("構文エラー：データベースへの問い合わせに、不正な構文が検知されました。");
		}
		catch (SQLException e) {
			e.printStackTrace();
			department.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	@Override
	public void deleteDepartment(Department department) {

		try {

			String sql = "DELETE FROM M_DEPARTMENT WHERE CUST_CODE = ? AND DEPT_CODE = ? ;";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, department.getCustCode());
			pst.setString(2, department.getDeptCode());

			int res = pst.executeUpdate();

			if (res > 0) {
				department.setErrResult("");
				System.out.println("削除完了");
			}

		}
		catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
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
				department.setAddress2(rs.getString(7));
				department.setAddress3(rs.getString(8));
				department.setTel(rs.getString(9));
				department.setChargeName(rs.getString(10));
				department.setMail(rs.getString(11));

				departments.add(department);
				department.setErrResult("");
			}
		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

	public String decisionWhere(String sql) {
		if(sql.contains("WHERE")) {
			return " AND ";
		}else {
			return " WHERE ";
		}
	}

	@Override
	public List<Department> searchDepartments(Department department) {

		List<Department> departments = new ArrayList<Department>();

		try {
			String sql = "SELECT * FROM M_DEPARTMENT ";

			String cc = department.getCustCode();
			String dc = department.getDeptCode();
			String dn1 = department.getDeptName1();
			String dn2 = department.getDeptName2();
			String pc = department.getPostCode();
			String a1 = department.getAddress1();
			String a2 = department.getAddress2();
			String a3 = department.getAddress3();
			String tl = department.getTel();
			String cn = department.getChargeName();
			String ml = department.getMail();


			String[][] sArray = {{"CUST_CODE ","DEPT_CODE ","DEPT_NAME1 ","DEPT_NAME2 ","POST_CODE ","ADDRESS1 ","ADDRESS2 ","ADDRESS3 ","TEL ","CHARGE_NAME ","MAIL "},{cc,dc,dn1,dn2,pc,a1,a2,a3,tl,cn,ml}};
			for(int i = 0 ; sArray[1].length > i ; i++) {
				if(sArray[1][i].isEmpty()){
					;
				}
				else {
					sql += decisionWhere(sql)+ sArray[0][i] + "= \"" + sArray[1][i] + "\"";
				}
			}

			sql += ";";

			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				//インデクス番号、値
				Department departmentCol = new Department();
				departmentCol.setCustCode(rs.getString(1));
				departmentCol.setDeptCode(rs.getString(2));
				departmentCol.setDeptName1(rs.getString(3));
				departmentCol.setDeptName2(rs.getString(4));
				departmentCol.setPostCode(rs.getString(5));
				departmentCol.setAddress1(rs.getString(6));
				departmentCol.setAddress2(rs.getString(7));
				departmentCol.setAddress3(rs.getString(8));
				departmentCol.setTel(rs.getString(9));
				departmentCol.setChargeName(rs.getString(10));
				departmentCol.setMail(rs.getString(11));

				departments.add(departmentCol);
			}
				department.setErrResult("");
				System.out.println("検索成功");

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return departments;
	}
	@Override
	public Department getDepartmentByCode(String cust_code,String dept_code) {

		Department department = null;

		try {

			String sql = "SELECT * FROM M_DEPARTMENT WHERE CUST_CODE = ? AND DEPT_CODE = ? ";

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, cust_code);
			pst.setString(2, dept_code);
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

		} catch (SQLSyntaxErrorException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return department;
	}

}