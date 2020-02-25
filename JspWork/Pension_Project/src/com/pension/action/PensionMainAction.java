package com.pension.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pension.model.PensionDAO;
import com.pension.model.PensionDTO;

/**
 * Servlet implementation class PensionMainAction
 */
@WebServlet("/pensionMain.go")
public class PensionMainAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PensionMainAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.sendRedirect("/Pension_Project/pension/pensionMain.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		PensionDAO dao = PensionDAO.getInstance();
		ArrayList<PensionDTO> arr = dao.pensionList();
		request.setAttribute("dto", arr);
		RequestDispatcher rd = request.getRequestDispatcher("/pension/pensionPage.jsp");
		rd.forward(request, response);	
	}

}

