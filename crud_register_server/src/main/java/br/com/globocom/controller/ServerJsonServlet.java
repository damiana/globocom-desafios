package br.com.globocom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerJsonServlet
 */
public class ServerJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("InfoLoggingServlet");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();		
		String jsonServices = (String)request.getParameter("jsonServices");
		
		LOGGER.info("\n SERVET JSON " + jsonServices);
		
		out.println(jsonServices);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
