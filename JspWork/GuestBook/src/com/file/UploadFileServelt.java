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
		String uploadFilePath = context.getRealPath(savePath); // 서버상의 실제 디렉토리
		//System.out.println("uploadFilePath:" + uploadFilePath);

		MultipartRequest multi = new MultipartRequest(request, //request 객체
				uploadFilePath, // 서버상의 실제 디렉토리
				uploadFileSize, // 최대 업로드 사이즈
				encType, // 인코딩방법				 
				new DefaultFileRenamePolicy()); // 동일 이름이 존재하면 새로운 이름 부여됨
		
		// 업로드된 파일 이름 구하기
		String fileName = multi.getFilesystemName("uploadFile");
		if(fileName==null) { // 업로드 안됨
			System.out.println("파일 업로드 되지 않음");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<br>글쓴이:"+multi.getParameter("name"));
			out.println("<br>제목:"+multi.getParameter("title"));
			out.println("<br>파일명:"+fileName);
		}
	}

}
