package com.board.action;

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

import com.board.model.Pension_BoardDAO;
import com.board.model.Pension_commentDTO;

/**
 * Servlet implementation class CommentViewAction
 */
@WebServlet("/commentView.go")
public class CommentViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		Pension_BoardDAO dao = Pension_BoardDAO.getInstance();
		ArrayList<Pension_commentDTO> arr = dao.commentView(bnum);
		JSONArray jarr = new JSONArray();
		for (Pension_commentDTO pc : arr) {
			JSONObject obj = new JSONObject();
			obj.put("userid", pc.getUserid());
			obj.put("content", pc.getContent());
			obj.put("date", pc.getDate());
			obj.put("num", pc.getNum());
			obj.put("name",pc.getName());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

