package fr.warriorteam.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.rpc.dto.CategoriesDTO;
import fr.warriorteam.rpc.dto.NewsDTO;

public interface NewsServiceAsync {

	void searchLastNews(AsyncCallback<List<NewsDTO>> callback);

	void searchCategories(AsyncCallback<CategoriesDTO> callback);

}
