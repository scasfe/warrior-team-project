package fr.warriorteam.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.WTCreationCategorieDialogBox;
import fr.warriorteam.client.WTDialogBox;
import fr.warriorteam.rpc.LoginService;
import fr.warriorteam.rpc.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class MenuDroitePane extends VerticalPanel {
	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	/**
	 * L'instance en singleton
	 */
	private static MenuDroitePane instance;

	/**
	 * Les attributs du menu de droite
	 */
	private static Label titleCommentaires;
	private static Label creationCategorieLabel;

	private MenuDroitePane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static MenuDroitePane getInstance() {
		if (instance == null) {
			instance = new MenuDroitePane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

		loginService.checkSession(new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				final WTDialogBox dialogBox = new WTDialogBox(new HTML(
						"Erreur : le serveur ne répond pas !"
								+ "<br><br> Vérifiez votre connection"));
				dialogBox.get().setText("Le serveur ne répond pas");

				// Add a handler to close the DialogBox
				dialogBox.getCloseButton().addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.get().hide();
					}
				});

			}

			public void onSuccess(Boolean sessionValide) {
				if (sessionValide) {
					titleCommentaires.setText("Connecté !!!");

					creationCategorieLabel.setVisible(true);
				} else {
					// le titre du menu

					titleCommentaires.setText("Vous n'êtes pas connecté");
					creationCategorieLabel.setVisible(false);
				}
			}

		});

	}

	private static void setup() {

		// Vertical Panel de droite

		// Label pseudo
		titleCommentaires = new Label();
		titleCommentaires.setStyleName("element_menu");
		instance.add(titleCommentaires);

		// Panel commentaires
		// TODO - créer Panel commentaires

		// Label création catégories
		creationCategorieLabel = new Label("Créer catégorie");
		creationCategorieLabel.setStyleName("element_menu");

		class CreationCategorieHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// methode de traitement
				// TODO - Créer WTCreationCategorieDialogBox
				WTCreationCategorieDialogBox.getInstance().show();
			}
		}
		creationCategorieLabel.addClickHandler(new CreationCategorieHandler());
		instance.add(creationCategorieLabel);

		instance.getElement().setId("menuD");

	}

}
