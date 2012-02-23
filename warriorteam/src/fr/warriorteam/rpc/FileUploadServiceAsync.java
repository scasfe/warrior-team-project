package fr.warriorteam.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileUploadServiceAsync {

	void uploadFile(String path, AsyncCallback<HashMap<String, String>> callback);

	void createZip(String fileName, AsyncCallback<String> callback);

	void addCommentaire(String commentaire, String imageName,
			AsyncCallback<String> callback);

}
