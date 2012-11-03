package fr.warriorteam.rpc.dto;

import java.io.Serializable;

/**
 * TODO
 * 
 * @author Yvan
 * 
 */
public class NewsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1894178910617732714L;
	/**
	 * 
	 */

	private String date;

	private String titre;

	private String texte;

	private Boolean reservee;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getTexte() {
		return texte;
	}

	public void setReservee(Boolean reservee) {
		this.reservee = reservee;
	}

	public Boolean getReservee() {
		return reservee;
	}

}
