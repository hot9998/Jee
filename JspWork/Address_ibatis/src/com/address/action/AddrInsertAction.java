package com.address.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.address.config.Config;
import com.address.model.AddressVO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Servlet implementation class AddrInsertAction
 */
@WebServlet("/address/insertAction.ib")
public class AddrInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddrInsertAction() {
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
		RequestDispatcher rd = request.getRequestDispatcher("addrInsert.jsp");
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
		AddressVO av = new AddressVO();
		av.setAddr(request.getParameter("addr"));
		av.setName(request.getParameter("name"));
		av.setTel(request.getParameter("tel"));
		av.setZipcode(request.getParameter("zipcode"));

		SqlMapClient sqlMap = Config.getMapInstance();
		try {
			sqlMap.insert("insertData", av);
			response.sendRedirect("listAction.ib");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
