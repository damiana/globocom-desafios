package br.com.globocom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.globocom.EntityManager.JpaEntityManager;
import br.com.globocom.model.ServerModel;
import br.com.globocom.ssh.SSHSession;

/**
 * Servlet implementation class ServerServlet
 */
public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	JpaEntityManager JPAEM = new JpaEntityManager();
	EntityManager objEM = JPAEM.getEntityManager();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ServerModel> sm = objEM.createQuery(
                "SELECT s FROM ServerModel s", ServerModel.class).getResultList();
	
		response.setContentType("application/json");
		
		
		PrintWriter out = response.getWriter();

	    String user = sm.get(0).getUsername();
	    String host = sm.get(0).getHost();
	    int port = sm.get(0).getPort();

	    SSHSession sshsession = new SSHSession(user, host, port);
	    sshsession.execCreateFile();


	    JSONObject obj = new JSONObject();
	    
	    obj.put("servidor", user);
	    
	    JSONArray listServices = new JSONArray();
	    JSONArray listPackges = new JSONArray();
	    
	    for (int i = 0; i < sshsession.getListServices().size(); i++) {
			//System.out.println(sshsession.getListServices().get(i));
			
			listServices.add(sshsession.getListServices().get(i));
		}
		    
	    obj.put("services", listServices);
	    
	    listPackges.add("br");
	    listPackges.add("breeee");
	    listPackges.add("br2313");
	    listPackges.add("br10000");
	    
	    obj.put("packges", listPackges);
	    
	    out.println(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
