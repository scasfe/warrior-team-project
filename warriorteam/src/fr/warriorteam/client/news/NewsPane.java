package fr.warriorteam.client.news;

import fr.warriorteam.client.WTVerticalPane;

import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.NewsServiceAsync;
import fr.warriorteam.rpc.dto.NewsDTO;

import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class NewsPane extends WTVerticalPane {
	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static NewsServiceAsync newsService = GWT
			.create(NewsService.class);

	/**
	 * L'instance en singleton
	 */
	private static NewsPane instance;

	/**
	 * Le contenu HTML de ce pane centre
	 */
	private static HTML contenu;

	private NewsPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static NewsPane getInstance() {
		if (instance == null) {
			instance = new NewsPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

		newsService.searchLastNews(new AsyncCallback<NewsDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				contenu.setHTML("FAIL APPEL RPC "
						+ caught.getLocalizedMessage() + caught.getMessage());
			}

			@Override
			public void onSuccess(NewsDTO result) {
				// TODO Auto-generated method stub
				contenu.setHTML(result.getLogin().toString());

			}
		});

	}

	private static void setup() {

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");

		// les fonctions HTML
		contenu = new HTML();
		instance.add(contenu);

	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

}
