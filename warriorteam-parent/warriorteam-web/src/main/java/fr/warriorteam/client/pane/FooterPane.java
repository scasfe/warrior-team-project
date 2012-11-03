package fr.warriorteam.client.pane;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class FooterPane extends HorizontalPanel {

	private static FooterPane instance;

	private FooterPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static FooterPane getInstance() {
		if (instance == null) {
			instance = new FooterPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

	}

	private static void setup() {

		// Bas de la page :
		// Un horizontal Panel contenant un vertical Panel

		instance.setSpacing(10);
		instance.setVerticalAlignment(ALIGN_TOP);
		instance.getElement().setId("footer");

		// le titre
		Label title = new Label();
		title.setText("Realisation : Yvan Serieye - Plugin GWT (Google Web Tools) - 2011");
		title.setStyleName("align_centre");
		// les catégories
		// Appel RPC pour les catégories

		// Methode de chargement des catégories

		instance.add(title);

	}

}
