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

import com.board.model.Pension_Board_PageUtil;
import com.board.model.Pension_BoardDTO;
import com.board.model.Pension_BoardDAO;

import oracle.net.aso.p;

/**
 * Servlet implementation class BoardMainAction
 */
@WebServlet("/boardMain.go")
public class BoardMainAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardMainAction() {
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
		request.setAttribute("btype", request.getParameter("btype"));
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardMain.jsp");
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
		String page = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		int currentPage = Integer.parseInt(page);
		int pageSize = 10;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int btype = Integer.parseInt(request.getParameter("btype"));

		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		int count = dao.boardNum(btype);
		int totpage = (count / pageSize) + (count % pageSize == 0 ? 0 : 1);
		int pageblock = 3;
		int startpage = ((currentPage - 1) / pageblock) * pageblock + 1;
		int endpage = startpage + pageblock - 1;
		if (endpage > totpage)
			endpage = totpage;
		
		Pension_Board_PageUtil pu = new Pension_Board_PageUtil();
		pu.setStartpage(startpage);
		pu.setCurrentPage(currentPage);
		pu.setEndpage(endpage);
		pu.setPageblock(pageblock);
		pu.setTotpage(totpage);
				
		ArrayList<Pension_BoardDTO> arr = dao.boardList(btype, startRow, endRow);		
		
		request.setAttribute("pu", pu);
		request.setAttribute("count", count);
		request.setAttribute("lists", arr);
		request.setAttribute("userid", request.getParameter("userid"));
		request.setAttribute("btype", btype);		
		
		int rowNo = count - ((currentPage-1)*pageSize);
		request.setAttribute("rowNo", rowNo);
				
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardPage.jsp");
		rd.forward(request, response);		
	}

}
