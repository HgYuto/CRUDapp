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

	HanyoDAO hanyoDAO;

	public HanyoController() throws FileNotFoundException, IOException {

		super();

		hanyoDAO = new HanyoDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_HANYO;
		}
		if (action.equals("updateFace")) {
			forward = UPDATE_HANYO;
			String hanyo_code = request.getParameter("hanyoCode");
			String value_code = request.getParameter("valueCode");
			Hanyo hanyo  = hanyoDAO.getHanyoByCode(hanyo_code,value_code);
			request.setAttribute("hanyo", hanyo);

		}
		if (action.equals("list")|| action.equals("delete")) {
			forward = LIST_HANYO;

			if(action.equals("delete")) {
				String hanyo_code = request.getParameter("hanyoCode");
				String value_code = request.getParameter("valueCode");
				Hanyo hanyo = new Hanyo();
				hanyo.setHanyoCode(hanyo_code);
				hanyo.setValueCode(value_code);
				hanyoDAO.deleteHanyo(hanyo);
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
		//ボタンを押した時の動作
		Hanyo hanyo = new Hanyo();
		hanyo.setHanyoCode(request.getParameter("hanyo_code"));
		hanyo.setValueCode(request.getParameter("value_code"));
		hanyo.setValueName(request.getParameter("value_name"));
		hanyo.setOldHanyoCode(request.getParameter("hanyoCode"));
		hanyo.setOldValueCode(request.getParameter("valueCode"));
		hanyo.setOldValueName(request.getParameter("valueName"));

		String action =request.getParameter("action");
		if(action.equals("insert")||action.equals("update")||action.equals("list")) {
			if(action.equals("insert")) {
				hanyoDAO.insertHanyo(hanyo);
			}
			if(action.equals("update")){
				hanyoDAO.updateHanyo(hanyo);
			}
		RequestDispatcher view = request.getRequestDispatcher(LIST_HANYO);
		List<Hanyo> list = hanyoDAO.getAllHanyos();
		request.setAttribute("hanyos", list);
		view.forward(request, response);
		}
		if(action.equals("search")) {
			RequestDispatcher view = request.getRequestDispatcher(LIST_HANYO);
			List<Hanyo> list = hanyoDAO.searchHanyos(hanyo);
			request.setAttribute("hanyos", list);
			view.forward(request, response);
		}
		else{
			System.out.println("p通っています。");
		}

	}

}