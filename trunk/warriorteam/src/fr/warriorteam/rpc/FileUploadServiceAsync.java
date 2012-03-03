package fr.warriorteam.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.warriorteam.dto.ImageDTO;

public interface FileUploadServiceAsync {

	void uploadFile(String path, AsyncCallback<List<ImageDTO>> callback);

	void createZip(String fileName, AsyncCallback<String> callback);

	void addCommentaire(String commentaire, String imageName,
			AsyncCallback<String[]> callback);

	void deleteImage(ImageDTO imageDto, AsyncCallback<String> callback);

}
