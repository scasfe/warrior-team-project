package fr.warriorteam.client.pane;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;

import fr.warriorteam.client.WTVerticalPane;

public class WTModalWaitPane extends PopupPanel {

	private static WTModalWaitPane instance;

	private static WTVerticalPane dialogVPanel = new WTVerticalPane() {

		@Override
		public void reloadData() {
			// TODO Auto-generated method stub

		}
	};

	private int count = 0;

	// private WTModalWaitPane() {
	// setup();
	// }

	public static WTModalWaitPane getInstance() {
		if (instance == null) {
			instance = new WTModalWaitPane();
			setup();
		}
		return instance;
	}

	// public void show() {
	// instance.show();
	// }
	//
	// public void hide() {
	//
	// // super.hide();
	// instance.setVisible(false);
	// // instance.hide();
	//
	// }

	public void forceHide() {
		count = 0;
		super.hide();
	}

	private static void setup() {

		// setModal(true);
		// setShim(false);
		// setShadow(false);
		// instance.setPixelSize(1500, 1500); // !Important pour IE6: ne pas
		// mettre
		// une taille
		// trop grande sinon on se retrouve avec un ecran noir en arriere plan
		// sans la transparence lors de l'affichage de l'image de traitement en
		// cours
		// instance.setClosable(false);
		// instance.setResizable(false);
		// setOnEsc(false);
		// setDraggable(false);
		// setFrame(false);
		// setBorders(false);
		// setBodyBorder(false);
		// instance.setStyleName("wt-wait");
		// dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		dialogVPanel.add(new HTML("Veuillez patienter !"));
		dialogVPanel.setStyleName("modal-dialogBox");

		instance.setModal(true);

		// dialogVPanel.setStyleName("demo-DialogBox");
		instance.setStyleName("wt-wait");
		instance.setWidget(dialogVPanel);

		instance.center();

		// setHeaderVisible(false);
		// setMaximizable(false);
	}
}
