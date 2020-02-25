package com.guestbook.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guestbook.model.GuestDAO;

/**
 * Servlet implementation class GuestLogin
 */
@WebServlet("/guestbook/adminLogin.gb")
public class GuestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		GuestDAO dao = GuestDAO.getInstance();
		boolean flag = dao.loginCheck(userid, pwd);
		String fileName="";
		if(flag) { //회원
			HttpSession session = request.getSession();
			session.setAttribute("login", userid);
			fileName="insert.jsp";
		}else {
			request.setAttribute("errMsg", "아이디/비밀번호 확인");
			fileName="login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(fileName);
		rd.forward(request, response);
	}

}
