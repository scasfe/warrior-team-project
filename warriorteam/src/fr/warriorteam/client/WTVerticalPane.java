package fr.warriorteam.client;

import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class WTVerticalPane extends VerticalPanel {

	/**
	 * A overrider pour recharger data en fonction de si l'utilisateur est
	 * connecté ou non
	 */
	public abstract void reloadData();
}
