package fr.warriorteam.client.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.WTAjoutCommentaireDialogBox;
import fr.warriorteam.client.WTDialogBox;
import fr.warriorteam.client.WTVerticalPane;
import fr.warriorteam.client.menu.MenuGauchePane;
import fr.warriorteam.client.pane.CenterPane;
import fr.warriorteam.client.pane.FileDownloaderPane;
import fr.warriorteam.client.pane.FileUploaderPane;
import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.dto.ImageDTO;
import fr.warriorteam.rpc.CategorieService;
import fr.warriorteam.rpc.CategorieServiceAsync;
import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.rpc.FileUploadServiceAsync;
import fr.warriorteam.rpc.WTModalAsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class CategoriesPane extends WTVerticalPane {

	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static FileUploadServiceAsync imagesService = GWT
			.create(FileUploadService.class);

	private final static CategorieServiceAsync categorieService = GWT
			.create(CategorieService.class);

	private static CategoriesPane instance;
	private static HashMap<String, HTML> commentaires;
	private static HashMap<String, ImageDTO> imagesList;
	private static List<Image> images;
	private static List<Label> pages;
	private static VerticalPanel imagesPanel;
	private static CategorieDTO categorie;
	private static Label supprimer;
	private static Label download;
	private static Label errorLabel;

	/**
	 * Gestion de spages d'affichage d'images
	 */
	private static int current_page;

	/**
	 * Max width pour les images
	 */
	private static final int MAX_WIDTH = 600;
	private static final int MAX_HEIGHT = 400;

	/**
	 * Permet l'upload de fichiers
	 */
	private static FileUploaderPane upload;

	private CategoriesPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static CategoriesPane getInstance() {
		if (instance == null) {
			instance = new CategoriesPane();
			setup();

		}
		return instance;
	}

	private static void loadDynamicData() {
		// Check de session avant tout

		// WTModalWaitPane.getInstance().setText("Veuillez patienter");

		// WTModalWaitPane.getInstance().setVisible(true);
		// WTModalWaitPane.getInstance().center();

		imagesService.uploadFile(categorie.getDossier(),
				new WTModalAsyncCallback<List<ImageDTO>>() {

					// @Override
					// public void onFailure(Throwable caught) {
					// // TODO Auto-generated method stub
					// imagesPanel.clear();
					//
					// imagesPanel.add(new HTML("FAIL APPEL RPC "
					// + caught.getLocalizedMessage()
					// + caught.getMessage()));
					// }
					//
					// @Override
					// public void onSuccess(HashMap<String, String> result) {
					// // TODO Auto-generated method stub
					//
					// // WTModalWaitPane.getInstance().hide();
					//
					// // Récupération des commentaires
					// commentaires.clear();
					// imagesString = new ArrayList<String>(result.keySet());
					// commentaires = result;
					//
					// // Affichage de la page courante
					// afficherPageCourante();
					//
					// }

					@Override
					public void handleResult(List<ImageDTO> result) {
						// TODO Auto-generated method stub
						// Récupération des commentaires
						commentaires.clear();
						imagesList = new HashMap<String, ImageDTO>();

						// Conversion en HTML
						commentaires = new HashMap<String, HTML>();
						for (ImageDTO imageDTO : result) {

							String image = imageDTO.getNomImage();

							int index = image.indexOf(categorie.getDossier());
							final String imageName = image.substring(index + 1
									+ categorie.getDossier().length());
							commentaires.put(imageName,
									new HTML(imageDTO.getCommentaires()));

							imagesList.put(imageName, imageDTO);
						}

						// Affichage de la page courante
						afficherPageCourante();
					}

				});

		// WTModalWaitPane.getInstance().hide();
	}

	public static int getCurrent_page() {
		return current_page;
	}

	public static void setCurrent_page(int current_page) {
		CategoriesPane.current_page = current_page;
	}

	private static void setup() {

		// page courante initialisée à 1
		current_page = 1;

		// initialisation des listes
		// on vire l'ancien contenu
		if (commentaires == null) {
			// instance.remove(htmls);
			commentaires = new HashMap<String, HTML>();
		}

		// on vire l'ancien contenu
		if (pages == null) {
			// instance.remove(htmls);
			pages = new ArrayList<Label>();
		}

		if (images == null) {
			images = new ArrayList<Image>();
		}

		if (imagesList == null) {
			imagesList = new HashMap<String, ImageDTO>();
		}

		imagesPanel = new WTVerticalPane() {

			@Override
			public void reloadData() {
				// TODO Auto-generated method stub
				loadDynamicData();
			}
		};

		// Le conteneur d' images
		// Supprimer la catégorie
		supprimer = new Label("Supprimer la categorie");
		ajouterSupprimerHandler();

		// Download des images
		download = new Label("Telecharger les images");
		ajouterDownloadHandler();

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");

		instance.setSpacing(10);
		// instance.add(upload);
		instance.add(imagesPanel);

	}

	private static void ajouterSupprimerHandler() {
		class SupprimerCategorieHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				if (Window.confirm("Etes-vous sur de supprimer la categorie "
						+ categorie.getNomCategorie() + " ?")) {
					deleteCategorie();
				}
			}

		}
		supprimer.addClickHandler(new SupprimerCategorieHandler());

	}

	private static void ajouterDownloadHandler() {
		class DownloadHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {

				FileDownloaderPane.download(categorie.getDossier());

			}

		}
		download.addClickHandler(new DownloadHandler());

	}

	public static void deleteCategorie() {
		// Appel du service RPC ajout catégorie
		categorieService.deleteCategorie(categorie,
				new WTModalAsyncCallback<String>() {

					@Override
					public void handleResult(String result) {
						if (result != null) {
							errorLabel.setText(result);
						} else {
							// Reload du MenuGauche car la catégorie n'existe
							// plus
							MenuGauchePane.getInstance();

							// Redirection vers l'accueil car la catégorie
							// n'existe plus
							CenterPane.getInstance().changeActiveCenterWidget(
									NewsPane.getInstance());
						}
					}
				});

	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

	private static void afficherPageCourante() {
		// Avant tout, effacement du panel images
		imagesPanel.clear();

		// Titre du panel image
		Label title = new Label(categorie.getNomCategorie());
		title.setStyleName("titleCategorie");
		imagesPanel.add(title);

		// Informations sur le créateur
		Label createur = new Label("Categorie creee par : "
				+ categorie.getCreateur());
		imagesPanel.add(createur);

		// HorizontalPanel pour composants de la categorie
		HorizontalPanel options = new HorizontalPanel();
		options.setSpacing(8);

		// Mise à jour de l'upload
		upload = new FileUploaderPane(categorie.getDossier());
		options.add(upload);
		options.add(supprimer);
		options.add(download);
		// Important d'ajouter le formulaire de download (il est invisible de
		// toute façon)
		options.add(FileDownloaderPane.getInstance());

		// Réinitialisation du errorLabel
		errorLabel = new Label();
		errorLabel.addStyleName("serverResponseLabelError");
		options.add(errorLabel);

		imagesPanel.add(options);

		// gestion de l'affichage
		List<ImageDTO> list = new ArrayList<ImageDTO>(imagesList.values());

		int indexDebut = 0;
		int indexFin = 0;
		if (current_page != 1) {
			indexDebut = (current_page - 1) * 20;
			if ((current_page - 1) * 20 + 20 >= imagesList.size()) {
				indexFin = imagesList.size();
			} else {
				indexFin = (current_page - 1) * 20 + 20;
			}
		} else {
			if (20 >= imagesList.size()) {
				indexFin = imagesList.size();
			} else {
				indexFin = 20;
			}
		}

		images.clear();

		class ImageHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// methode de traitement
				Image image = (Image) event.getSource();

				// obligation de copier l'élément
				// en String pour afficher l'image grande

				String urlImage = image.getUrl();
				urlImage = urlImage.replaceFirst("/resize", "");

				int index = urlImage.indexOf(categorie.getDossier());
				final String imageName = urlImage.substring(index + 1
						+ categorie.getDossier().length());

				HTML html2 = commentaires.get(imageName);

				String htmlImage = "<img src=\" " + urlImage + "\" width=\""
						+ MAX_WIDTH + "\" height=\"" + MAX_HEIGHT + "\" />";

				HTML newHtml = new HTML(htmlImage);

				// Process panel
				HorizontalPanel processPanel = new HorizontalPanel();
				Button addCommButton = new Button("Ajouter commentaire");
				addCommButton.setPixelSize(150, 30);
				addCommButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						WTAjoutCommentaireDialogBox.getInstance().show(
								imageName);

					}
				});
				processPanel.add(addCommButton);
				Button suppImageButton = new Button("Supprimer image");
				suppImageButton.setPixelSize(150, 30);
				suppImageButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (Window
								.confirm("Etes-vous sur de supprimer l'image "
										+ imageName + " ?")) {
							deleteImage(imagesList.get(imageName));
						}

					}

				});
				processPanel.add(suppImageButton);

				WTDialogBox dialogBox = new WTDialogBox(newHtml, processPanel,
						html2);

				dialogBox.get().setText(
						"Image postee par "
								+ imagesList.get(imageName).getPosteur());

				dialogBox.get().setAnimationEnabled(false);
				dialogBox.get().center();
				dialogBox.getCloseButton().setFocus(true);

			}

		}

		// Création des handlers
		class PageHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// methode de traitement
				Label source = (Label) event.getSource();

				int pageDemandee = Integer.valueOf(source.getText());
				current_page = pageDemandee;
				afficherPageCourante();

			}

		}

		// découpage en pages
		int nbPages = imagesList.size() / 20;
		int reste = imagesList.size() % 20;
		if (reste != 0 && nbPages >= 1) {
			nbPages++;
		}

		if (nbPages > 1) {
			HorizontalPanel pagesPanel = new HorizontalPanel();
			pagesPanel.setSpacing(10);
			for (int i = 0; i < nbPages; i++) {

				Label label = new Label(String.valueOf(i + 1));
				label.addClickHandler(new PageHandler());
				label.setVisible(true);

				// on met la page en cours en gras
				if ((i + 1) == current_page) {
					label.setStyleName("page_gras");
				}
				pagesPanel.add(label);

			}
			imagesPanel.add(pagesPanel);
		}

		HorizontalPanel ligneImages = new HorizontalPanel();
		for (int i = indexDebut; i < indexFin; i++) {

			if (i % 3 == 0) {
				ligneImages = new HorizontalPanel();

				imagesPanel.add(ligneImages);

			}

			Image image = new Image(list.get(i).getNomImage());
			images.add(image);
			image.addClickHandler(new ImageHandler());
			image.setStyleName("div_image");
			ligneImages.add(image);

		}

	}

	private static void deleteImage(ImageDTO imageDto) {
		// Appel du service RPC ajout catégorie
		imagesService.deleteImage(imageDto, new WTModalAsyncCallback<String>() {

			@Override
			public void handleResult(String result) {

				// Redirection vers l'accueil car la catégorie
				// n'existe plus
				CategoriesPane.getInstance().reloadData();

			}
		});

	}

	public static CategorieDTO getCategorie() {
		return categorie;
	}

	public static void setCategorie(CategorieDTO categorie) {
		CategoriesPane.categorie = categorie;
	}

	public static HashMap<String, HTML> getCommentaires() {
		return commentaires;
	}

}
