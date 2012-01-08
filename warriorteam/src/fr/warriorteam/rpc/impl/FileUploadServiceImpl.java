package fr.warriorteam.rpc.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.dto.NewsDTO;
import fr.warriorteam.server.servlet.UploadServlet;
import fr.warriorteam.server.utils.DAOFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class FileUploadServiceImpl extends RemoteServiceServlet implements
		FileUploadService {

	private final Logger logger = Logger.getLogger(FileUploadServiceImpl.class);
	
	/**
	 * 
	 */
	// private LoginService loginService = new LoginServiceImpl();

	public List<String> uploadFile() throws IllegalArgumentException {

		
		
		
		
		
		
//		HttpSession session = getThreadLocalRequest().getSession();
//
//	//	if (LoginServiceImpl.checkSession(session)) {
//		// Appel de la servlet d'upload

		
		// Dossier image
		File[] images = null;
		File file = new File("../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images");
		//if(file.isDirectory()){
			images = file.listFiles();
		//}
		List<String> imagesList = new ArrayList<String>();

		for(File image : images){
			imagesList.add(image.getName());
		}
			
		//}
	
			return imagesList;
	}

}
