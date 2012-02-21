package fr.warriorteam.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import fr.warriorteam.client.menu.MenuGauchePane;
import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.rpc.CategorieService;
import fr.warriorteam.rpc.CategorieServiceAsync;
import fr.warriorteam.rpc.WTModalAsyncCallback;
import fr.warriorteam.shared.FieldVerifier;

public class WTCreationCategorieDialogBox {

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static CategorieServiceAsync categorieService = GWT
			.create(CategorieService.class);

	private static WTCreationCategorieDialogBox instance;
	private static WTDialogBox dialogBox;
	private static Label nom;
	private static TextBox nomInput;
	private static Label date;
	private static TextBox dateInput;
	private static Button submitBouton;
	private static Label errorLabel;

	/**
	 * Instance singleton côté client donc constructeur private
	 */
	private WTCreationCategorieDialogBox() {

	}

	private static void setup() {

		// Ajout des fields

		nom = new Label("Nom Categorie : ");
		nomInput = new TextBox();
		date = new Label("Date (AAAA-MM): ");
		dateInput = new TextBox();
		submitBouton = new Button("Creer");
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

		// Ajout du click handler sur le bouton création
		createCreationHandler(submitBouton);

		errorLabel = new Label("Test");
		dialogBox = new WTDialogBox(nom, nomInput, date, dateInput,
				submitBouton, errorLabel);
		dialogBox.get().setText("Ajout d'une categorie");
		dialogBox.get().setAnimationEnabled(false);
		dialogBox.get().center();
		dialogBox.getCloseButton().setFocus(true);
	}

	private static void createCreationHandler(Button button) {
		// TODO Auto-generated method stub

		class CreateCategorieHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addCategorie();
			}

		}
		button.addClickHandler(new CreateCategorieHandler());

	}

	public static void addCategorie() {
		boolean error = false;
		StringBuilder msgErr = new StringBuilder();
		if (!FieldVerifier.isValidName(nomInput.getText())
				|| nomInput.getText().length() > 15) {
			msgErr.append("Format incorrect pour le nom (min 4 carac - max 15)<BR/>");
			error = true;
		}

		if (!FieldVerifier.isValidDate(dateInput.getText())) {
			msgErr.append("Date incorrect - rappel AAAA-MM ");
			error = true;
		}

		if (error) {
			errorLabel.setText(msgErr.toString());
			return;
		}

		// Création du DTO de catégorie
		CategorieDTO categorie = new CategorieDTO();
		categorie.setNomCategorie(nomInput.getText());
		String dossier = nomInput.getText().replaceAll(" ", "_") + "_"
				+ dateInput.getText();
		categorie.setDossier(dossier);
		categorie.setDate(dateInput.getText());

		// Appel du service RPC ajout catégorie
		categorieService.createCategorie(categorie,
				new WTModalAsyncCallback<String>() {

					@Override
					public void handleResult(String result) {
						errorLabel.setText(result);
						MenuGauchePane.getInstance();
					}
				});

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
