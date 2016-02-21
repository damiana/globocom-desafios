package br.com.globocom.ssh;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.UserInfo;

public class SSHUserInfo implements UserInfo {

	String password;
	JTextField passwordField=(JTextField) new JPasswordField(20);


	public String getPassphrase() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	public boolean promptPassphrase(String arg0) {
		return true;
	}

	public boolean promptPassword(String message) {

		Object[] ob = { passwordField };
		int result = JOptionPane.showConfirmDialog(null, ob, "Enter the Server password:",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			password = passwordField.getText();
			
			return true;
			
		} else { 
			return false; 
		}
	}

	public boolean promptYesNo(String arg0) {
		return true;
	}

	public void showMessage(String arg0) {

	}

}
