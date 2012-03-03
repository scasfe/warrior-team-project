package fr.warriorteam.rpc.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.dto.ImageDTO;
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

	public List<ImageDTO> uploadFile(String path)
			throws IllegalArgumentException {

		// TODO a enlever - pour tester le d

		HttpSession session = getThreadLocalRequest().getSession();

		if (!LoginServiceImpl.checkSession(session)) {
			throw new IllegalArgumentException("Vous n'�tes pas connect� !");
		}

		// Le r�sultat retourn�
		List<ImageDTO> result = new ArrayList<ImageDTO>();

		// HttpSession session = getThreadLocalRequest().getSession();
		//
		// // if (LoginServiceImpl.checkSession(session)) {
		// // Appel de la servlet d'upload

		// Dossier image
		File[] images = null;
		File file = new File(
				PropertiesUtils.getProperties("path_file_images_resize") + path);
		// if(file.isDirectory()){
		images = file.listFiles();
		// }

		// // Map key: url photo, value : commentaires
		// HashMap<String, String> imagesList = new HashMap<String, String>();

		// s'il y a des images dansR le dossier
		if (images != null) {
			String path_images = PropertiesUtils
					.getProperties("path_file_images_resize");
			for (File image : images) {

				// allez voir en base le commentaire
				// TODO - cr�er service va lire en base commentaire pour les
				// photos
				// String commentaire =
				// imageService.searchCommentaires(image.getName());

				// Construction du DTO
				ImageDTO dto = new ImageDTO();

				dto.setNomImage("images/resize/" + path + "/" + image.getName());

				List<String> commPosteur = searchCommentairesAndPosteurFromDB(image
						.getName());
				if (commPosteur != null && commPosteur.size() > 1) {
					dto.setPosteur(commPosteur.get(0));
					dto.setCommentaires(commPosteur.get(1));
				}

				result.add(dto);

			}

		}

		// }

		return result;
	}

	private List<String> searchCommentairesAndPosteurFromDB(String name) {

		List<String> commentaires = null;

		HttpSession session = getThreadLocalRequest().getSession();

		// Si l'utilisa�teur est connect�, il peut voir les news r�serv�es aux
		// connect�s
		boolean sessionValide = LoginServiceImpl.checkSession(session);

		if (!sessionValide) {
			commentaires = new ArrayList<String>();
			commentaires
					.add("Vous devez �tre connect� pour voir les commentaires !");
			return commentaires;
		}

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// Cr�ation de la requ�te
			StringBuilder query = new StringBuilder();
			query.append("SELECT posteur, commentaires FROM image WHERE nom_image = '"
					+ name + "'");

			// Cr�ation d'un objet Statement
			Statement state = connection.createStatement();
			// L'objet ResultSet contient le r�sultat de la requ�te SQL
			ResultSet result = state.executeQuery(query.toString());
			ResultSetMetaData resultMeta = result.getMetaData();

			Integer nbNews = 0;
			while (result.next()) {
				commentaires = new ArrayList<String>();

				commentaires.add(result.getObject(1).toString());
				commentaires.add(result.getObject(2).toString());

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
		commentaireToAdd.append(searchCommentairesAndPosteurFromDB(imageName));
		commentaireToAdd.append(commHTML);

		// Pr�paration du r�sultat
		String commAjoute = "";
		String message = "";

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

			message = "commentaire ajout� !";
			logger.debug("Commentaire ajout�e en base : " + imageName + " par "
					+ pseudo);
			commAjoute = commHTML;

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Probl�me interne du serveur");
		}

		String[] result = { message, commAjoute };
		return result;
	}
}