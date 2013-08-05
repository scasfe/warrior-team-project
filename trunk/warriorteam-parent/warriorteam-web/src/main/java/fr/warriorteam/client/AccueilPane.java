package fr.warriorteam.client;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.warriorteam.client.pane.HeaderPane;

/**
 * classes define Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class AccueilPane extends DockPanel {
	static int pan;
	static Label plus;
	private static AccueilPane instance;

	private AccueilPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static AccueilPane getInstance() {
		if (instance == null) {
			instance = new AccueilPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget child) {
		// TODO Auto-generated method stub
		return false;
	}

	private static void setup() {

		RootPanel.get().setStyleName("root");
		// Faire patienter barre de chargement

		// Chargement des proxys importants

		// Haut du panel

		RootPanel.get().add(HeaderPane.getInstance());

		// Centre de la page :

//		RootPanel.get().add(CenterPane.getInstance());
//
//		// Footer de la page
//
//		RootPanel.get().add(FooterPane.getInstance());

	}

	private static void buttonAdd(DockLayoutConstant position, int row, int col) {
		// Add a single button the the display area
		String posText = "";
		if (position == DockPanel.NORTH)
			posText = "NORTH";
		else if (position == DockPanel.SOUTH)
			posText = "SOUTH";
		else if (position == DockPanel.EAST)
			posText = "EAST";
		else if (position == DockPanel.WEST)
			posText = "WEST";
		else if (position == DockPanel.CENTER)
			posText = "CENTER";
		Button b = new Button(posText);
		instance.add(b, position);
	}
}
