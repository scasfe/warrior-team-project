package fr.warriorteam.dto;

import java.io.Serializable;

public class ImageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2408875460263542955L;

	private String nomImage;
	private String posteur;
	private String commentaires;

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getPosteur() {
		return posteur;
	}

	public void setPosteur(String posteur) {
		this.posteur = posteur;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

}
