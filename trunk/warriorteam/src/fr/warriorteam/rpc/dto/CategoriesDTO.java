package fr.warriorteam.rpc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.warriorteam.dto.CategorieDTO;

/**
 * TODO
 * 
 * @author Yvan
 * 
 */
public class CategoriesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1894178910617732714L;
	/**
	 * 
	 */

	private List<CategorieDTO> categories = new ArrayList<CategorieDTO>();

	public List<CategorieDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategorieDTO> categories) {
		this.categories = categories;
	}

}
