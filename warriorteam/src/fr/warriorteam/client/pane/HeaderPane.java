package fr.warriorteam.client.pane;

import fr.warriorteam.client.WTLoginPane;

import com.google.gwt.user.client.ui.HTML;

import com.google.gwt.user.client.ui.HorizontalPanel;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class HeaderPane extends HorizontalPanel {

	/** 
	 *
	 */
	private static HeaderPane instance;

	private HeaderPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static HeaderPane getInstance() {
		if (instance == null) {
			instance = new HeaderPane();
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

		instance.setSpacing(10);
		instance.setVerticalAlignment(ALIGN_TOP);
		// TODO
		// panelHaut.add(WTLastPhotosPublished.getInstance());

		// Temporaire : à virer une fois le widget LastPhoto codé
		VerticalPanel lastPhotos = new VerticalPanel();
		lastPhotos.setSize("300", "150");
		lastPhotos.add(new HTML("<h2>Dernieres photos :</h2>"
				+ "Postee par Bouly le 04/09/2011 a 00h18"
				+ "<br><br> <img src=\"exemple.jpg\"/>"));
		lastPhotos.setVerticalAlignment(ALIGN_TOP);

		VerticalPanel title = new VerticalPanel();
		title.setVerticalAlignment(ALIGN_TOP);
		title.add(new HTML("<h1>WarriorTeam Photo Starter Project</h1>"));

		instance.add(lastPhotos);
		instance.add(title);
		instance.add(WTLoginPane.getInstance());
		// panelHaut.setStyleName("element_menu");
		instance.getElement().setId("en_tete");

	}

}
