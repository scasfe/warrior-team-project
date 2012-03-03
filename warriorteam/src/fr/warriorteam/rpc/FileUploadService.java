package fr.warriorteam.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.dto.ImageDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("upload")
public interface FileUploadService extends RemoteService {
	List<ImageDTO> uploadFile(String path) throws IllegalArgumentException;

	String createZip(String fileName);

	String[] addCommentaire(String commentaire, String imageName)
			throws IllegalArgumentException;

}
