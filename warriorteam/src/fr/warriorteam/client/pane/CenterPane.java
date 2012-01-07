package fr.warriorteam.client.pane;

import fr.warriorteam.client.WTVerticalPane;
import fr.warriorteam.client.menu.MenuDroitePane;
import fr.warriorteam.client.menu.MenuGauchePane;
import fr.warriorteam.client.news.NewsPane;

import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class CenterPane extends HorizontalPanel {

	/**
	 * Obligation de mettre un HorizontalPanel pour le HeaderPane pour
	 * l'association avec le RootPanel
	 */
	private static CenterPane instance;

	// Le widget central entre les menus
	private static WTVerticalPane activeCenterWidget;

	private CenterPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static CenterPane getInstance() {
		if (instance == null) {
			instance = new CenterPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout
		rechargerActiveCenterWidget();
	}

	private static void setup() {

		// Centre de la page :
		// Un horizontal Panel contenant un vertical Panel

		instance.setSpacing(10);
		instance.setVerticalAlignment(ALIGN_TOP);
		instance.getElement().setId("element_center");

		instance.add(MenuGauchePane.getInstance());
		activeCenterWidget = NewsPane.getInstance();
		instance.add(activeCenterWidget);
		instance.add(MenuDroitePane.getInstance());

	}

	public void changeActiveCenterWidget(WTVerticalPane newWidget) {
		// Vu que ce center pane est un horizontal pane
		// il faut dabord enlever le menu de droite pour le remettre
		CenterPane.getInstance().remove(MenuDroitePane.getInstance());
		CenterPane.getInstance().remove(activeCenterWidget);
		CenterPane.activeCenterWidget = newWidget;
		CenterPane.instance.add(activeCenterWidget);
		CenterPane.instance.add(MenuDroitePane.getInstance());
	}

	private static void rechargerActiveCenterWidget() {
		activeCenterWidget.reloadData();
	}
}
