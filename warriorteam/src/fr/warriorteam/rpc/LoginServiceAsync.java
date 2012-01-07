package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.rpc.dto.LoginDTO;

public interface LoginServiceAsync {

	void login(LoginDTO loginDto, AsyncCallback<String> callback);

	void checkSession(AsyncCallback<Boolean> callback);

	void logout(AsyncCallback<String> callback);

}
