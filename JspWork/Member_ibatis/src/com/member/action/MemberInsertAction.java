package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.member.config.Config;
import com.member.model.MemberVO;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/memberInsertAction.ib")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		MemberVO mv = new MemberVO();
		mv.setAdmin(Integer.parseInt(request.getParameter("admin")));
		mv.setEmail(request.getParameter("email"));
		mv.setName(request.getParameter("name"));
		mv.setPhone(request.getParameter("phone"));
		mv.setPwd(request.getParameter("pwd"));
		mv.setUserid(request.getParameter("userid"));
		SqlMapClient sqlMap = Config.getMapInstance();
		try {
			sqlMap.insert("memberInsert",mv);			
		}catch (Exception e) {
			e.printStackTrace();			
		}
		
	}

}
