package com.examAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdderServlet extends HttpServlet {
	@Override
	//	서블릿을 이용
	//	값 받아오거나 사용 방식은 jsp와 동일
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int n1 = Integer.parseInt(req.getParameter("num1"));
		int n2 = Integer.parseInt(req.getParameter("num2"));
		int sum = n1 + n2;
		// System.out.println("결과 : " + sum);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>덧셈 프로그램</title></head>");
		out.println("<body>");
		out.println("첫번째 수 : " + n1 + "<br>");
		out.println("두번째 수 : " + n2 + "<br>");
		out.println("결과 : " + sum + "<br>");
		out.println("</body>");
		out.println("</html>");
//		서블릿을 이용하여 값을 받아 온 뒤 printWriter 객체를 사용하여 html태그입력 후 바로 출력

	}

}
