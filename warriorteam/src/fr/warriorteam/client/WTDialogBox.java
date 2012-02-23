package fr.warriorteam.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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

			if (o instanceof TextBox) {
				dialogVPanel.add((TextBox) o);
			}

			if (o instanceof Button) {
				dialogVPanel.add((Button) o);
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
		// dialogBox.setWidth("280");
		dialogBox.setVisible(true);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
	}

	public Button getCloseButton() {
		return closeButton;
	}

}