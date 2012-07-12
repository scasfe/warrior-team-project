package fr.warriorteam.client.pane;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.warriorteam.client.news.CategoriesPane;

public class FileUploaderPane extends FormPanel {

	// FormPanel form = new FormPanel();
	private FileUpload fu = new FileUpload();

	public FileUploaderPane(String path) {
		this.setEncoding(FormPanel.ENCODING_MULTIPART);
		this.setMethod(FormPanel.METHOD_POST);
		this.setAction("fileupload.do?path=" + path);

		VerticalPanel holder = new VerticalPanel();

		fu.setName("upload");
		holder.add(fu);
		holder.add(new Button("Submit", new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (fu.getFilename().length() > 0) {
					// Modal wait
					WTModalWaitPane.getInstance().setVisible(true);
					WTModalWaitPane.getInstance().center();
					GWT.log("You selected: " + fu.getFilename(), null);

					submit();
				}

			}
		}));

		addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				if (!"".equalsIgnoreCase(fu.getFilename())) {
					GWT.log("UPLOADING FILE????", null);
					// NOW WHAT????
				} else {
					event.cancel(); // cancel the event
				}

			}
		});

		addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {

				WTModalWaitPane.getInstance().hide();
				CategoriesPane.getInstance().reloadData();
				if (!event.getResults().equals("")) {
					Window.alert(event.getResults());
				} else {
					Window.alert("Image " + fu.getFilename()
							+ " ajoutee avec SUCCES !");
				}
			}
		});

		add(holder);

	}
}
