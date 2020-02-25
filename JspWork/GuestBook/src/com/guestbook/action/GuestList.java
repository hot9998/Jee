package com.guestbook.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.GuestDTO;
import com.guestbook.model.PageUtil;

/**
 * Servlet implementation class GuestList
 */
@WebServlet("/guestbook/list.gb")
public class GuestList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestList() {
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
		String page = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		int currentPage = Integer.parseInt(page);
		String field = request.getParameter("field") == null ? "" : request.getParameter("field");
		String word = request.getParameter("word") == null ? "" : request.getParameter("word");
		int pageSize = 5; // 한 화면에 보여지는 수
		int startRow = (currentPage - 1) * pageSize + 1; // 4페이지라면 16번 (4-1)*5+1
		int endRow = currentPage * pageSize; // 4페이지라면 20번 4*5

		GuestDAO dao = GuestDAO.getInstance();
		ArrayList<GuestDTO> arr = dao.guestList(field, word, startRow, endRow);
		int count = dao.guestCount(field, word); // 게시물 수
		int totpage = (count / pageSize) + (count % pageSize == 0 ? 0 : 1); // 총 페이지 수
		int pageblock = 3; // 페이지 블럭 수
		int startpage = ((currentPage - 1) / pageblock) * pageblock + 1;
		int endpage = startpage + pageblock - 1;
		if (endpage > totpage)
			endpage = totpage;

		PageUtil pu = new PageUtil();
		pu.setStartpage(startpage);
		pu.setCurrentPage(currentPage);
		pu.setEndpage(endpage);
		pu.setPageblock(pageblock);
		pu.setField(field);
		pu.setTotpage(totpage);
		pu.setWord(word);

		request.setAttribute("pu", pu);
		request.setAttribute("count", count);
		request.setAttribute("lists", arr);

		int rowNo = count - ((currentPage - 1) * pageSize);
		request.setAttribute("rowNo", rowNo);

		RequestDispatcher rd = request.getRequestDispatcher("listResult.jsp");
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
