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
	HanyoDAO hanyoDAO;
	Hanyo hanyo = new Hanyo();

	public HanyoController() throws FileNotFoundException, IOException {

		super();
		try {
			hanyoDAO = new HanyoDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			hanyo.setErrResult("接続エラー：ネットワーク不良");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

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
			else if(action.equals("delete")) {
				forward = LIST_HANYO;
				hanyo.setHanyoCode(hanyo_code);
				hanyo.setValueCode(value_code);
				hanyoDAO.deleteHanyo(hanyo);
				List<Hanyo> list = hanyoDAO.getAllHanyos();
				request.setAttribute("hanyos", list);
			}
		}
		if (action.equals("list")||action.equals("firstList")) {
			if(action.equals("firstList")) {
				hanyo.setErrResult("");
			}
			forward = LIST_HANYO;
			if(!hanyo.getErrResult().isEmpty() || hanyo.getErrResult() != null) {
				request.setAttribute("result", hanyo.getErrResult());
			}
				List<Hanyo> list = hanyoDAO.getAllHanyos();
				request.setAttribute("hanyos", list);
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

		hanyo.setHanyoCode(request.getParameter("hanyo_code"));
		hanyo.setValueCode(request.getParameter("value_code"));
		hanyo.setValueName(request.getParameter("value_name"));
		hanyo.setOldHanyoCode(request.getParameter("hanyoCode"));
		hanyo.setOldValueCode(request.getParameter("valueCode"));
		hanyo.setOldValueName(request.getParameter("valueName"));

		//ボタンを押した時の動作
		String action =request.getParameter("action");

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
				hanyoDAO.insertHanyo(hanyo);
				if(hanyo.getErrResult().isEmpty() || hanyo.getErrResult() == null) {
					forward = LIST_HANYO;
					List<Hanyo> list = hanyoDAO.getAllHanyos();
					request.setAttribute("hanyos", list);
				}else{
					forward = INSERT_HANYO;
					request.setAttribute("result",hanyo.getErrResult());
				}
			}
			if(action.equals("backIn")) {
				forward = INSERT_HANYO;
			}
		}
		//更新画面、更新の動作
		if(action.equals("update")||action.equals("backUp")) {
			if(action.equals("update")) {
				hanyoDAO.updateHanyo(hanyo);
				if(hanyo.getErrResult().isEmpty() || hanyo.getErrResult() == null) {
					forward = LIST_HANYO;
					List<Hanyo> list = hanyoDAO.getAllHanyos();
					request.setAttribute("hanyos", list);
				}
				else{
					forward = UPDATE_HANYO;
					request.setAttribute("result",hanyo.getErrResult());
					hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
					request.setAttribute("hanyo", hanyo);
				}
			}
			if(action.equals("backUp")){
				forward = UPDATE_HANYO;
				hanyo = hanyoDAO.getHanyoByCode(hanyo.getHanyoCode(),hanyo.getValueCode());
				request.setAttribute("hanyo", hanyo);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}