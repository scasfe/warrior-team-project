package fr.warriorteam.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.rpc.dto.CategoriesDTO;
import fr.warriorteam.rpc.dto.NewsDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("news")
public interface NewsService extends RemoteService {
	List<NewsDTO> searchLastNews() throws IllegalArgumentException;

	CategoriesDTO searchCategories() throws IllegalArgumentException;
}
