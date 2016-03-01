package crud_register_server;

import java.util.ArrayList;

public class TestSSH {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {
		System.out.println("Inicial...");

		ArrayList<String> hosts = new ArrayList<String>();
		hosts.add("192.168.33.33");
		hosts.add("1.1.1.1.1.1");
		hosts.add("1.1.1.1.1.33");
		hosts.add("192.168.33.30");
		hosts.add("1.1.1.1.23");
		
		/*try {
			
			for (int i = 0; i < hosts.size(); i++) {
				
				SSHSession sshsession = new SSHSession("vagrant","vagrant",hosts.get(i), 22);
				sshsession.execCreateFile();
				System.out.println(sshsession.getListServices().size());
				
			}

		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}*/
		
	}
}
