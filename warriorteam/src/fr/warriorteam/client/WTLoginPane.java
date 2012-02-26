package fr.warriorteam.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.menu.MenuDroitePane;
import fr.warriorteam.client.menu.MenuGauchePane;
import fr.warriorteam.client.news.NewsPane;
import fr.warriorteam.client.pane.CenterPane;
import fr.warriorteam.rpc.LoginService;
import fr.warriorteam.rpc.LoginServiceAsync;
import fr.warriorteam.rpc.dto.LoginDTO;
import fr.warriorteam.server.utils.CryptageDonneesUtils;
import fr.warriorteam.shared.FieldVerifier;

public class WTLoginPane extends VerticalPanel {

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static LoginServiceAsync loginService = GWT
			.create(LoginService.class);

	private static WTLoginPane instance;
	private final static HorizontalPanel panel1 = new HorizontalPanel();
	private final static HorizontalPanel panel2 = new HorizontalPanel();
	private final static Button connectButton = new Button("Se connecter");
	private final static Button createButton = new Button("Creer un compte");
	private final static Button deconnectionButton = new Button(
			"Se deconnecter");

	public static WTLoginPane getInstance() {
		if (instance == null) {
			instance = new WTLoginPane();
			setup();
		}
		loadDynamicData();
		return instance;
	}

	public static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Checker la session pour voir ce que l'on affiche
		// Vérification de l'existence d'une session
		loginService.checkSession(new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				final WTDialogBox dialogBox = new WTDialogBox(new HTML(
						"Erreur : le serveur ne répond pas !"
								+ "<br><br> Vérifiez votre connection"));
				dialogBox.get().setText("Le serveur ne répond pas");

				// Add a handler to close the DialogBox
				// dialogBox.getCloseButton().addClickHandler(new ClickHandler()
				// {
				// public void onClick(ClickEvent event) {
				// dialogBox.get().hide();
				// }
				// });

			}

			public void onSuccess(Boolean sessionValide) {
				// Si une session est active on affiche le bouton déconnection
				// de l'utilisateur

				if (sessionValide) {
					panel1.setVisible(false);
					connectButton.setVisible(false);
					createButton.setVisible(false);
					deconnectionButton.setVisible(true);
				} else {

					// Sinon on affiche l'écran de login
					panel1.setVisible(true);
					connectButton.setVisible(true);

					// Actuellement d"ésactivé creer compte
					createButton.setVisible(false);
					createButton.setEnabled(false);

					deconnectionButton.setVisible(false);
				}
			}

		});

	}

	private static void setup() {
		// TODO Auto-generated method stub
		// instance.setSize("480", "100");
		final Label login = new Label("Login : ");
		final TextBox loginInput = new TextBox();
		final Label password = new Label("Password : ");
		final TextBox passwordInput = new PasswordTextBox();
		loginInput.setSize("50", "10");
		passwordInput.setSize("50", "10");

		createButton.setEnabled(true);
		createButton.setEnabled(true);
		deconnectionButton.setEnabled(true);

		// Définition de la taille des boutons
		connectButton.setPixelSize(100, 30);
		createButton.setPixelSize(110, 30);
		deconnectionButton.setPixelSize(110, 30);
		final HTML reply = new HTML();

		panel1.add(login);
		panel1.add(loginInput);
		panel1.add(password);
		panel1.add(passwordInput);
		panel1.add(reply);

		panel2.add(connectButton);
		panel2.add(createButton);
		final Label errorLabel = new Label("");

		panel1.setSpacing(10);
		panel2.setSpacing(10);

		instance.add(panel1);
		instance.add(panel2);
		instance.add(deconnectionButton);
		instance.add(errorLabel);

		// Crée la popup dialog Box
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();

		final WTDialogBox dialogBox = new WTDialogBox(

		new HTML("<b>Sending name to the server:</b>"), textToServerLabel,
				new HTML("<br><b>Server replies:</b>"), serverResponseLabel

		);

		// Add a handler to close the DialogBox
		dialogBox.getCloseButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.get().hide();
				createButton.setEnabled(true);
				connectButton.setEnabled(true);

				connectButton.setFocus(true);

			}
		});

		// Create a handler for the sendButton and nameField
		class ConnectButtonHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendLoginDtoToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendLoginDtoToServer();
				}
			}

			/**
			 * Permet d'appeler le service RPC LoginService pour une tentative
			 * de connection
			 */
			private void sendLoginDtoToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String login = loginInput.getText();
				String password = passwordInput.getText();

				if (!FieldVerifier.isValidName(login)
						|| !FieldVerifier.isValidName(password)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}
				password = CryptageDonneesUtils.crypt(password);

				LoginDTO input = new LoginDTO();
				input.setLogin(login);
				input.setPwdEncrypted(password);

				// Then, we send the input to the server.
				connectButton.setEnabled(false);
				createButton.setEnabled(false);
				textToServerLabel.setText(login);
				serverResponseLabel.setText("");
				loginService.login(input, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.get().setText("Erreur :");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(caught.getMessage());
						dialogBox.get().center();
						dialogBox.getCloseButton().setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.get().setText("Bienvenue !");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.get().center();
						dialogBox.getCloseButton().setFocus(true);

						panel1.setVisible(false);
						connectButton.setVisible(false);
						createButton.setVisible(false);
						deconnectionButton.setVisible(true);

						// On recharge tous les widgets pour activer les
						// fonctionnalités
						// réservées aux utilisateurs connectés
						// cela pour les menus et le widget central actif
						MenuGauchePane.getInstance();
						MenuDroitePane.getInstance();
						CenterPane.getInstance();

					}
				});
			}
		}

		// Add a handler to send the name to the server
		ConnectButtonHandler handler = new ConnectButtonHandler();
		connectButton.addClickHandler(handler);
		loginInput.addKeyUpHandler(handler);
		passwordInput.addKeyUpHandler(handler);

		// Create a handler for the deconnectionButton
		class DisconnectButtonHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				disconnectFromServer();
			}

			/**
			 * Permet d'appeler le service RPC LoginService pour une tentative
			 * de connection
			 */
			private void disconnectFromServer() {
				loginService.logout(new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.get().setText("Erreur :");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(caught.getMessage());
						dialogBox.get().center();
						dialogBox.getCloseButton().setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.get().setText("Déconnection !");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.get().center();
						dialogBox.getCloseButton().setFocus(true);

						// on affiche l'écran de login
						panel1.setVisible(true);
						connectButton.setVisible(true);
						createButton.setVisible(true);
						deconnectionButton.setVisible(false);

						// On recharge tous les widgets pour désactiver les
						// fonctionnalités
						// réservées aux utilisateurs connectés
						// cela pour les menus et le widget central actif
						MenuGauchePane.getInstance();
						MenuDroitePane.getInstance();
						CenterPane.getInstance().changeActiveCenterWidget(
								NewsPane.getInstance());

					}
				});
			}
		}

		// Add a handler to send the name to the server
		DisconnectButtonHandler handlerDisconnect = new DisconnectButtonHandler();
		deconnectionButton.addClickHandler(handlerDisconnect);

	}

}
