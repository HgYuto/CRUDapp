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

import com.crud.app.dao.UserDAO;
import com.crud.app.dao.UserDAOImpl;
import com.crud.app.model.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static String INSERT_USER = "/insertUser.jsp";
	private static String LIST_USER = "/listUser.jsp";
	private static String UPDATE_USER ="/updateUser.jsp";
	private static String INDEX ="/index.jsp";

	String forward;
	String result;
	UserDAO userDAO;
	User user = new User();

	public UserController() throws FileNotFoundException, IOException {

		super();

		try {
			userDAO = new UserDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			user.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_USER;
		}
		if (action.equals("updateFace")||action.equals("delete")) {
			String syain_code = request.getParameter("syainCode");
			String user_id = request.getParameter("userId");
			//更新画面の移動
			if(action.equals("updateFace")) {
				forward = UPDATE_USER;
				User user  = userDAO.getUserByCode(syain_code,user_id);
				request.setAttribute("user", user);
			}//消去
			else if(action.equals("delete")) {
				forward = LIST_USER;
				user.setSyainCode(syain_code);
				user.setUserId(user_id);
				userDAO.deleteUser(user);
				List<User> list = userDAO.getAllUsers();
				request.setAttribute("users", list);
			}
		}
		if (action.equals("list")||action.equals("firstList")) {
			if(action.equals("firstList")) {
				user.setErrResult("");
			}
			forward = LIST_USER;
			if(!user.getErrResult().isEmpty() || user.getErrResult() != null) {
				request.setAttribute("result", user.getErrResult());
			}
			List<User> list = userDAO.getAllUsers();
			request.setAttribute("users", list);
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

		user.setSyainCode(request.getParameter("syain_code"));
		user.setUserId(request.getParameter("user_id"));
		user.setPassword(request.getParameter("password"));
		user.setPreUserId(request.getParameter("preUserId"));

		if(request.getParameter("authority").isEmpty()) {
			short authority = -1;
			user.setAuthority(authority);
		}else {
			user.setAuthority(Short.valueOf(request.getParameter("authority")));
		}

		//ボタンを押した時の動作
		String action =request.getParameter("action");
		//ユーザ全体画面、検索の動作
		if(action.equals("list")||action.equals("search")) {
			forward = LIST_USER;
			if(action.equals("search")) {
				List<User> list = userDAO.searchUsers(user);
				request.setAttribute("users", list);
			}
			else{
				List<User> list = userDAO.getAllUsers();
				request.setAttribute("users", list);
			}
		}
		//追加画面、追加の動作
		if(action.equals("insert")||action.equals("backIn")) {
			if(action.equals("insert")) {
				userDAO.insertUser(user);
				if(user.getErrResult().isEmpty() || user.getErrResult() == null) {
					forward = LIST_USER;
					List<User> list = userDAO.getAllUsers();
					request.setAttribute("users", list);
				}else{
					forward = INSERT_USER;
					request.setAttribute("result",user.getErrResult());
				}
			}
			if(action.equals("backIn")) {
				forward = INSERT_USER;
			}
		}
		//更新画面、更新の動作
		if(action.equals("update")||action.equals("backUp")) {
			if(action.equals("update")) {
				userDAO.updateUser(user);
				if(user.getErrResult().isEmpty() || user.getErrResult() == null) {
					forward = LIST_USER;
					List<User> list = userDAO.getAllUsers();
					request.setAttribute("users", list);
				}
				else{
					forward = UPDATE_USER;
					request.setAttribute("result",user.getErrResult());
					user = userDAO.getUserByCode(user.getSyainCode(),user.getPreUserId());
					request.setAttribute("user", user);
				}
			}
			if(action.equals("backUp")){
				forward = UPDATE_USER;
				user = userDAO.getUserByCode(user.getSyainCode(),user.getPreUserId());
				request.setAttribute("user", user);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

}