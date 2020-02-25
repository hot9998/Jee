package com.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadFileServelt
 */
@WebServlet("/file/upload.do")
public class UploadFileServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServelt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println("path:"+request.getContextPath());
		request.setCharacterEncoding("utf-8");

		String savePath = "upload";
		int uploadFileSize = 5 * 1024 * 1024; // 5MB
		String encType = "UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath); // �������� ���� ���丮
		//System.out.println("uploadFilePath:" + uploadFilePath);

		MultipartRequest multi = new MultipartRequest(request, //request ��ü
				uploadFilePath, // �������� ���� ���丮
				uploadFileSize, // �ִ� ���ε� ������
				encType, // ���ڵ����				 
				new DefaultFileRenamePolicy()); // ���� �̸��� �����ϸ� ���ο� �̸� �ο���
		
		// ���ε�� ���� �̸� ���ϱ�
		String fileName = multi.getFilesystemName("uploadFile");
		if(fileName==null) { // ���ε� �ȵ�
			System.out.println("���� ���ε� ���� ����");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<br>�۾���:"+multi.getParameter("name"));
			out.println("<br>����:"+multi.getParameter("title"));
			out.println("<br>���ϸ�:"+fileName);
		}
	}

}
