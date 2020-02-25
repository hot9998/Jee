package com.guestbook.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.GuestDTO;

/**
 * Servlet implementation class GuestView
 */
@WebServlet("/guestbook/view.gb")
public class GuestView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		GuestDAO dao = GuestDAO.getInstance();
		GuestDTO dto = dao.guestView(num);
		JSONObject obj = new JSONObject();
		obj.put("name", dto.getName());
		obj.put("content", dto.getContent());
		obj.put("created", dto.getCreated());
		obj.put("grade", dto.getGrade());
		obj.put("ipaddr", dto.getIpaddr());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj.toString());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
