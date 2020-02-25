package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.Pension_BoardDTO;
import com.board.model.Pension_BoardDAO;

/**
 * Servlet implementation class BoardWriteAction
 */
@WebServlet("/boardWrite.go")
public class BoardWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardWriteAction() {
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
		int btype = Integer.parseInt(request.getParameter("btype"));
		request.setAttribute("btype", btype);
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Pension_BoardDTO pb = new Pension_BoardDTO();
		int btype = Integer.parseInt(request.getParameter("btype"));
		pb.setContent(request.getParameter("content"));
		pb.setTitle(request.getParameter("title"));
		pb.setType(btype);
		pb.setUserid(request.getParameter("userid"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		dao.boardWrite(pb);
		response.sendRedirect("/Pension_Project/boardMain.go?btype="+btype);
	}
}
