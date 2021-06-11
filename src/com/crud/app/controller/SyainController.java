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

import com.crud.app.dao.SyainDAO;
import com.crud.app.dao.SyainDAOImpl;
import com.crud.app.model.Syain;

@WebServlet("/SyainController")
public class SyainController extends HttpServlet {

	private static String INSERT_SYAIN = "/insertSyain.jsp";
	private static String LIST_SYAIN = "/listSyain.jsp";
	private static String UPDATE_SYAIN ="/updateSyain.jsp";
	private static String INDEX ="/index.jsp";

	String forward;
	String result;
	SyainDAO syainDAO;
	Syain syain = new Syain();

	public SyainController() throws FileNotFoundException, IOException{

		super();
		try {
			syainDAO = new SyainDAOImpl();
		}
		catch(SQLException e) {
			e.printStackTrace();
			syain.setErrResult("接続エラー：ネットワーク不良");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		//表示画面からのパラメーターを取得
		String action = request.getParameter("action");

		if (action.equals("insertFace")) {
			forward = INSERT_SYAIN;
		}
		if (action.equals("updateFace")||action.equals("delete")) {
			String syain_code = request.getParameter("syainCode");
			//更新画面の移動
			if(action.equals("updateFace")) {
				forward = UPDATE_SYAIN;
				Syain syain  = syainDAO.getSyainByCode(syain_code);
				request.setAttribute("syain", syain);
			}//消去
			else if(action.equals("delete")) {
				forward = LIST_SYAIN;
				syain.setSyainCode(syain_code);
				syainDAO.deleteSyain(syain);
				List<Syain> list = syainDAO.getAllSyains();
				request.setAttribute("syains", list);
			}
		}
		if (action.equals("list")||action.equals("firstList")){
			if(action.equals("firstList")) {
				syain.setErrResult("");
			}
			forward = LIST_SYAIN;
			if(!syain.getErrResult().isEmpty() || syain.getErrResult() != null) {
				request.setAttribute("result", syain.getErrResult());
			}
			List<Syain> list = syainDAO.getAllSyains();
			request.setAttribute("syains", list);
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
		syain.setSyainCode(request.getParameter("syain_code"));
		if(request.getParameter("position").isEmpty()) {
			short position = -1;
			syain.setPosition(position);
		}else {
			syain.setPosition(Short.valueOf(request.getParameter("position")));
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
		if(action.equals("insert")||action.equals("backIn")) {
			if(action.equals("insert")) {
				syainDAO.insertSyain(syain);
				if(syain.getErrResult().isEmpty() || syain.getErrResult() == null) {
					forward = LIST_SYAIN;
					List<Syain> list = syainDAO.getAllSyains();
					request.setAttribute("syains", list);
				}else{
					forward = INSERT_SYAIN;
					request.setAttribute("result",syain.getErrResult());
				}
			}
			if(action.equals("backIn")) {
				forward = INSERT_SYAIN;
			}
		}
		//更新画面、更新の動作
		if(action.equals("update")||action.equals("backUp")) {
			if(action.equals("update")) {
				syainDAO.updateSyain(syain);
				if(syain.getErrResult().isEmpty() || syain.getErrResult() == null) {
					forward = LIST_SYAIN;
					List<Syain> list = syainDAO.getAllSyains();
					request.setAttribute("syains", list);
				}
				else{
					forward = UPDATE_SYAIN;
					request.setAttribute("result",syain.getErrResult());
					syain = syainDAO.getSyainByCode(syain.getSyainCode());
					request.setAttribute("syain", syain);
				}
			}
			if(action.equals("backUp")){
				forward = UPDATE_SYAIN;
				syain = syainDAO.getSyainByCode(syain.getSyainCode());
				request.setAttribute("syain", syain);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

}