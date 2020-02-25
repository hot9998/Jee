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
 * Servlet implementation class BoardUpdateAction
 */
@WebServlet("/boardUpdate.go")
public class BoardUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		int btype = Integer.parseInt(request.getParameter("btype"));
		request.setAttribute("btype", btype);
		request.setAttribute("bnum", bnum);
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		Pension_BoardDTO pb = dao.boardView(bnum);	
		request.setAttribute("pb", pb);
		RequestDispatcher rd = request.getRequestDispatcher("board/boardUpdate.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");		
		int btype = Integer.parseInt(request.getParameter("btype"));
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		Pension_BoardDTO pb = new Pension_BoardDTO();
		pb.setContent(request.getParameter("content"));
		pb.setTitle(request.getParameter("title"));
		pb.setNum(bnum);
		dao.boardUpdate(pb);
		response.sendRedirect("/Pension_Project/boardMain.go?btype="+btype);		
	}

}
