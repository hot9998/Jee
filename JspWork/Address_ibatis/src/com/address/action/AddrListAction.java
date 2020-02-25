package com.address.action;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AddrListAction
 */
@WebServlet("/address/listAction.ib")
public class AddrListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddrListAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SqlMapClient sqlMap = Config.getMapInstance();		
		try {
			List<AddressVO> arr = sqlMap.queryForList("listData");
			//queryForList : 리턴값이 List(여러개의 값을 리턴)
			request.setAttribute("arrList", arr);
			RequestDispatcher rd = request.getRequestDispatcher("addrList.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
