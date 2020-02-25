package com.address.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.address.config.Config;
import com.address.model.ZipcodeVO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Servlet implementation class ZipAction
 */
@WebServlet("/address/zipAction.ib")
public class ZipAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ZipAction() {
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
		RequestDispatcher rd = request.getRequestDispatcher("zipCheck.jsp");
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
		String dong = request.getParameter("dong");
		SqlMapClient sqlMap = Config.getMapInstance();
		try {
			List<ZipcodeVO> zarr = sqlMap.queryForList("zipData", dong);
			JSONArray jarr = new JSONArray();
			for(ZipcodeVO zvo : zarr) {
				JSONObject obj = new JSONObject();
				obj.put("zipcode", zvo.getZipcode());
				obj.put("sido", zvo.getSido());
				obj.put("gugun", zvo.getGugun());
				obj.put("dong",zvo.getDong());
				obj.put("bunji",zvo.getBunji());
				jarr.add(obj);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jarr.toString());					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
