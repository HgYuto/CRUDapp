package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.app.dao.DepartmentDAO;
import com.crud.app.dao.DepartmentDAOImpl;
import com.crud.app.model.Department;

@WebServlet("/DepartmentController")
public class DepartmentController extends HttpServlet {

	private static String INSERT_DEPARTMENT = "/insertDepartment.jsp";
	private static String LIST_DEPARTMENT = "/listDepartment.jsp";
	private static String UPDATE_DEPARTMENT ="/updateDepartment.jsp";
	private static String INDEX ="/index.jsp";

	String forward;
	String result;
	String errM = "";
	DepartmentDAO departmentDAO;
	Department department = new Department();

	public DepartmentController() throws FileNotFoundException, IOException {

		super();

		try {
			departmentDAO = new DepartmentDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			errM = "接続エラー：ネットワーク不良";
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
			//表示画面からのパラメーターを取得
			String action = request.getParameter("action");
			//エラー文リセット
			errM = "";

			if (action.equals("insertFace")) {
				forward = INSERT_DEPARTMENT;
			}
			if (action.equals("updateFace")||action.equals("delete")) {
				String cust_code = request.getParameter("code");
				String dept_code = request.getParameter("deptCode");
				//更新画面の移動
				if(action.equals("updateFace")) {
					forward = UPDATE_DEPARTMENT;
					Department department  = departmentDAO.getDepartmentByCode(cust_code,dept_code);
					request.setAttribute("department", department);
				}//消去
				if(action.equals("delete")) {
					forward = LIST_DEPARTMENT;
					department.setCustCode(cust_code);
					department.setDeptCode(dept_code);
					departmentDAO.deleteDepartment(department);
					List<Department> list = departmentDAO.getAllDepartments();
					request.setAttribute("departments", list);
				}
			}
			if (action.equals("list")) {
				forward = LIST_DEPARTMENT;
				List<Department> list = departmentDAO.getAllDepartments();
				request.setAttribute("departments", list);
			}
			if(action.equals("face")) {
				forward = INDEX;
			}
		}
		catch(SQLSyntaxErrorException e) {
			e.printStackTrace();
			errM = "構文エラー：データベースへの問い合わせに、不正な構文が検知されました。";
		}
		catch (SQLException e) {
			e.printStackTrace();
			errM = "接続エラー：ネットワーク不良";
		}
		finally {
			request.setAttribute("result", errM);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding("UTF-8");

			department.setCustCode(request.getParameter("cust_code"));
			department.setDeptCode(request.getParameter("dept_code"));
			department.setDeptName1(request.getParameter("dept_name1"));
			department.setDeptName2(request.getParameter("dept_name2"));
			department.setPostCode(request.getParameter("post_code"));
			department.setAddress1(request.getParameter("address1"));
			department.setAddress2(request.getParameter("address2"));
			department.setAddress3(request.getParameter("address3"));
			department.setTel(request.getParameter("tel"));
			department.setChargeName(request.getParameter("charge_name"));
			department.setMail(request.getParameter("mail"));

			//ボタンを押した時の動作
			String action =request.getParameter("action");

			//エラー文リセット
			errM = "";

			//部署全体画面、検索の動作
			if(action.equals("list")||action.equals("search")) {
				forward = LIST_DEPARTMENT;
				if(action.equals("search")) {
					List<Department> list = departmentDAO.searchDepartments(department);
					request.setAttribute("departments", list);
				}
				else{
					List<Department> list = departmentDAO.getAllDepartments();
					request.setAttribute("departments", list);
				}
			}
			//追加画面、追加の動作
			if(action.equals("insert")) {
				int count = departmentDAO. findCount(department);
				if(count > 0) {
					forward = INSERT_DEPARTMENT;
					errM = "取引先コードもしくは、部署コードが重複しています。再度見直してください。";
				}
				else if(count < 0) {
					forward = INSERT_DEPARTMENT;
					errM = "接続エラー：ネットワーク不良";
				}
				else {
					departmentDAO.insertDepartment(department);
					forward = LIST_DEPARTMENT;
					List<Department> list = departmentDAO.getAllDepartments();
					request.setAttribute("departments", list);
				}
			}
			//更新画面、更新の動作
			if(action.equals("update")) {
				departmentDAO.updateDepartment(department);
				forward = LIST_DEPARTMENT;
				List<Department> list = departmentDAO.getAllDepartments();
				request.setAttribute("departments", list);
			}
		}
		catch(SQLSyntaxErrorException e) {
			e.printStackTrace();
			errM = "構文エラー：データベースへの問い合わせに、不正な構文が検知されました。";
		}
		catch (SQLException e) {
			e.printStackTrace();
			errM = "接続エラー：ネットワーク不良";
		}
		finally{
			request.setAttribute("result",errM);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
	}

}