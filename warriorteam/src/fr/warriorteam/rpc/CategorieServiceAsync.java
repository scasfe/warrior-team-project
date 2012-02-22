package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.dto.CategorieDTO;

public interface CategorieServiceAsync {

	void createCategorie(CategorieDTO categorie, AsyncCallback<String> callback);

	void deleteCategorie(CategorieDTO categorie, AsyncCallback<String> callback);

}
