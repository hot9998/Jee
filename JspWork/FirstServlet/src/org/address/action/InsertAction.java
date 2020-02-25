package org.address.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.AddressDTO;
import org.address.SAddressDAO;

/**
 * Servlet implementation class InsertAction
 */
@WebServlet("/address/insert.do")
public class InsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		AddressDTO ad = new AddressDTO();
		ad.setAddr(request.getParameter("addr"));
		ad.setName(request.getParameter("name"));
		ad.setTel(request.getParameter("tel"));
		ad.setZipcode(request.getParameter("zipcode"));
		SAddressDAO dao = SAddressDAO.getInstance();
		dao.addrInsert(ad);
		RequestDispatcher rd = request.getRequestDispatcher("list.do");
		response.sendRedirect("list.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
