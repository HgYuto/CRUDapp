package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.app.dao.EmployeeDAO;
import com.crud.app.dao.EmployeeDAOImpl;
import com.crud.app.model.Employee;

public class EmployeeController extends HttpServlet {

	private static String INSERT_OR_EDIT = "/employee.jsp";
	private static String LIST_EMPLOYEE = "/listEmployee.jsp";

	String forward;

	EmployeeDAO employeeDAO;

	public EmployeeController() throws FileNotFoundException, IOException {

		super();

		employeeDAO = new EmployeeDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		//取得値が"list"か"delete"の場合
		if (action.equals("list") || action.equals("delete")) {

			forward = LIST_EMPLOYEE;

			if (action.equals("delete")) {
				String id = request.getParameter("empId");

				Employee employee = new Employee();
				employee.setEmployeeId(Long.parseLong(id));

				employeeDAO.deleteEmployee(employee);
			}
			List<Employee> list = employeeDAO.getAllEmployees();
			request.setAttribute("employees", list);

			//取得値が"edit"の場合
		} else if (action.equals("edit")) {

			forward = INSERT_OR_EDIT;

			String id = request.getParameter("empId");

			Employee employee = employeeDAO.getEmployeeById(Long.parseLong(id));

			request.setAttribute("employee", employee);

		} else {
			forward = INSERT_OR_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Employee employee = new Employee();

		String id = request.getParameter("id");

		employee.setEmployeeName(request.getParameter("name"));

		if (id == null || id.isEmpty()) {

			employeeDAO.addEmployee(employee);

		} else {

			employee.setEmployeeId(Long.parseLong(id));
			employeeDAO.updateEmployee(employee);

		}

		RequestDispatcher view = request.getRequestDispatcher("listEmployee.jsp");
		List<Employee> list = employeeDAO.getAllEmployees();
		request.setAttribute("employees", list);
		view.forward(request, response);

	}

}