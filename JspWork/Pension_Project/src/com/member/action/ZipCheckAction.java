package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.member.model.Pension_MemberDAO;
import com.member.model.ZipCodeDTO;

/**
 * Servlet implementation class ZipCheckAction
 */
@WebServlet("/zipCheck.go")
public class ZipCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipCheckAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.sendRedirect("/Pension_Project/member/zipCheck.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String dong = request.getParameter("dong");
		Pension_MemberDAO dao = Pension_MemberDAO.getInstance();
		ArrayList<ZipCodeDTO> arr = dao.zipCheck(dong);
		JSONArray jarr = new JSONArray();
		for(ZipCodeDTO z : arr) {
			JSONObject obj = new JSONObject();	
			obj.put("zipcode", z.getZipcode());
			obj.put("sido", z.getSido());
			obj.put("gugun",z.getGugun());
			obj.put("dong",z.getDong());
			obj.put("bunji",z.getBunji());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

}
