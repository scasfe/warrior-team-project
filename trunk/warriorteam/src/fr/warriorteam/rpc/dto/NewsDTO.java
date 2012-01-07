package fr.warriorteam.rpc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author Yvan
 * 
 */
public class NewsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1894178910617732714L;
	/**
	 * 
	 */

	private List<String> login = new ArrayList<String>();

	public List<String> getLogin() {
		return login;
	}

	public void setLogin(List<String> login) {
		this.login = login;
	}

}
