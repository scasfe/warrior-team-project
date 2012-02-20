package fr.warriorteam.client.menu;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.news.CategoriesPane;
import fr.warriorteam.client.news.NewsPane;
import fr.warriorteam.client.pane.CenterPane;
import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.NewsServiceAsync;
import fr.warriorteam.rpc.dto.CategoriesDTO;

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

	private static Label titleMenu;

	/**
	 * Les DTO de catégories
	 */
	private static List<CategorieDTO> categories;

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

		// Avant tout enlever toutes les autres catégories déjà présentes
		if (categories != null) {
			instance.clear();
			instance.add(titleMenu);
		}

		// Check de session avant tout

		// Chargement des catégories
		newsService.searchCategories(new AsyncCallback<CategoriesDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				HTML html = new HTML();
				html.setHTML("FAIL APPEL RPC " + caught.getLocalizedMessage()
						+ caught.getMessage());
				instance.add(html);
			}

			@Override
			public void onSuccess(CategoriesDTO result) {
				// TODO Auto-generated method stub
				// HTML html = new HTML();
				// html.setHTML(result.getCategories().toString());
				// instance.add(html);
				categories = result.getCategories();
				afficherCategories();

			}
		});

	}

	protected static void afficherCategories() {
		// TODO Auto-generated method stub
		for (final CategorieDTO categorie : categories) {
			Label titleMenu = new HTML("<a href=# >"
					+ categorie.getNomCategorie() + "</a>");
			titleMenu.setStyleName("element_menu");

			// Create a handler for the sendButton and nameField
			class CategoriesHandler implements ClickHandler {
				/**
				 * Fired when the user clicks on the sendButton.
				 */
				public void onClick(ClickEvent event) {
					// methode de traitement
					CenterPane.getInstance()
							.changeActiveCenterWidgetForCategorie(
									CategoriesPane.getInstance(), categorie);

				}

			}

			CategoriesHandler categoriesHandler = new CategoriesHandler();
			titleMenu.addClickHandler(categoriesHandler);

			instance.add(titleMenu);
		}
	}

	private static void setup() {

		// Vertical Panel de gauche (menu catégories de photos)

		// menu.setPixelSize(250,400);
		// instance.setBorderWidth(1);

		// le titre du menu
		titleMenu = new HTML("<a href=# >Accueil</a>");
		titleMenu.setStyleName("element_menu");

		// les catégories
		// Appel RPC pour les catégories

		// Methode de chargement des catégories
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

		// Add a handler to send the name to the server
		AccueilHandler accueilHandler = new AccueilHandler();
		titleMenu.addClickHandler(accueilHandler);

		instance.add(titleMenu);

		// CategoriesHandler categoriesHandler = new CategoriesHandler();
		// titleMenu2.addClickHandler(categoriesHandler);

	}

}
