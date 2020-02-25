package com.pension.action;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pension.model.PensionDAO;
import com.pension.model.PensionDTO;

/**
 * Servlet implementation class PensionDeleteAction
 */
@WebServlet("/pensionDelete.go")
public class PensionDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PensionDeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		PensionDAO dao = PensionDAO.getInstance();		
		PensionDTO dto = dao.pensionView(rnum);
		String src = dto.getSrc();		
		String filename = dto.getImgname();		
		
		File file = new File(src+"/"+filename);
		if(file.exists()) {
			file.delete();			
		}
		dao.pensionDelete(rnum);
		response.sendRedirect("/Pension_Project/pensionMain.go");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
