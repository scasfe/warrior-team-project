package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.rpc.dto.LoginDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface LoginService extends RemoteService {
	String login(LoginDTO loginDto) throws IllegalArgumentException;

	boolean checkSession();

	String logout();
}
