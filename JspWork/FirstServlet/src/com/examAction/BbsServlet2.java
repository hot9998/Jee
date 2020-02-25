package com.examAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BbsServlet2 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		req.setAttribute("name", name);
		req.setAttribute("title", title);
		req.setAttribute("content", content);

		RequestDispatcher rd = req.getRequestDispatcher("bbsResult.jsp");
		rd.forward(req, resp);

	}

}