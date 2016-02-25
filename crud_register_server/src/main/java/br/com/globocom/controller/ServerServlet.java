package br.com.globocom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.globocom.model.ServerModel;


public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServerServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		
        //String user = (String)request.getParameter("user");
        //String host = (String)request.getParameter("host");
        //String password = (String)request.getParameter("password");
        
        //int port = Integer.parseInt(request.getParameter("port"));
        
       // System.out.println("user " + user);
        //System.out.println("host " + host);
        //System.out.println("port " + port);
       // System.out.println("password " + password);
        
        ArrayList<ServerModel> sm = new ArrayList<ServerModel>();
        //sm = (ArrayList<ServerModel>)request.getAttribute("servers");

        ///(ArrayList<ServerModel>)request.getAttribute("servers");
        
        //for (int i = 0; i < array.length; i++) {
			
		//}
        
        PrintWriter out = response.getWriter();
        
        //for (int i = 0; i < sm.size(); i++) {
        	 out.print("Minha Servlet TESTE ULTIMO " + (String)request.getParameter("listserver" ));
		//}
        	 //out.(jsonObject);
        	 out.flush();
        
		
/*	    JSONObject obj = new JSONObject();
	    JSONArray listServices = new JSONArray();
	    
		for (int i = 0; i < sm.size(); i++) {
			
		    String user = sm.get(i).getUsername();
		    String host = sm.get(i).getHost();
		    int port = sm.get(i).getPort();

		    SSHSession sshsession = new SSHSession(user, host, port);
		    sshsession.execCreateFile();

		    obj.put("servidor", user);		    

		    for (int j = 0; j < sshsession.getListServices().size(); j++) {			
				listServices.add(sshsession.getListServices().get(j));
			}
		}
		
		    
	    obj.put("services", listServices);
	    */
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
