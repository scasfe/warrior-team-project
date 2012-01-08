package fr.warriorteam.client.news;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
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
	
	private static CategoriesPane instance;
	private static HTML html;
	
	/**
	 * Max width pour les images
	 */
	private static final int MAX_WIDTH = 320;
	private static final int MAX_HEIGHT = 240;
	
	
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
		// TODO Auto-generated method stub

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
				// on vire l'ancien contenu
				if(html != null){
					instance.remove(html);
				}
				StringBuilder string = new StringBuilder();
				
				// TODO petit algo pour affichage en miniature
				for(String image : result){
					
					Image imageEnCours = new Image("images/"+image);
					
					// taille reelle de l'image
					int width = imageEnCours.getWidth();
					int height = imageEnCours.getHeight();
					
					// si portait
					if(height > width){
						if(height > MAX_HEIGHT){
							double coeff = (double)MAX_HEIGHT/(double)height;
							height = MAX_HEIGHT;
							width *= coeff; 
						}
					}else{
						// paysage
						if(width > MAX_WIDTH){
							double coeff = (double)MAX_WIDTH/(double)width;
							width = MAX_WIDTH;
							height *= coeff;
						}
					}
									
					string.append("<br/><img src=\"images/"+image+"\"  width=\""+width+"\" height=\""+height+"\"/><br/>");
					
				}
				html = new HTML(string.toString());
				instance.add(html);

			}
		});
	}

	private static void setup() {

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");
		

		
		
		instance.add(upload);



	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

}
