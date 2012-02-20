package fr.warriorteam.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class WTCreationCategorieDialogBox {

	private static WTCreationCategorieDialogBox instance;
	private static WTDialogBox dialogBox;
	private static Label errorLabel;

	/**
	 * Instance singleton côté client donc constructeur private
	 */
	private WTCreationCategorieDialogBox() {

	}

	private static void setup() {

		// Ajout des fields

		final Label nom = new Label("Nom Categorie : ");
		final TextBox nomInput = new TextBox();
		final Label date = new Label("Date : ");
		final TextBox dateInput = new TextBox();
		final Button submitBouton = new Button("Creer");
		nomInput.setSize("50", "10");
		dateInput.setSize("50", "10");

		// createButton.setEnabled(true);
		// createButton.setEnabled(true);
		// deconnectionButton.setEnabled(true);
		//
		// // Définition de la taille des boutons
		// connectButton.setPixelSize(100, 30);
		// createButton.setPixelSize(110, 30);
		// deconnectionButton.setPixelSize(110, 30);
		// final HTML reply = new HTML();

		// fields.add(login);
		// fields.add(loginInput);
		// fields.add(password);
		// fields.add(passwordInput);
		// fields.add(reply);

		errorLabel = new Label("Test");
		dialogBox = new WTDialogBox(nom, nomInput, date, dateInput,
				submitBouton, errorLabel);
		dialogBox.get().setText("Ajout d'une categorie");
		dialogBox.get().setAnimationEnabled(false);
		dialogBox.get().center();
		dialogBox.getCloseButton().setFocus(true);
	}

	public static WTCreationCategorieDialogBox getInstance() {
		if (instance == null) {
			instance = new WTCreationCategorieDialogBox();
			setup();
		}

		return instance;
	}

	public void show() {
		dialogBox.get().center();
		dialogBox.getCloseButton().setFocus(true);
		dialogBox.get().setVisible(true);
	}

}
