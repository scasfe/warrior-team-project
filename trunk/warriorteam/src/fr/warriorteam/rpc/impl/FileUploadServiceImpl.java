package fr.warriorteam.rpc.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.server.utils.ZipFileWriter;

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

	public HashMap<String, String> uploadFile(String path)
			throws IllegalArgumentException {

		// TODO a enlever - pour tester le d

		HttpSession session = getThreadLocalRequest().getSession();

		if (!LoginServiceImpl.checkSession(session)) {
			throw new IllegalArgumentException("Vous n'êtes pas connecté !");
		}

		// HttpSession session = getThreadLocalRequest().getSession();
		//
		// // if (LoginServiceImpl.checkSession(session)) {
		// // Appel de la servlet d'upload

		// Dossier image
		File[] images = null;
		File file = new File(
				"../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"
						+ path + "/resize");
		// if(file.isDirectory()){
		images = file.listFiles();
		// }

		// Map key: url photo, value : commentaires
		HashMap<String, String> imagesList = new HashMap<String, String>();

		// s'il y a des images dansR le dossier
		if (images != null) {

			for (File image : images) {

				// allez voir en base le commentaire
				// TODO - créer service va lire en base commentaire pour les
				// photos
				// String commentaire =
				// imageService.searchCommentaires(image.getName());

				// TODO à virer pour tester return commentaire unique
				imagesList
						.put(image.getName(),
								"Ceci est un commentaire de test pour notre application");

			}

		}

		// }

		return imagesList;
	}

	public boolean createZip(String fileName) {
		boolean success = false;

		try {
			ZipFileWriter zip = new ZipFileWriter(fileName);
			// On recupere la liste des fichiers
			for (String st : uploadFile(fileName).keySet()) {
				if (true) { // TODO tester ici si le fichier existe deja ou pas

				}
			}

			zip.addFile("c://wouaf.txt");
			zip.addFile("c://deletemail.log");
			zip.addFile("c://fobec.gif");
			zip.close();
			success = true;
		} catch (IOException ex) {
			logger.fatal("Erreur IO lors de la creation du ZIP : " + ex);
		}

		return success;

	}

}
