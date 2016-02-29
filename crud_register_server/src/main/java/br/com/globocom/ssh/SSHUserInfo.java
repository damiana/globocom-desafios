package br.com.globocom.ssh;

import com.jcraft.jsch.UserInfo;

public class SSHUserInfo implements UserInfo {

	String password;
	
	public String getPassphrase() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public boolean promptPassphrase(String arg0) {
		return false;
	}

	public boolean promptPassword(String password) {

		return false;	
	}

	public boolean promptYesNo(String arg0) {
		return false;
	}

	public void showMessage(String arg0) {

	}

}
