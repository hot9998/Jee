package com.examAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdderServlet extends HttpServlet {
	@Override
	//	������ �̿�
	//	�� �޾ƿ��ų� ��� ����� jsp�� ����
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int n1 = Integer.parseInt(req.getParameter("num1"));
		int n2 = Integer.parseInt(req.getParameter("num2"));
		int sum = n1 + n2;
		// System.out.println("��� : " + sum);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>���� ���α׷�</title></head>");
		out.println("<body>");
		out.println("ù��° �� : " + n1 + "<br>");
		out.println("�ι�° �� : " + n2 + "<br>");
		out.println("��� : " + sum + "<br>");
		out.println("</body>");
		out.println("</html>");
//		������ �̿��Ͽ� ���� �޾� �� �� printWriter ��ü�� ����Ͽ� html�±��Է� �� �ٷ� ���

	}

}
