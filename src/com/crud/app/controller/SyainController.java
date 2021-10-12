package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.app.dao.SyainDAO;
import com.crud.app.dao.SyainDAOImpl;
import com.crud.app.model.Syain;
import com.crud.app.util.DButil;

@WebServlet("/SyainController")
public class SyainController extends HttpServlet {

	private static String INSERT_SYAIN = "/insertSyain.jsp";
	private static String LIST_SYAIN = "/listSyain.jsp";
	private static String UPDATE_SYAIN ="/updateSyain.jsp";
	private static String INDEX ="/index.jsp";

	String forward;
	String result;
	String errM = "";
	String pattern = "";
	SyainDAO syainDAO;
	Syain syain = new Syain();

	public SyainController() throws FileNotFoundException, IOException{

		super();
		try {
			syainDAO = new SyainDAOImpl();
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
			syainDAO = new SyainDAOImpl();

			if (action.equals("insertFace")) {
				forward = INSERT_SYAIN;
			}
			if (action.equals("updateFace")||action.equals("delete")) {
				String syain_code = request.getParameter("syainCode");
				//更新画面の移動
				if(action.equals("updateFace")) {
					forward = UPDATE_SYAIN;
					syain  = syainDAO.getSyainByCode(syain_code);
					request.setAttribute("syain", syain);
				}//消去
				if(action.equals("delete")) {
					forward = LIST_SYAIN;
					syain.setSyainCode(syain_code);
					syainDAO.deleteSyain(syain);
					List<Syain> list = syainDAO.getAllSyains();
					request.setAttribute("syains", list);
				}
			}
			if (action.equals("list")){
				forward = LIST_SYAIN;
				List<Syain> list = syainDAO.getAllSyains();
				request.setAttribute("syains", list);
			}
			if(action.equals("face")) {
				forward = INDEX;
			}
		}
		catch (SQLSyntaxErrorException e) {
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

			//エラー文リセット
			errM = "";
			syainDAO = new SyainDAOImpl();

			//正規表現
			pattern = "[0-9]+";
			Pattern p = Pattern.compile(pattern);

			syain.setSyainCode(request.getParameter("syain_code"));
			if(request.getParameter("position").isEmpty()) {
				short position = -1;
				syain.setPosition(position);
			}
			else if(!request.getParameter("position").isEmpty()) {
				if(p.matcher(request.getParameter("position")).find()) {
					syain.setPosition(Short.valueOf(request.getParameter("position")));
				}
				else {
					short position = -1;
					syain.setPosition(position);
				}
			}
			syain.setSyainName(request.getParameter("syain_name"));
			syain.setMailAddress(request.getParameter("mail_address"));
			syain.setTel(request.getParameter("tel"));

			//ボタンを押した時の動作
			String action =request.getParameter("action");

			//社員全体画面、検索
			if(action.equals("list")||action.equals("search")) {
				forward = LIST_SYAIN;
				if(action.equals("search")) {
					List<Syain> list = syainDAO.searchSyains(syain);
					request.setAttribute("syains", list);
				}
				else{
					List<Syain> list = syainDAO.getAllSyains();
					request.setAttribute("syains", list);
				}
			}
			//追加画面、追加の動作
			if(action.equals("insert")) {
				int count = syainDAO.findCount(syain);
				if(count > 0) {
					forward = INSERT_SYAIN;
					errM = "社員コードが重複しています。再度見直してください。";
				}
				else if(count < 0) {
					forward = INSERT_SYAIN;
					errM = "接続エラー：ネットワーク不良";
				}
				else {
					syainDAO.insertSyain(syain);
					forward = LIST_SYAIN;
					List<Syain> list = syainDAO.getAllSyains();
					request.setAttribute("syains", list);
				}
			}
			//更新画面、更新の動作
			if(action.equals("update")) {
				syainDAO.updateSyain(syain);
				forward = LIST_SYAIN;
				List<Syain> list = syainDAO.getAllSyains();
				request.setAttribute("syains", list);
			}
		}
		catch (SQLSyntaxErrorException e) {
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