package fr.warriorteam.rpc.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.rpc.FileUploadService;
import fr.warriorteam.server.utils.DAOFactory;
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
			throw new IllegalArgumentException("Vous n'�tes pas connect� !");
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
				// TODO - cr�er service va lire en base commentaire pour les
				// photos
				// String commentaire =
				// imageService.searchCommentaires(image.getName());

				String commentaires = searchCommentairesFromDB(image.getName());
				// TODO � virer pour tester return commentaire unique
				imagesList.put(image.getName(), commentaires);

			}

		}

		// }

		return imagesList;
	}

	private String searchCommentairesFromDB(String name) {

		HttpSession session = getThreadLocalRequest().getSession();

		// Si l'utilisa�teur est connect�, il peut voir les news r�serv�es aux
		// connect�s
		boolean sessionValide = LoginServiceImpl.checkSession(session);

		if (!sessionValide) {
			return "Vous devez �tre connect� pour voir les commentaires !";
		}

		String commentaires = null;

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// Cr�ation de la requ�te
			StringBuilder query = new StringBuilder();
			query.append("SELECT commentaires FROM image WHERE nom_image = '"
					+ name + "'");

			// Cr�ation d'un objet Statement
			Statement state = connection.createStatement();
			// L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery(query.toString());
			ResultSetMetaData resultMeta = result.getMetaData();

			Integer nbNews = 0;
			while (result.next()) {

				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					commentaires = result.getObject(i).toString();
				}
			}

			result.close();
			state.close();

			logger.debug(name + " : commentaires trouv�s");

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Probl�me interne du serveur");
		}

		return commentaires;
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

	@Override
	public String addCommentaire(String commentaire, String imageName)
			throws IllegalArgumentException {

		HttpSession session = getThreadLocalRequest().getSession();
		String pseudo = (String) session.getAttribute("pseudo");

		StringBuilder commentaireToAdd = new StringBuilder();
		commentaireToAdd.append(searchCommentairesFromDB(imageName));
		commentaireToAdd.append("<b>" + pseudo + " le " + new Date() + ": </b>"
				+ commentaire + "<br/>");

		String msg = "";

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// TODO - Mettre la bonne date dans la requ�te

			// Cr�ation de la requ�te
			StringBuilder query = new StringBuilder();
			query.append("UPDATE image set commentaires = ' "
					+ commentaireToAdd.toString() + "' WHERE nom_image = '"
					+ imageName + "'");

			// Cr�ation d'un objet Statement
			java.sql.PreparedStatement state = connection
					.prepareStatement(query.toString());
			// L'objet ResultSet contient le r�sultat de la requ�te SQL
			int result = state.executeUpdate();
			// ResultSetMetaData resultMeta = result.getMetaData();

			// result.close();
			state.close();

			msg = "commentaire ajout� !";
			logger.debug("Commentaire ajout�e en base : " + imageName + " par "
					+ pseudo);

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Probl�me interne du serveur");
		}

		return msg;
	}

}
