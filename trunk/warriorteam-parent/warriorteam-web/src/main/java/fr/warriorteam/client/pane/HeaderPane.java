package fr.warriorteam.client.pane;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.rpc.RpcNewsServiceAsync;
import fr.warriorteam.dto.NewsResultDTO;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entr�e
 * affichant un root panel permettant de s'identifier
 */
public class HeaderPane extends HorizontalPanel {

	/** 
	 *
	 */
	private static HeaderPane instance;

	private HeaderPane() {

	}

	/**
	 * M�thode permettant de cr�er le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static HeaderPane getInstance() {
		if (instance == null) {
			instance = new HeaderPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		

	}

	private static void setup() {

		instance.setSpacing(10);
		instance.setVerticalAlignment(ALIGN_TOP);
		// TODO - requ�ter les derni�res photos ajout�es
		// panelHaut.add(WTLastPhotosPublished.getInstance());

		// Temporaire : � virer une fois le widget LastPhoto cod�
		VerticalPanel lastPhotos = new VerticalPanel();
		lastPhotos.setSize("300", "150");
		lastPhotos
				.add(new HTML(
						"<h2>Dernieres photos :</h2>"
								+ "Postee par Bouly le 04/09/2011 a 00h18"
								+ "<br><br> <img src=\"images/exemple.png\""
								+ " width=\"60\" height=\"40\" /> -- <img src=\"images/exemple2.png\""
								+ " width=\"60\" height=\"40\" /> "));

		lastPhotos.setVerticalAlignment(ALIGN_TOP);

		VerticalPanel title = new VerticalPanel();
		title.setVerticalAlignment(ALIGN_TOP);
		title.add(new HTML("<h1>WarriorTeam Photo Starter Project</h1>"));

		instance.add(lastPhotos);
		instance.add(title);
//		instance.add(WTLoginPane.getInstance());
		// panelHaut.setStyleName("element_menu");
		instance.getElement().setId("en_tete");




// Create a handler for the sendButton and nameField
class ButtonHandler implements ClickHandler {
	/**
	 * Fired when the user clicks on the sendButton.
	 */
	public void onClick(ClickEvent event) {
		// test du service RPC News
				RpcNewsServiceAsync.INSTANCE.processSuccess(new AsyncCallback<NewsResultDTO>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("FAILED !!!"+caught.getMessage());
					}

					public void onSuccess(NewsResultDTO result) {
						// TODO Auto-generated method stub
						Window.alert(result.getStock().toString());
					}

					
		        });
	}

}
		
		
		
		Button b = new Button();
		b.addClickHandler(new ButtonHandler());
		b.setEnabled(true);
		instance.add(b);
		
	}

}
