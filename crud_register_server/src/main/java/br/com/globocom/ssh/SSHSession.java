package br.com.globocom.ssh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHSession {

	Session session = null;
	ChannelExec channel = null;

	String user;
	int port;
	String host;
	String password;
	
	ArrayList<String> listServices = new ArrayList<String>();
	
	public SSHSession(String user, String password, String host, int port) {
		
		this.user = user;
		this.password = password;
		this.host = host;
		this.port = port;

	}
	
	public void execCreateFile() {

		try {

			JSch jsch = new JSch();
			
			session = jsch.getSession(user,host,port);
			
			session.setUserInfo(new SSHUserInfo());
			session.setPassword(password);
			
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			
			session.connect();
			
			String listServicesInstalled = 
					" initctl list | egrep -v  "
							+ " \" stop/waiting|^tty\"; service --status-all 2>&1 | egrep -v "
							+ " \"\\[ (\\?) \\]\"\\ ";
			
			channel = (ChannelExec) session.openChannel("exec");
			
			InputStream stream = channel.getInputStream();
			channel.setCommand(listServicesInstalled);	        
			channel.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            
            while ((line = reader.readLine()) != null && true) {
            	//System.out.println("line " + line);
            	listServices.add(line);
            }
            
	        //System.out.println("Finished sending commands!" + listServices.size());
	        
	        
		} catch (Exception e) {

			e.printStackTrace(System.err);

		} finally {

			if(channel != null) channel.disconnect(); 
			if(session !=null ) session.disconnect();
		}
	}
	
	public ArrayList<String> getListServices() {
		return listServices;
	}
	
}
