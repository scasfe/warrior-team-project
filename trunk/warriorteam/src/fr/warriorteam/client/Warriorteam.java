package fr.warriorteam.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entr�e
 * affichant un root panel permettant de s'identifier
 */
public class Warriorteam implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// TODO
		// On affiche une image de chargement le temps de voir si l'utilisateur
		// est bien connect� ou non

		AccueilPane.getInstance();

	}

}
