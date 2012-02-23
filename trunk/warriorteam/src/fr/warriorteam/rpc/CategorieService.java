package fr.warriorteam.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.dto.CategorieDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("categorie")
public interface CategorieService extends RemoteService {

	String createCategorie(CategorieDTO categorie);

	String deleteCategorie(CategorieDTO categorie);

}