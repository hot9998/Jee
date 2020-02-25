package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.model.Pension_BoardDAO;
import com.board.model.Pension_commentDTO;

/**
 * Servlet implementation class CommentWriteAction
 */
@WebServlet("/commentWrite.go")
public class CommentWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentWriteAction() {
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
		Pension_commentDTO pc = new Pension_commentDTO();
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		pc.setBnum(bnum);
		pc.setContent(request.getParameter("content"));
		pc.setUserid(request.getParameter("muserid"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		dao.commentWrite(pc);
		request.setAttribute("bnum", bnum);
		RequestDispatcher rd = request.getRequestDispatcher("commentView.go");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
