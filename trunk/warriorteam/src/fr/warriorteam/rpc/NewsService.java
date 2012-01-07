package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.rpc.dto.NewsDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("news")
public interface NewsService extends RemoteService {
	NewsDTO searchLastNews() throws IllegalArgumentException;

}
