package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.board.model.Pension_BoardDTO;
import com.board.model.Pension_BoardDAO;

/**
 * Servlet implementation class BoardViewAction
 */
@WebServlet("/boardView.go")
public class BoardViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardViewAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		int btype = Integer.parseInt(request.getParameter("btype"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		dao.boardCount(bnum);
		request.setAttribute("btype", btype);
		request.setAttribute("bnum", bnum);
		request.setAttribute("userid", request.getParameter("userid"));		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		Pension_BoardDTO pb = dao.boardView(bnum);
		JSONObject obj = new JSONObject();
		obj.put("title", pb.getTitle());
		obj.put("content", pb.getContent());
		obj.put("count", pb.getCount());
		obj.put("date", pb.getDate());
		obj.put("num", pb.getNum());
		obj.put("type", pb.getType());
		obj.put("userid", pb.getUserid());
		obj.put("name", pb.getName());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
	}

}
