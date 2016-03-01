package crud_register_server;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.globocom.model.ServerModel;

public class TestJson {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.out.println("inicial");
	
		ServerModel s1 = new ServerModel();
		s1.setId_server(1);
		s1.setName_server("videoplay");
	
		ServerModel s2 = new ServerModel();
		s2.setId_server(2);
		s2.setName_server("musicplay");
		
		ServerModel s3 = new ServerModel();
		s3.setId_server(3);
		s3.setName_server("datacenter");
		
		ArrayList<ServerModel> listServer = new ArrayList<ServerModel>();
		listServer.add(s1);
		listServer.add(s2);
		listServer.add(s3);
		
		System.out.println(listServer.size());
						
		ArrayList<StringWriter> serverList = new ArrayList<StringWriter>();
		
		
		for (int i = 0; i < listServer.size(); i++) {
			//System.out.println(listServer.get(i).getName_server());
			
			StringWriter sw = new StringWriter();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonGenerator generator = mapper.getFactory().createGenerator(sw);
			JsonNode treeRootNode = mapper.createObjectNode(); 
			
			((ObjectNode) treeRootNode).put("server", listServer.get(i).getName_server());
			ArrayNode arrayNode = ((ObjectNode) treeRootNode).putArray("services");
			arrayNode.add("mysql");
	        arrayNode.add("apache");
	        arrayNode.add("tomcat");
	        arrayNode.add("nginx");
	        arrayNode.add("java");
	        arrayNode.add("vim-gnome");
	        
	        
	       mapper.writeTree(generator, treeRootNode);
	        
	        serverList.add(sw);
	        
		}
		
		System.out.println(" huhh " + serverList.get(0));
		
		System.out.println("\n");
		ObjectMapper mapper1 = new ObjectMapper();
		JsonNode actualObj = mapper1.readTree("{\"k1\":\"v1\"}");
		System.out.println( actualObj.get("k1") );
		
		
		
		String json = "[{\"id_server\":1,\"name_server\":\"globocom\",\"username\":\"vagrant\",\"password\":\"vagrant\",\"host\":\"192.168.33.30\",\"port\":22},"
				+ "{\"id_server\":2,\"name_server\":\"top\",\"username\":\"vagrant\",\"password\":\"vagrant\",\"host\":\"192.168.33.32\",\"port\":22},"
				+ "{\"id_server\":\3,\"name_server\":\"azul\",\"username\":\"teste dam 1\",\"password\":\"123456\",\"host\":\"1111111\",\"port\":1111111}]";
		
		ObjectMapper mapper2 = new ObjectMapper();
		//ServerModel[] sortings = mapper2.readValue(json, TypeFactory.defaultInstance().constructArrayType(ServerModel.class));
		
	/*	JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		
		List<ServerModel> lstUser = null;
		
		TypeReference<List<ServerModel>> tRef = new TypeReference<List<ServerModel>>() {};
	    lstUser = mapper2.readValue(jp, tRef);
	    for (ServerModel user : lstUser) {
	        System.out.println(user.toString());
	    }
		
		//System.out.println(sortings); */
		
		
	}
}
