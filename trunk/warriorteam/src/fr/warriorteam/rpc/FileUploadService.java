package fr.warriorteam.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("upload")
public interface FileUploadService extends RemoteService {
	HashMap<String, String> uploadFile(String path)
			throws IllegalArgumentException;

	String createZip(String fileName);

	String[] addCommentaire(String commentaire, String imageName)
			throws IllegalArgumentException;

}
