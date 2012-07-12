package fr.warriorteam.client.news;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.WTVerticalPane;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.NewsServiceAsync;
import fr.warriorteam.rpc.dto.NewsDTO;

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
	private static VerticalPanel contenu;

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
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

		newsService.searchLastNews(new AsyncCallback<List<NewsDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				contenu.clear();
				contenu.add(new HTML("FAIL APPEL RPC "
						+ caught.getLocalizedMessage() + caught.getMessage()));
			}

			@Override
			public void onSuccess(List<NewsDTO> result) {
				// TODO Auto-generated method stub
				contenu.clear();
				for (NewsDTO news : result) {
					// Le contenu de la news en détail
					VerticalPanel newsPanel = new VerticalPanel();
					newsPanel.add(new HTML(
							"Poste le <span class=\"jaune_gras\">"
									+ news.getDate() + "</span>"));
					newsPanel.add(new HTML("<h3><span class=\"blanc_gras\">"
							+ news.getTitre() + "</span></h3>"));
					newsPanel.add(new HTML(news.getTexte()));
					newsPanel.setStyleName("element_corps");
					contenu.add(newsPanel);
				}

			}
		});

	}

	private static void setup() {

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");

		// les fonctions HTML
		contenu = new VerticalPanel();
		instance.add(contenu);
		
		// premier chargement du contenu
		loadDynamicData();

	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

}
