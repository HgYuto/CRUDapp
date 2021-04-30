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

	private static String INSERT_OR_EDIT = "/insertHanyo.jsp";
	private static String LIST_HANYO = "/listHanyo.jsp";
	private static String UPDATE_HANYO ="/updateHanyo.jsp";

	String forward;

	HanyoDAO hanyoDAO;

	public HanyoController() throws FileNotFoundException, IOException {

		super();

		hanyoDAO = new HanyoDAOImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_OR_EDIT;
		}
		if (action.equals("updateFace")) {
			forward = UPDATE_HANYO;
			String hanyo_code = request.getParameter("hanyoCode");
			Hanyo hanyo  = hanyoDAO.getHanyoByCode(hanyo_code);
			request.setAttribute("hanyo", hanyo);
		}
		if (action.equals("list")|| action.equals("delete")) {
			forward = LIST_HANYO;

			if(action.equals("delete")) {
				String hanyo_code = request.getParameter("hanyoCode");
				Hanyo hanyo = new Hanyo();
				hanyo.setHanyoCode(hanyo_code);
				hanyoDAO.deleteHanyo(hanyo);
			}

			List<Hanyo> list = hanyoDAO.getAllHanyos();
			request.setAttribute("hanyos", list);
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//ボタンを押した時の動作
		Hanyo hanyo = new Hanyo();
		hanyo.setHanyoCode(request.getParameter("hanyo_code"));
		hanyo.setValueCode(request.getParameter("value_code"));
		hanyo.setValueName(request.getParameter("value_name"));

		String test =request.getParameter("action");
		if(test.equals("insert")||test.equals("update")) {
			if(test.equals("insert")) {
				hanyoDAO.insertHanyo(hanyo);
			}
			if(test.equals("update")){
				hanyoDAO.updateHanyo(hanyo);
			}
		RequestDispatcher view = request.getRequestDispatcher("listHanyo.jsp");
		List<Hanyo> list = hanyoDAO.getAllHanyos();
		request.setAttribute("hanyos", list);
		view.forward(request, response);
		}
/*		if(test.equals("search")) {
			hanyoDAO.searchHanyo(hanyo);
		}
*/
	}

}