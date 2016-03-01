package br.com.globocom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.globocom.model.ServerModel;
import br.com.globocom.ssh.SSHSession;


public class ServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("InfoLogging");

	public ServerServlet() {
		super();
	}

	@SuppressWarnings({ "deprecation" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();


		LOGGER.info("******** INICIO log *******");

		String servers = (String)request.getParameter("serverList");

		LOGGER.info("\n******** SERVER-LIST PARAM  ******* " + servers +"\n");

		ObjectMapper mapper = new ObjectMapper();

		JsonFactory jsonFactory = new JsonFactory();
		JsonParser jsonParser = jsonFactory.createJsonParser(servers);
		
		List<ServerModel> listServerModel = null;
		
		TypeReference<List<ServerModel>> typeReference = new TypeReference<List<ServerModel>>() {};
		listServerModel = mapper.readValue(jsonParser, typeReference);
	    
		ArrayList<StringWriter> serverList = new ArrayList<StringWriter>();
		
	    for (ServerModel server : listServerModel) {
	    
	    	StringWriter sw = new StringWriter();
			ObjectMapper mapperServer = new ObjectMapper();
			JsonGenerator generator = mapperServer.getFactory().createGenerator(sw);
			JsonNode treeRootNode = mapperServer.createObjectNode(); 

			((ObjectNode) treeRootNode).put("server", server.getName_server());
			ArrayNode arrayNode = ((ObjectNode) treeRootNode).putArray("services");
			
			SSHSession sshsession = new SSHSession(server.getUsername(),server.getPassword(),server.getHost(),server.getPort());
			sshsession.execCreateFile();
			
			for (String services : sshsession.getListServices()) {
				arrayNode.add(services);
			}

			mapper.writeTree(generator, treeRootNode);
			serverList.add(sw);
	    }

	    LOGGER.info("\n OBJECT JSON CRIADO TAMANHO" + serverList.size() +"\n");
		LOGGER.info("\n OBJECT JSON CRIADO COM NAME SERVERS -- 1 " + serverList.get(0) +"\n");
		//LOGGER.info("\n OBJECT JSON CRIADO COM NAME SERVERS -- 2 " + serverList.get(1) +"\n");
		//LOGGER.info("\n OBJECT JSON CRIADO COM NAME SERVERS -- 3 " + serverList.get(2) +"\n");
		
	    out.print(serverList);
	    
	    //send serverList to ServerJsonServlet
	    HttpSession serverSession = request.getSession();
	    serverSession.setAttribute("serverList", serverList);	    
	    getServletContext().getRequestDispatcher("/ServerJsonServlet").forward(request,response);
	    
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
