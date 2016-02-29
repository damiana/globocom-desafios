package br.com.globocom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.fabric.xmlrpc.base.Array;

import br.com.globocom.model.ServerModel;
import br.com.globocom.ssh.SSHSession;


public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("InfoLogging");

	public ServerServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();


		LOGGER.info("******** INICIO log *******");

		String servers = (String)request.getParameter("serverList");

		LOGGER.info("\n******** SERVER-LIST PARAM  ******* " + servers +"\n");


		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(servers);
		
		
		JSONObject obj = new JSONObject();
		JSONArray listServices = new JSONArray();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonElement server = jsonParser.parse(jsonArray.get(i).toString());

			//int idServer = server.getAsJsonObject().get("id_server").getAsInt();
			String nameServer = server.getAsJsonObject().get("name_server").toString().replaceAll("\"", "");
			String user = server.getAsJsonObject().get("username").toString().replaceAll("\"", "");
			String host = server.getAsJsonObject().get("host").toString().replaceAll("\"", "");
			String password = server.getAsJsonObject().get("password").toString().replaceAll("\"", "");
			int port = server.getAsJsonObject().get("port").getAsInt();

			//obj.put("server", nameServer);
			
			SSHSession sshsession = new SSHSession(user,password,host,port);
			sshsession.execCreateFile();

			//LOGGER.info("\n SERVERRRR: " + nameServer + " LISTA SERVICOS NOS SERVIDORES DIRETO " + sshsession.getListServices().size());
			//LOGGER.info("\n SERVERRRR: " + nameServer + " MERDAAAAAAAA " + sshsession.getListServices());

			//for (int j = 0; j < sshsession.getListServices().size(); j++) {
			//	LOGGER.info(" OS SERVICOS: " + " index --> " + j +  sshsession.getListServices().get(j));
			listServices.add(sshsession.getListServices());
			//}
			
			//LOGGER.info("\n SERVERRRR merdaaaaaa: " + sshsession.getListServices() );
			
			obj.put("services", listServices);
		}
		
		out.println(obj);
		
		//LOGGER.info("\n\n LOG SERVICOS MERDAAAAAAA listServices: " + listServices );
		LOGGER.info("\n\n LOG SERVICOS TAMANHO: " + listServices.size() );
		
		for (int i = 0; i < listServices.size(); i++) {
			LOGGER.info("\n\n LOG SERVICOS MERDAAAAAAA listServices: " + listServices.get(i).toString() );
			
		}
		LOGGER.info("\n\n JSON COMPLETO :::: " + obj);
		LOGGER.info("******** END log *******");

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
