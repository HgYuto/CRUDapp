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

import com.crud.app.dao.CustomerDAO;
import com.crud.app.dao.CustomerDAOImpl;
import com.crud.app.model.Customer;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {

	private static String INSERT_OR_EDIT = "/insertCustomer.jsp";
	private static String LIST_CUSTOMER = "/listCustomer.jsp";
	private static String UPDATE_CUSTOMER ="/updateCustomer.jsp";

	String forward;

	CustomerDAO customerDAO;

	public CustomerController() throws FileNotFoundException, IOException {

		super();

		customerDAO = new CustomerDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_OR_EDIT;
		}
		if (action.equals("updateFace")) {
			forward = UPDATE_CUSTOMER;
			String cust_code = request.getParameter("empcode");
			Customer customer = customerDAO.getCustomerByCode(cust_code);
			request.setAttribute("customer", customer);
		}
		if (action.equals("list")|| action.equals("delete")) {
			forward = LIST_CUSTOMER;

			if(action.equals("delete")) {
				String cust_code = request.getParameter("empcode");
				Customer customer = new Customer();
				customer.setCustCode(cust_code);
				customerDAO.deleteCustomer(customer);
			}

			List<Customer> list = customerDAO.getAllCustomers();
			request.setAttribute("customers", list);
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//ボタンを押した時の動作
		Customer customer = new Customer();
		customer.setCustCode(request.getParameter("cust_code"));
		customer.setCustName(request.getParameter("cust_name"));
		customer.setUrl(request.getParameter("url"));
		customer.setPaymentSite(request.getParameter("payment_site"));

		String test =request.getParameter("action");

		if(test.equals("insert")) {
			customerDAO.insertCustomer(customer);
		}
		if(test.equals("update")){
			customerDAO.updateCustomer(customer);
		}

		RequestDispatcher view = request.getRequestDispatcher("listCustomer.jsp");
		List<Customer> list = customerDAO.getAllCustomers();
		request.setAttribute("customers", list);
		view.forward(request, response);

	}

}