package fr.warriorteam.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.dto.NewsResultDTO;

public interface RpcNewsServiceAsync {
	 /**
     * Singleton côté client pour l'appel au service RPC.
     */
	RpcNewsServiceAsync INSTANCE = GWT.create(RpcNewsService.class);

    void processSuccess(AsyncCallback<NewsResultDTO> callback);

}
