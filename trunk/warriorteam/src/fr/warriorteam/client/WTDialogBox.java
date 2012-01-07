package fr.warriorteam.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WTDialogBox {

	private final Button closeButton = new Button("Fermer");
	private final DialogBox dialogBox = new DialogBox();

	public DialogBox get() {
		return dialogBox;
	}

	public WTDialogBox(Object... params) {

		// Create the popup dialog box
		VerticalPanel dialogVPanel = new VerticalPanel();

		for (Object o : params) {
			if (o instanceof Label) {
				dialogVPanel.add((Label) o);
			}

			if (o instanceof HTML) {
				dialogVPanel.add((HTML) o);
			}
		}

		dialogBox.setText("");
		dialogBox.setAnimationEnabled(true);

		// We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");

		dialogVPanel.addStyleName("dialogVPanel");

		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		dialogBox.setWidth("280");
	}

	public Button getCloseButton() {
		return closeButton;
	}

}
