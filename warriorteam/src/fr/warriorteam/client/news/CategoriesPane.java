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

	}

	private static void setup() {

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");
		

		
		
		instance.add(upload);

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
				for(String image : result){
				
				instance.add(new HTML("<br/><img src=\"images/"+image+"\" /><br/>"));
				instance.add(new HTML("<img src=\"../images/alienware.png\"/>"));
				
				}

			}
		});

	
		// Une photo
		instance.add(new HTML(""));

	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

}
