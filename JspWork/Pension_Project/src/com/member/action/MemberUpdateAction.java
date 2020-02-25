package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.Pension_MemberDTO;
import com.member.model.Pension_MemberDAO;

/**
 * Servlet implementation class MemberUpdateAction
 */
@WebServlet("/memberUpdate.go")
public class MemberUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		Pension_MemberDAO dao = Pension_MemberDAO.getInstance();
		Pension_MemberDTO pm = dao.memberInfo(userid);
		request.setAttribute("pm", pm);
		RequestDispatcher rd = request.getRequestDispatcher("/member/memberUpdateForm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Pension_MemberDTO pm = new Pension_MemberDTO();
		if(request.getParameter("code").equals("123")) {
			pm.setAdmin(1);
		}else {
			pm.setAdmin(Integer.parseInt(request.getParameter("admin")));
		}		
		pm.setAddr(request.getParameter("addr"));
		pm.setEmail(request.getParameter("email"));
		pm.setName(request.getParameter("name"));
		pm.setPwd(request.getParameter("pwd"));
		pm.setTel(request.getParameter("tel"));
		pm.setUserid(request.getParameter("userid"));
		pm.setZipcode(request.getParameter("zipcode"));	
		Pension_MemberDAO dao = Pension_MemberDAO.getInstance();
		dao.memberUpdate(pm);		
		HttpSession session = request.getSession();
		session.setAttribute("sessPm", pm);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);		
	}

}

