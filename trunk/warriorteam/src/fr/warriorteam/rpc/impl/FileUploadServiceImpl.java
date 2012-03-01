package fr.warriorteam.rpc.impl;

import java.io.File;
import java.io.FileNotFoundException;
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
import fr.warriorteam.server.utils.PropertiesUtils;
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
		File file = new File(PropertiesUtils.getProperties("path_file_images")
				+ path + "/resize");
		// if(file.isDirectory()){
		images = file.listFiles();
		// }

		// Map key: url photo, value : commentaires
		HashMap<String, String> imagesList = new HashMap<String, String>();

		// s'il y a des images dansR le dossier
		if (images != null) {
			String path_images = PropertiesUtils
					.getProperties("path_file_images");
			for (File image : images) {

				// allez voir en base le commentaire
				// TODO - créer service va lire en base commentaire pour les
				// photos
				// String commentaire =
				// imageService.searchCommentaires(image.getName());

				String commentaires = searchCommentairesFromDB(image.getName());
				// TODO à virer pour tester return commentaire unique
				imagesList.put(
						path_images + path + "/resize/" + image.getName(),
						commentaires);

			}

		}

		// }

		return imagesList;
	}

	private String searchCommentairesFromDB(String name) {

		HttpSession session = getThreadLocalRequest().getSession();

		// Si l'utilisaéteur est connecté, il peut voir les news réservées aux
		// connectés
		boolean sessionValide = LoginServiceImpl.checkSession(session);

		if (!sessionValide) {
			return "Vous devez être connecté pour voir les commentaires !";
		}

		String commentaires = null;

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// Création de la requête
			StringBuilder query = new StringBuilder();
			query.append("SELECT commentaires FROM image WHERE nom_image = '"
					+ name + "'");

			// Création d'un objet Statement
			Statement state = connection.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
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

			logger.debug(name + " : commentaires trouvés");

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Problème interne du serveur");
		}

		return commentaires;
	}

	public String createZip(String pathName) {

		String zipName = pathName + "_zip.zip";

		try {
			ZipFileWriter zip = new ZipFileWriter(
					PropertiesUtils.getProperties("path_file_images") + zipName);

			File file = new File(
					PropertiesUtils.getProperties("path_file_images")
							+ pathName);
			for (File f : file.listFiles()) {
				if (!f.getName().endsWith("resize")) {
					zip.addFile(PropertiesUtils
							.getProperties("path_file_images")
							+ pathName
							+ "/"
							+ f.getName());
				}
			}

			zip.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return zipName;

	}

	@Override
	public String[] addCommentaire(String commentaire, String imageName)
			throws IllegalArgumentException {

		HttpSession session = getThreadLocalRequest().getSession();
		String pseudo = (String) session.getAttribute("pseudo");

		String commHTML = commentaire.replaceAll("'", "&rsquo;");
		commHTML = commHTML.replaceAll("\"", "&rdquo;");
		commHTML = "<b>" + pseudo + " le " + new Date() + ": </b>" + commHTML
				+ "<br/>";

		StringBuilder commentaireToAdd = new StringBuilder();
		commentaireToAdd.append(searchCommentairesFromDB(imageName));
		commentaireToAdd.append(commHTML);

		// Préparation du résultat
		String commAjoute = "";
		String message = "";

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// TODO - Mettre la bonne date dans la requête

			// Création de la requête
			StringBuilder query = new StringBuilder();
			query.append("UPDATE image set commentaires = ' "
					+ commentaireToAdd.toString() + "' WHERE nom_image = '"
					+ imageName + "'");

			// Création d'un objet Statement
			java.sql.PreparedStatement state = connection
					.prepareStatement(query.toString());
			// L'objet ResultSet contient le résultat de la requête SQL
			int result = state.executeUpdate();
			// ResultSetMetaData resultMeta = result.getMetaData();

			// result.close();
			state.close();

			message = "commentaire ajouté !";
			logger.debug("Commentaire ajoutée en base : " + imageName + " par "
					+ pseudo);
			commAjoute = commHTML;

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Problème interne du serveur");
		}

		String[] result = { message, commAjoute };
		return result;
	}
}
