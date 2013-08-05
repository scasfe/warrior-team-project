package fr.warriorteam.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.dto.NewsResultDTO;

@RemoteServiceRelativePath("news")
public interface RpcNewsService extends RemoteService{
	 /**
     * Processus declenché suite a un login réussi.
     * 
     * @return Le resultat du processus.
     */
    NewsResultDTO processSuccess();

}
