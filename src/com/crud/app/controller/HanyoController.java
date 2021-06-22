package com.crud.app.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.app.dao.HanyoDAO;
import com.crud.app.dao.HanyoDAOImpl;
import com.crud.app.model.Hanyo;

@WebServlet("/HanyoController")
public class HanyoController extends HttpServlet {

	private static String INSERT_HANYO = "/insertHanyo.jsp";
	private static String LIST_HANYO = "/listHanyo.jsp";
	private static String UPDATE_HANYO ="/updateHanyo.jsp";
	private static String INDEX ="/index.jsp";

	String forward;
	String result;
	String errM = "";
	HanyoDAO hanyoDAO;
	Hanyo hanyo = new Hanyo();

	public HanyoController() throws FileNotFoundException, IOException {

		super();
		try {
			hanyoDAO = new HanyoDAOImpl();
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
				forward = INSERT_HANYO;
			}
			if (action.equals("updateFace")||action.equals("delete")) {
				String hanyo_code = request.getParameter("hanyoCode");
				String value_code = request.getParameter("valueCode");
				//更新画面の移動
				if(action.equals("updateFace")) {
					forward = UPDATE_HANYO;
					Hanyo hanyo  = hanyoDAO.getHanyoByCode(hanyo_code,value_code);
					request.setAttribute("hanyo", hanyo);
				}//消去
				if(action.equals("delete")) {
					forward = LIST_HANYO;
					hanyo.setHanyoCode(hanyo_code);
					hanyo.setValueCode(value_code);
					hanyoDAO.deleteHanyo(hanyo);
					List<Hanyo> list = hanyoDAO.getAllHanyos();
					request.setAttribute("hanyos", list);
				}
			}
			if (action.equals("list")) {
				forward = LIST_HANYO;
				List<Hanyo> list = hanyoDAO.getAllHanyos();
				request.setAttribute("hanyos", list);
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			request.setCharacterEncoding("UTF-8");

			hanyo.setHanyoCode(request.getParameter("hanyo_code"));
			hanyo.setValueCode(request.getParameter("value_code"));
			hanyo.setValueName(request.getParameter("value_name"));
			hanyo.setOldHanyoCode(request.getParameter("hanyoCode"));
			hanyo.setOldValueCode(request.getParameter("valueCode"));
			hanyo.setOldValueName(request.getParameter("valueName"));

			//ボタンを押した時の動作
			String action =request.getParameter("action");
			Calendar cl = Calendar.getInstance();
			System.out.println(cl.getTime());

			//エラー文リセット
			errM = "";

			//汎用全体画面、検索の動作
			if(action.equals("list")||action.equals("search")) {
				forward = LIST_HANYO;
				if(action.equals("search")) {
					List<Hanyo> list = hanyoDAO.searchHanyos(hanyo);
					request.setAttribute("hanyos", list);
				}
				else{
					List<Hanyo> list = hanyoDAO.getAllHanyos();
					request.setAttribute("hanyos", list);
				}
			}
			//追加画面、追加の動作
			if(action.equals("insert")||action.equals("backIn")) {
				if(action.equals("insert")) {
					int count = hanyoDAO.findCount(hanyo);

					if(count > 0) {
						forward = INSERT_HANYO;
						errM = "汎用コードと値コードが重複しています。再度見直してください。";
					}
					else if(count < 0) {
						forward = INSERT_HANYO;
						errM = "接続エラー：ネットワーク不良";
					}
					else {
						hanyoDAO.insertHanyo(hanyo);
						forward = LIST_HANYO;
						List<Hanyo> list = hanyoDAO.getAllHanyos();
						request.setAttribute("hanyos", list);
					}
				}
				//jspでエラーの場合の画面移動
				if(action.equals("backIn")) {
					forward = INSERT_HANYO;
				}
			}
			//更新画面、更新の動作
			if(action.equals("update")||action.equals("backUp")) {
				if(action.equals("update")) {
					int count = hanyoDAO.findCount(hanyo);
					if(count > 1) {
						forward = UPDATE_HANYO;
						errM = "汎用コードと値コードが重複しています。再度見直してください。";
						hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
						request.setAttribute("hanyo", hanyo);
					}
					else if(count <= 0) {
						forward = UPDATE_HANYO;
						errM = "接続エラー：ネットワーク不良";
						hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
						request.setAttribute("hanyo", hanyo);
					}
					hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
					request.setAttribute("hanyo", hanyo);
					}
					else {
						hanyoDAO.updateHanyo(hanyo);
						forward = LIST_HANYO;
						List<Hanyo> list = hanyoDAO.getAllHanyos();
						request.setAttribute("hanyos", list);
					}
				}
				//jspでエラーの場合の画面移動
				if(action.equals("backUp")){
					forward = UPDATE_HANYO;
					hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
					request.setAttribute("hanyo", hanyo);
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
		}
	}
}