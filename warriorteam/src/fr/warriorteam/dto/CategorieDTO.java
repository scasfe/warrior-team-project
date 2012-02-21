package fr.warriorteam.dto;

import java.io.Serializable;

public class CategorieDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439042456683686594L;
	private Long id;
	private String nomCategorie;
	private String dossier;
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getDossier() {
		return dossier;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

}
