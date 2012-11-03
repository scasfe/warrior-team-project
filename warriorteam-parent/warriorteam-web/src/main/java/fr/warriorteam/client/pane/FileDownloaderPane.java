package fr.warriorteam.client.pane;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel;

import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.rpc.FileUploadServiceAsync;
import fr.warriorteam.rpc.WTModalAsyncCallback;

public class FileDownloaderPane extends FormPanel {

	// FormPanel form = new FormPanel();
	// private FileUpload fu = new FileUpload();
	/**
	 * Create a remote service proxy to talk to the server-side Login service.
	 */
	private final static FileUploadServiceAsync imagesService = GWT
			.create(FileUploadService.class);
	private static FileDownloaderPane instance;
	private static String path;

	public static FileDownloaderPane getInstance() {
		if (instance == null) {
			instance = new FileDownloaderPane();
			setup();
		}

		return instance;
	}

	private FileDownloaderPane() {

	}

	private static void setup() {
		instance.setEncoding(FormPanel.ENCODING_MULTIPART);
		instance.setMethod(FormPanel.METHOD_POST);

		instance.setVisible(false);

		instance.addSubmitHandler(new FormPanel.SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
				if (!"".equalsIgnoreCase(path)) {
					GWT.log("DOWNLOADING FILE????", null);
					// NOW WHAT????
				} else {
					event.cancel(); // cancel the event
				}

			}
		});

		instance.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {

				WTModalWaitPane.getInstance().hide();
				if (!event.getResults().equals("")) {
					Window.alert(event.getResults());
				} else {
					Window.alert("Fichier " + path + " downloadé !");
				}
			}
		});

	}

	public static void download(String file) {
		GWT.log("You selected: " + path, null);
		imagesService.createZip(file, new WTModalAsyncCallback<String>() {

			@Override
			public void handleResult(String result) {
				path = result;
				instance.setAction("filedownload.do?path=" + path);
				instance.submit();

			}
		});

	}
}
