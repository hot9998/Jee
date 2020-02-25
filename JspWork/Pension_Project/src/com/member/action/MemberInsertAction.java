package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.Pension_MemberDTO;
import com.member.model.Pension_MemberDAO;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/memberInsert.go")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.sendRedirect("/Pension_Project/member/memberForm.jsp");
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
			pm.setAdmin(0);
		}
		pm.setAddr(request.getParameter("addr"));
		pm.setEmail(request.getParameter("email"));
		pm.setName(request.getParameter("name"));
		pm.setPwd(request.getParameter("pwd"));
		pm.setTel(request.getParameter("tel"));
		pm.setUserid(request.getParameter("uid"));
		pm.setZipcode(request.getParameter("zipcode"));
		Pension_MemberDAO dao = Pension_MemberDAO.getInstance();
		dao.memberInsert(pm);
		response.sendRedirect("/Pension_Project/index.jsp");					
	}

}
