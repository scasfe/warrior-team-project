package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.rpc.dto.CategoriesDTO;
import fr.warriorteam.rpc.dto.NewsDTO;

public interface NewsServiceAsync {

	void searchLastNews(AsyncCallback<NewsDTO> callback);
	
	void searchCategories(AsyncCallback<CategoriesDTO> callback);

}
