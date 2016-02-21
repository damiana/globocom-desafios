package br.com.globocom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="server")
public class ServerModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_server;

	@Column(name="name_server")
	String name_server;
	
	@Column(name="username")
	String username;
	
	@Column(name="host")
	String host;
	
	@Column(name="password")
	String password;

	public int getId_server() {
		return id_server;
	}

	public void setId_server(int id_server) {
		this.id_server = id_server;
	}

	public String getName_server() {
		return name_server;
	}

	public void setName_server(String name_server) {
		this.name_server = name_server;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
