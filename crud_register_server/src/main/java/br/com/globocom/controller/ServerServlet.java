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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.globocom.model.ServerModel;


public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("InfoLogging");
			
    public ServerServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.setContentType("application/json");
		
		LOGGER.info("******** INICIO log *******");

		String serverList = (String)request.getParameter("serverList");
		
		LOGGER.info("\n******** SERVER-LIST PARAM  ******* " + serverList +"\n");

		LOGGER.info("\n******** tamanho array  ******* " + serverList +"\n");
		
		//for (int i = 0; i < size; i++) {
			
		//}
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(serverList);
		
		for (int i = 0; i < jsonArray.size(); i++) {
			LOGGER.info("\n******** jsonArray FOR  ******* " + jsonArray.get(i) +"\n");
			JsonElement server = jsonParser.parse(jsonArray.get(i).toString());
			JsonElement id_server = server.getAsJsonObject().get("id_server");
			JsonElement name_server = server.getAsJsonObject().get("name_server");
			
			LOGGER.info("\n******** ELEMENTS FOR  ******* " + id_server + " " + name_server +"\n");
		}
		
		//LOGGER.info("\n******** jsonArray array  ******* " + jsonArray.size() +"\n");
		//LOGGER.info("\n******** jsonArray NORMAL  ******* " + jsonArray +"\n");
		
		
		LOGGER.info("******** END log *******");
		
		
		
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
