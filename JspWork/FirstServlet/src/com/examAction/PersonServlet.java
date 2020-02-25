package com.examAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String[] notice = req.getParameterValues("notice");
		String job = req.getParameter("job");
		Person p = new Person();
		p.setGender(gender);
		p.setId(id);
		p.setJob(job);
		p.setName(name);
		p.setNotice(notice);
		p.setPassword(password);
		req.setAttribute("ps", p);
		
//		req.setAttribute("name", name);
//		req.setAttribute("id", id);
//		req.setAttribute("password", password);
//		req.setAttribute("gender", gender);
//		req.setAttribute("notice", notice);
//		req.setAttribute("job", job);
			
		RequestDispatcher rd = req.getRequestDispatcher("personResult.jsp");
		rd.forward(req, resp);		
	}
	

}
