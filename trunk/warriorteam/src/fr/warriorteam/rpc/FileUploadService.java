package fr.warriorteam.rpc;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.warriorteam.rpc.dto.NewsDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("upload")
public interface FileUploadService extends RemoteService {
	HashMap<String, String> uploadFile() throws IllegalArgumentException;
	
	 boolean createZip(String fileName);

}
