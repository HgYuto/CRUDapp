package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
	DepartmentDAO departmentDAO;
	Department department = new Department();

	public DepartmentController() throws FileNotFoundException, IOException {

		super();

		try {
			departmentDAO = new DepartmentDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			department.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

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
			else if(action.equals("delete")) {
				forward = LIST_DEPARTMENT;
				department.setCustCode(cust_code);
				department.setCustCode(dept_code);
				departmentDAO.deleteDepartment(department);
				List<Department> list = departmentDAO.getAllDepartments();
				request.setAttribute("departments", list);
			}
		}
		if (action.equals("list")||action.equals("firstList")) {
			if(action.equals("firstList")) {
				department.setErrResult("");
			}
			forward = LIST_DEPARTMENT;
			if(!department.getErrResult().isEmpty() || department.getErrResult() != null) {
				request.setAttribute("result", department.getErrResult());
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
		if(action.equals("insert")||action.equals("backIn")) {
			if(action.equals("insert")) {
				departmentDAO.insertDepartment(department);
				if(department.getErrResult().isEmpty() || department.getErrResult() == null) {
					forward = LIST_DEPARTMENT;
					List<Department> list = departmentDAO.getAllDepartments();
					request.setAttribute("departments", list);
				}else{
					forward = INSERT_DEPARTMENT;
					request.setAttribute("result",department.getErrResult());
				}
			}
			if(action.equals("backIn")) {
				forward = INSERT_DEPARTMENT;

			}
		}
		//更新画面、更新の動作
		if(action.equals("update")||action.equals("backUp")) {
			if(action.equals("update")) {
				departmentDAO.updateDepartment(department);
				if(department.getErrResult().isEmpty() || department.getErrResult() == null) {
					forward = LIST_DEPARTMENT;
					List<Department> list = departmentDAO.getAllDepartments();
					request.setAttribute("departments", list);
				}
				else{
					forward = UPDATE_DEPARTMENT;
					request.setAttribute("result",department.getErrResult());
					department = departmentDAO.getDepartmentByCode(department.getCustCode(),department.getDeptCode());
					request.setAttribute("department", department);
				}
			}
			if(action.equals("backUp")){
				forward = UPDATE_DEPARTMENT;
				department = departmentDAO.getDepartmentByCode(department.getCustCode(),department.getDeptCode());
				request.setAttribute("department", department);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}