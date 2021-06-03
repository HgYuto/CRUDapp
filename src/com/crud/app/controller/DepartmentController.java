package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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

	DepartmentDAO departmentDAO;

	public DepartmentController() throws FileNotFoundException, IOException {

		super();

		departmentDAO = new DepartmentDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_DEPARTMENT;
		}
		if (action.equals("updateFace")) {
			forward = UPDATE_DEPARTMENT;
			String cust_code = request.getParameter("custCode");
			Department department  = departmentDAO.getDepartmentByCode(cust_code);
			request.setAttribute("department", department);
		}
		if (action.equals("list")|| action.equals("delete")) {
			forward = LIST_DEPARTMENT;

			if(action.equals("delete")) {
				String cust_code = request.getParameter("custCode");
				Department department = new Department();
				department.setCustCode(cust_code);
				departmentDAO.deleteDepartment(department);
			}

			List<Department> list = departmentDAO.getAllDepartments();
			request.setAttribute("departments", list);
		}
		if(action.equals("face")) {
			forward = INDEX;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//ボタンを押した時の動作
		Department department = new Department();
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
		//追加、更新のボタン
		if(action.equals("insert")||action.equals("update")||action.equals("list")) {
			if(action.equals("insert")) {
				departmentDAO.insertDepartment(department);
			}
			if(action.equals("update")){
				departmentDAO.updateDepartment(department);
			}
			RequestDispatcher view = request.getRequestDispatcher(LIST_DEPARTMENT);
			List<Department> list = departmentDAO.getAllDepartments();
			request.setAttribute("departments", list);
			view.forward(request, response);
		}
		if(action.equals("search")) {
			RequestDispatcher view = request.getRequestDispatcher(LIST_DEPARTMENT);
			List<Department> list = departmentDAO.searchDepartments(department);
			request.setAttribute("departments", list);
			view.forward(request, response);
		}
		else{
			System.out.println("p通っています。");
		}

	}

}