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

import com.crud.app.dao.CustomerDAO;
import com.crud.app.dao.CustomerDAOImpl;
import com.crud.app.model.Customer;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {

	private static String INSERT_CUSTOMER = "/insertCustomer.jsp";
	private static String LIST_CUSTOMER = "/listCustomer.jsp";
	private static String UPDATE_CUSTOMER = "/updateCustomer.jsp";
	private static String INDEX = "/index.jsp";

	String forward;
	String result;
	CustomerDAO customerDAO;
	Customer customer = new Customer();

	public CustomerController() throws FileNotFoundException, IOException {
		super();

		try {
			customerDAO = new CustomerDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			customer.setErrResult("接続エラー：ネットワーク不良");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_CUSTOMER;
		}
		if (action.equals("updateFace")||action.equals("delete")) {
			String cust_code = request.getParameter("custCode");
			//更新画面の移動
			if(action.equals("updateFace")) {
				forward = UPDATE_CUSTOMER;
				customer = customerDAO.getCustomerByCode(cust_code);
				request.setAttribute("customer", customer);
			}//消去
			else if(action.equals("delete")) {
				forward = LIST_CUSTOMER;
				customer.setCustCode(cust_code);
				customerDAO.deleteCustomer(customer);
				List<Customer> list = customerDAO.getAllCustomers();
				request.setAttribute("customers", list);
			}
		}
		if (action.equals("list")||action.equals("firstList")){
			if(action.equals("firstList")) {
				customer.setErrResult("");
			}
			forward = LIST_CUSTOMER;
			if(!customer.getErrResult().isEmpty() || customer.getErrResult() != null) {
				request.setAttribute("result", customer.getErrResult());
			}
			List<Customer> list = customerDAO.getAllCustomers();
			request.setAttribute("customers", list);
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
		customer.setCustCode(request.getParameter("cust_code"));
		customer.setCustName(request.getParameter("cust_name"));
		customer.setUrl(request.getParameter("url"));
		customer.setPaymentSite(request.getParameter("payment_site"));

		//ボタンを押した時の動作
		String action =request.getParameter("action");
		//取引先全体画面、検索
		if(action.equals("list")||action.equals("search")) {
			forward = LIST_CUSTOMER;
			if(action.equals("search")) {
				List<Customer> list = customerDAO.searchCustomers(customer);
				request.setAttribute("customers", list);
			}
			else{
				List<Customer> list = customerDAO.getAllCustomers();
				request.setAttribute("customers", list);
			}
		}
		//追加画面、追加の動作
		if(action.equals("insert")||action.equals("backIn")) {
			if(action.equals("insert")) {
				customerDAO.insertCustomer(customer);
				if(customer.getErrResult().isEmpty() || customer.getErrResult() == null) {
					forward = LIST_CUSTOMER;
					List<Customer> list = customerDAO.getAllCustomers();
					request.setAttribute("customers", list);
				}else{
					forward = INSERT_CUSTOMER;
					request.setAttribute("result",customer.getErrResult());
				}
			}
			if(action.equals("backIn")) {
				forward = INSERT_CUSTOMER;
			}
		}
		//更新画面、更新の動作
		if(action.equals("update")||action.equals("backUp")) {
			if(action.equals("update")) {
				customerDAO.updateCustomer(customer);
				if(customer.getErrResult().isEmpty() || customer.getErrResult() == null) {
					forward = LIST_CUSTOMER;
					List<Customer> list = customerDAO.getAllCustomers();
					request.setAttribute("customers", list);
				}
				else{
					forward = UPDATE_CUSTOMER;
					request.setAttribute("result",customer.getErrResult());
					customer = customerDAO.getCustomerByCode(customer.getCustCode());
					request.setAttribute("customer", customer);
				}
			}
			if(action.equals("backUp")){
				forward = UPDATE_CUSTOMER;
				customer = customerDAO.getCustomerByCode(customer.getCustCode());
				request.setAttribute("customer", customer);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

}