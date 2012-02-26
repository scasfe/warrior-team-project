package fr.warriorteam.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import fr.warriorteam.client.news.CategoriesPane;
import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.rpc.FileUploadServiceAsync;
import fr.warriorteam.rpc.WTModalAsyncCallback;
import fr.warriorteam.shared.FieldVerifier;

public class WTAjoutCommentaireDialogBox {

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static FileUploadServiceAsync fileUploadServiceImpl = GWT
			.create(FileUploadService.class);

	private static WTAjoutCommentaireDialogBox instance;
	private static WTDialogBox dialogBox;
	private static Label commentaire;
	private static TextBox commentaireInput;
	private static Button submitBouton;
	private static String imageName;
	private static Label errorLabel;

	/**
	 * Instance singleton côté client donc constructeur private
	 */
	private WTAjoutCommentaireDialogBox() {

	}

	private static void setup() {

		// Ajout des fields

		commentaire = new Label("Message : ");
		commentaireInput = new TextBox();

		submitBouton = new Button("Repondre");
		commentaireInput.setSize("50", "10");

		// Ajout du click handler sur le bouton création
		createAjoutHandler(submitBouton);

		errorLabel = new Label("Test");
		errorLabel.addStyleName("serverResponseLabelError");
		dialogBox = new WTDialogBox(commentaire, commentaireInput,
				submitBouton, errorLabel);
		dialogBox.get().setText("Ajout d'un commentaire");
		dialogBox.get().setAnimationEnabled(false);
		dialogBox.get().center();
		dialogBox.getCloseButton().setFocus(true);
	}

	private static void createAjoutHandler(Button button) {
		// TODO Auto-generated method stub

		class AjoutHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				addCommentaire();
			}

		}
		button.addClickHandler(new AjoutHandler());

	}

	public static void addCommentaire() {
		boolean error = false;
		StringBuilder msgErr = new StringBuilder();
		if (!FieldVerifier.isValidComm(commentaireInput.getText())) {
			msgErr.append("Format incorrect pour le commentaire (min 2 carac - max 250)<BR/>");
			error = true;
		}

		if (error) {
			errorLabel.setText(msgErr.toString());
			return;
		}

		// Appel du service RPC ajout catégorie
		fileUploadServiceImpl.addCommentaire(commentaireInput.getText(),
				imageName, new WTModalAsyncCallback<String[]>() {

					@Override
					public void handleResult(String[] result) {
						errorLabel.setText(result[0]);

						// S'il y a eu un commentaire d'ajouté
						if (result.length > 1) {
							// Mise à jour dynamique du commentaire

							String html = CategoriesPane.getCommentaires()
									.get(imageName).getHTML();
							html += result[1];
							CategoriesPane.getCommentaires().get(imageName)
									.setHTML(html);
						}

					}
				});

	}

	public static WTAjoutCommentaireDialogBox getInstance() {
		if (instance == null) {
			instance = new WTAjoutCommentaireDialogBox();
			setup();
		}

		return instance;
	}

	public void show(String image) {
		imageName = image;
		dialogBox.get().center();
		dialogBox.getCloseButton().setFocus(true);
		dialogBox.get().setVisible(true);
	}

}
