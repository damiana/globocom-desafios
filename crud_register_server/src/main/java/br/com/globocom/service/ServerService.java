package br.com.globocom.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.globocom.EntityManager.JpaEntityManager;
import br.com.globocom.model.ServerModel;

@Path("/server")
public class ServerService {

	private JpaEntityManager JPAEM = new JpaEntityManager();
	private EntityManager objEM = JPAEM.getEntityManager();

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServerModel> list() {

		try {
			String query = "select s from ServerModel s";
			List<ServerModel> servers = objEM.createQuery(query, ServerModel.class).getResultList();
			objEM.close();

			return servers;

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/search/{id_server}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServerModel search(@PathParam("id_server") int id_server) {

		try {
			ServerModel server = objEM.find(ServerModel.class, id_server);
			objEM.close();

			return server;

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(ServerModel objServer){
		try {
			objEM.getTransaction().begin();
			objEM.persist(objServer);
			objEM.getTransaction().commit();
			objEM.close();
			
			return Response.status(200).entity("register success!!!").build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	
	@PUT
	@Path("/modify")
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(ServerModel objClinte){
		try {
			objEM.getTransaction().begin();
			objEM.merge(objClinte);
			objEM.getTransaction().commit();
			objEM.close();

			return Response.status(200).entity("update success!!!").build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/delete/{id_server}")
	public Response delete(@PathParam("id_server") int id_server){
		try {
			ServerModel objServer = objEM.find(ServerModel.class, id_server);

			objEM.getTransaction().begin();
			objEM.remove(objServer);
			objEM.getTransaction().commit();
			objEM.close();

			return Response.status(200).entity("delete success!!!").build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
