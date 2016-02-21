package br.com.globocom.ssh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
	public SSHSession(String user, String host, int port) {
		
		this.user = user;
		this.host = host;
		this.port = port;
	}
	
	public void execCreateFile() {

		try {

			JSch jsch = new JSch();

			session = jsch.getSession(user,host,port);
			
			session.setUserInfo(new SSHUserInfo());
			session.connect();
			
			String listServices = 
					" initctl list | egrep -v  "
							+ " \" stop/waiting|^tty\"; service --status-all 2>&1 | egrep -v "
							+ " \"\\[ (\\?) \\]\"\\ ";
			
			channel = (ChannelExec) session.openChannel("exec");
			
			InputStream stream = channel.getInputStream();
			channel.setCommand(listServices);	        
			channel.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null && true)
            	System.out.println("line " + line);

	        System.out.println("Finished sending commands!");
	        
	        
		} catch (Exception e) {

			e.printStackTrace(System.err);

		} finally {

			if(channel != null) channel.disconnect(); 
			if(session !=null ) session.disconnect();

		}
	}
}