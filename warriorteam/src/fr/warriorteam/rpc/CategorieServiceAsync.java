package fr.warriorteam.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.dto.CategorieDTO;

public interface CategorieServiceAsync {

	void createCategorie(CategorieDTO categorie, AsyncCallback<String> callback);

}
