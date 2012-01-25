package fr.warriorteam.client.news;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.warriorteam.client.WTDialogBox;
import fr.warriorteam.client.WTVerticalPane;
import fr.warriorteam.client.menu.MenuDroitePane;
import fr.warriorteam.client.menu.MenuGauchePane;
import fr.warriorteam.client.pane.CenterPane;
import fr.warriorteam.client.pane.FileUploader;
import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.rpc.FileUploadServiceAsync;
import fr.warriorteam.rpc.LoginService;
import fr.warriorteam.rpc.LoginServiceAsync;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.NewsServiceAsync;
import fr.warriorteam.rpc.dto.LoginDTO;
import fr.warriorteam.rpc.dto.NewsDTO;
import fr.warriorteam.server.utils.CryptageDonneesUtils;
import fr.warriorteam.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DialogBox.Caption;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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

	private static  CategoriesPane instance;
	private static  List<HTML> commentaires;
	private static  List<String> imagesString;
	private static  List<Image> images;
	private static  List<Label> pages;
	private static  VerticalPanel imagesPanel;

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
	private static Widget upload = FileUploader.getFileUploaderWidget();

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
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// Check de session avant tout
		imagesService.uploadFile(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				instance.add(new HTML("FAIL APPEL RPC "
						+ caught.getLocalizedMessage() + caught.getMessage()));
			}

			@Override
			public void onSuccess(List<String> result) {
				// TODO Auto-generated method stub


				for (HTML html : commentaires) {
					if (instance.getWidgetIndex(html) != -1) {
						instance.remove(html);
					}

				}
				commentaires.clear();
				imagesString = result;

				// Affichage de la page courante
				afficherPageCourante();

			}

			
		});
	}

	private static void setup() {

		// page courante initialisée à 1
		current_page = 1;
		
		// initialisation des listes
		// on vire l'ancien contenu
		if (commentaires == null) {
			// instance.remove(htmls);
			commentaires = new ArrayList<HTML>();
		}
		
		// on vire l'ancien contenu
		if (pages == null) {
			// instance.remove(htmls);
			pages = new ArrayList<Label>();
		}
		
		if( images == null){
			images = new ArrayList<Image>();
		}
		
		if(imagesString == null){
			imagesString = new ArrayList<String>();
		}
		
		imagesPanel = new WTVerticalPane() {
			
			@Override
			public void reloadData() {
				// TODO Auto-generated method stub
				loadDynamicData();
			}
		};

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");

		instance.setSpacing(10);
		instance.add(upload);
		instance.add(imagesPanel);

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
		
		int indexDebut = 0;
		int indexFin = 0;
		if(current_page!=1){
			indexDebut = (current_page-1)*20;
			if((current_page-1)*20+20>=imagesString.size()){
				indexFin = imagesString.size();
			}else{
				indexFin = (current_page-1)*20+20;
			}
		}else{
			if(20>=imagesString.size()){
				indexFin = imagesString.size();
			}else{
				indexFin =20;
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
				
				HTML html2 = new HTML( "dsdssssssssssssssssssssssssssssssssssssssssssssssssssssssss<BR/>sssssssssssssssssss" +
						"dssssssssssssssssssssssssssssssssss<BR/>ssssssssssssssssssssssssssssssssssssssss<BR/>ssssssssssssssssssssssss" +
						"dssssssssssssssssssssssssssssssssssssssssssssssssss<BR/>sssssssssssssssssssss<BR/>ssssssssssssssssssssssss");
				
				HTML newHtml = new HTML("<img src=\""+urlImage+"\" width=\""+MAX_WIDTH+"\" height=\""+MAX_HEIGHT+"\" ");
				WTDialogBox dialogBox = new WTDialogBox(newHtml, html2);
				
				
				dialogBox.get().setText("Image n° XXX");
				
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
		int nbPages = imagesString.size() / 20;
		int reste = imagesString.size() % 20;
		if (reste != 0 && nbPages >= 1) {
			nbPages++;
		}

		if (nbPages > 1) {
			HorizontalPanel pagesPanel = new HorizontalPanel();
			pagesPanel.setSpacing(10);
			for (int i = 0; i < nbPages; i++) {

				Label label = new Label(String.valueOf(i+1));
				label.addClickHandler(new PageHandler());
				label.setVisible(true);
				
				// on met la page en cours en gras
				if((i+1) == current_page){
					label.setStyleName("page_gras");
				}
				pagesPanel.add(label);
				

			}
			imagesPanel.add(pagesPanel);
		}
		
		HorizontalPanel ligneImages = new HorizontalPanel();
		for (int i=indexDebut; i< indexFin; i++) {
			
			if(i%3 == 0){
				ligneImages = new HorizontalPanel();
				
				imagesPanel.add(ligneImages);
				
			}
			
	Image image =new Image("images/resize/" + imagesString.get(i));
	images.add(image);
			image.addClickHandler(new ImageHandler());
			image.setStyleName("div_image");
			ligneImages.add(image);
			

		}
		
	}		
		
}
