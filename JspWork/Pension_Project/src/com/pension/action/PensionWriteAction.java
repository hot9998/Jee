package com.pension.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pension.model.PensionDAO;
import com.pension.model.PensionDTO;

/**
 * Servlet implementation class PensionWriteAction
 */
@WebServlet("/pensionWrite.go")
public class PensionWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PensionWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.sendRedirect("/Pension_Project/pension/pensionWrite.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		
		String savePath = "/pension/image";
		int uploadFileSize = 5 * 1024 * 1024;
		String encType = "UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);		
		
		MultipartRequest multi = new MultipartRequest(request,
				uploadFilePath,
				uploadFileSize,
				encType,
				new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("file");		
		
		PensionDTO p = new PensionDTO();		
		p.setContent(multi.getParameter("content"));
		p.setLowseasonPrice(Integer.parseInt(multi.getParameter("lowseasonPrice")));
		p.setMperson(Integer.parseInt(multi.getParameter("mperson")));
		p.setName(multi.getParameter("name"));
		p.setPeakPrice(Integer.parseInt(multi.getParameter("peakPrice")));
		p.setSperson(Integer.parseInt(multi.getParameter("sperson")));
		p.setImgname(fileName);
		p.setSrc(uploadFilePath);		
			
		PensionDAO dao = PensionDAO.getInstance();		
		dao.pensionWrite(p);
		response.sendRedirect("/Pension_Project/pension/pensionMain.jsp");				
	}

}
