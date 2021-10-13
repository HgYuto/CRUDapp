package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.app.dao.CustomerDAO;
import com.crud.app.dao.CustomerDAOImpl;
import com.crud.app.model.Customer;
import com.crud.app.util.DButil;
import com.crud.app.util.LogUtil;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {

	private static String INSERT_CUSTOMER = "/insertCustomer.jsp";
	private static String LIST_CUSTOMER = "/listCustomer.jsp";
	private static String UPDATE_CUSTOMER = "/updateCustomer.jsp";
	private static String INDEX = "/index.jsp";

	String forward;
	String result;
	String errM = "";
	CustomerDAO customerDAO;
	Customer customer = new Customer();

	public CustomerController() throws FileNotFoundException, IOException {
		super();

		try {
			customerDAO = new CustomerDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			errM = "接続エラー：ネットワーク不良";
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			// アプリケーションスコープに保存された訪問回数を更新
		    ServletContext application = this.getServletContext();
		    Integer count = (Integer)application.getAttribute("count");
		    count++;
		    application.setAttribute("count", count);
		    LogUtil.initialized();

			request.setCharacterEncoding("UTF-8");
			//表示画面からのパラメーターを取得
			String action = request.getParameter("action");

			//エラー文リセット
			errM = "";
			customerDAO = new CustomerDAOImpl();

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
				if(action.equals("delete")) {
					forward = LIST_CUSTOMER;
					customer.setCustCode(cust_code);
					customerDAO.deleteCustomer(customer);
					List<Customer> list = customerDAO.getAllCustomers();
					request.setAttribute("customers", list);
				}
			}
			if (action.equals("list")){
				forward = LIST_CUSTOMER;
				List<Customer> list = customerDAO.getAllCustomers();
				request.setAttribute("customers", list);
			}
			if(action.equals("face")) {
				forward = INDEX;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
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
			DButil.closeConnection();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
			customer.setCustCode(request.getParameter("cust_code"));
			customer.setCustName(request.getParameter("cust_name"));
			customer.setUrl(request.getParameter("url"));
			customer.setPaymentSite(request.getParameter("payment_site"));

			//ボタンを押した時の動作
			String action =request.getParameter("action");

			//エラー文リセット
			errM = "";
			customerDAO = new CustomerDAOImpl();

			//取引先全体画面に戻る、検索
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
			if(action.equals("insert")) {
				int count = customerDAO.findCount(customer);
				if(count > 0) {
					forward = INSERT_CUSTOMER;
					errM = "取引先コードが重複しています。再度見直してください。";
				}
				else if(count < 0) {
					forward = INSERT_CUSTOMER;
					errM = "接続エラー：ネットワーク不良";
				}
				else {
					customerDAO.insertCustomer(customer);
					forward = LIST_CUSTOMER;
					List<Customer> list = customerDAO.getAllCustomers();
					request.setAttribute("customers", list);
				}
			}
			//更新画面、更新の動作
			if(action.equals("update")) {
				customerDAO.updateCustomer(customer);
				forward = LIST_CUSTOMER;
				List<Customer> list = customerDAO.getAllCustomers();
				request.setAttribute("customers", list);
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
			DButil.closeConnection();
		}
	}
}