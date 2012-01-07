package fr.warriorteam.rpc.dto;

import java.io.Serializable;

/**
 * TODO
 * 
 * @author Yvan
 * 
 */
public class LoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8472210926066685359L;
	private String login;
	private String pwdEncrypted;

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String getPwdEncrypted() {
		return pwdEncrypted;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public void setPwdEncrypted(String pwdEncrypted) {
		this.pwdEncrypted = pwdEncrypted;
	}

}
