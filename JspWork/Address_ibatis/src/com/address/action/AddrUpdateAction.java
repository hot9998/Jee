package com.address.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.address.config.Config;
import com.address.model.AddressVO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Servlet implementation class AddrUpdateAction
 */
@WebServlet("/address/updateAction.ib")
public class AddrUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddrUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		AddressVO av = new AddressVO();
		av.setAddr(request.getParameter("addr"));
		av.setName(request.getParameter("name"));
		av.setNum(Integer.parseInt(request.getParameter("num")));
		av.setTel(request.getParameter("tel"));
		av.setZipcode(request.getParameter("zip"));		
		SqlMapClient sqlMap = Config.getMapInstance();
		try {
			sqlMap.update("updateData",av);
			response.sendRedirect("listAction.ib");
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
