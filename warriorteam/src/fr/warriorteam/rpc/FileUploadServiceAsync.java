package fr.warriorteam.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.rpc.dto.NewsDTO;

public interface FileUploadServiceAsync {

	void uploadFile(AsyncCallback<List<String>> callback);
	
	 void createZip(String fileName,AsyncCallback<Boolean> callback);

}
