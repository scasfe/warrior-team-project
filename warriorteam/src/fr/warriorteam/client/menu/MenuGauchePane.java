package fr.warriorteam.client.menu;

import fr.warriorteam.client.news.CategoriesPane;
import fr.warriorteam.client.news.NewsPane;
import fr.warriorteam.client.pane.CenterPane;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.NewsServiceAsync;
import fr.warriorteam.rpc.dto.CategoriesDTO;
import fr.warriorteam.rpc.dto.NewsDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class MenuGauchePane extends VerticalPanel {
	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static NewsServiceAsync newsService = GWT
			.create(NewsService.class);
	private static MenuGauchePane instance;

	private MenuGauchePane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static MenuGauchePane getInstance() {
		if (instance == null) {
			instance = new MenuGauchePane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout
		
		// Chargement des catégories
		newsService.searchCategories(new AsyncCallback<CategoriesDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				HTML html = new HTML();
				html.setHTML("FAIL APPEL RPC "
						+ caught.getLocalizedMessage() + caught.getMessage());
				instance.add(html);
			}

			@Override
			public void onSuccess(CategoriesDTO result) {
				// TODO Auto-generated method stub
				HTML html = new HTML();
				html.setHTML(result.getCategories().toString());
				instance.add(html);

			}
		});


	}

	private static void setup() {

		// Vertical Panel de gauche (menu catégories de photos)

		// menu.setPixelSize(250,400);
		// instance.setBorderWidth(1);

		// le titre du menu
		Label titleMenu = new HTML("<a href=# >Accueil</a>");
		Label titleMenu2 = new HTML("<a href=# >Categories 1</a>");
		Label titleMenu3 = new HTML("<a href=# >Categories 2</a>");
		Label titleMenu4 = new HTML("<a href=# >Categories 3</a>");

		titleMenu.setStyleName("element_menu");
		titleMenu2.setStyleName("element_menu");
		titleMenu3.setStyleName("element_menu");
		titleMenu4.setStyleName("element_menu");

		// les catégories
		// Appel RPC pour les catégories

		// Methode de chargement des catégories

		instance.add(titleMenu);
		instance.add(titleMenu2);
		instance.add(titleMenu3);
		instance.add(titleMenu4);
		instance.getElement().setId("menuG");

		// Création des handlers
		class AccueilHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// methode de traitement
				CenterPane.getInstance().changeActiveCenterWidget(
						NewsPane.getInstance());

			}

		}

		// Create a handler for the sendButton and nameField
		class CategoriesHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// methode de traitement
				CenterPane.getInstance().changeActiveCenterWidget(
						CategoriesPane.getInstance());

			}

		}

		// Add a handler to send the name to the server
		AccueilHandler accueilHandler = new AccueilHandler();
		titleMenu.addClickHandler(accueilHandler);

		CategoriesHandler categoriesHandler = new CategoriesHandler();
		titleMenu2.addClickHandler(categoriesHandler);

	}

}
