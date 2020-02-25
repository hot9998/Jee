package org.member.action;

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
import org.member.model.MemberDTO;
import org.member.model.SMemberDAOImpl;


/**
 * Servlet implementation class MemberAdminDelete
 */
@WebServlet("/member/memAdminDelete.go")
public class MemberAdminDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberAdminDelete() {
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
		String userid = request.getParameter("userid");
		SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		dao.memDelete(userid);
		ArrayList<MemberDTO> arr = dao.memList();
		JSONArray jarr = new JSONArray();
		for (MemberDTO m : arr) {
			JSONObject obj = new JSONObject();
			String str = m.getAdmin()==1?"관리자":"일반회원";
			obj.put("name", m.getName());
			obj.put("userid", m.getUserid());
			obj.put("phone", m.getPhone());
			obj.put("email", m.getEmail());
			obj.put("admin", str);
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
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
