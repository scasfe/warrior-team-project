package fr.warriorteam.server.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import fr.warriorteam.server.utils.DAOFactory;
import fr.warriorteam.server.utils.ImagesUtils;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8841695241975408617L;

	private final Logger logger = Logger.getLogger(UploadServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Infos de session
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");

		ServletFileUpload upload = new ServletFileUpload();

		try {
			FileItemIterator iter = upload.getItemIterator(request);

			while (iter.hasNext()) {
				FileItemStream item = iter.next();

				String path = request.getParameter("path");
				String name = item.getFieldName();
				InputStream stream = item.openStream();

				// Path de images cr�ation du dossier si non existant
				File directory = new File(
						"../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"
								+ path);
				directory.mkdir();

				// Traitement du fichier
				if (!item.getName().matches("^.*\\.zip$")) {

					logger.info("Ajout du fichier : " + item.getName());

					// TODO - Couper la taille du nom � 30 caract�res

					File file = new File(
							"../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"
									+ path + "/" + item.getName());
					if (file.getName().matches(
							"^.*\\.(JPG|jpg|JPEG|BMP|bmp|png|PNG|GIF|gif)$")) {

						// File a laquelle rattacher le output stream
						FileOutputStream fos = new FileOutputStream(file);

						int len;
						byte[] buffer = new byte[8192];
						while ((len = stream.read(buffer, 0, buffer.length)) != -1) {
							fos.write(buffer, 0, len);
						}

						// TODO comment
						ImagesUtils.copyWithRedimImage(file, path);
						if (fos != null) {
							fos.close();
						}

						addAuthorAndDateOnDB(file.getName(), path, user_id);

					}

				} else {
					logger.info("Ajout du fichier ZIP: " + item.getName());
					BufferedOutputStream dest = null;
					byte[] BUFFER = new byte[8192];
					ZipInputStream zis = new ZipInputStream(stream);
					ZipEntry entree;
					int count;
					while ((entree = zis.getNextEntry()) != null) {
						logger.info("Ajout du fichier contenu dans le ZIP : "
								+ entree.toString());

						File file = new File(
								"../apache-tomcat-6.0.33-windows-x64/apache-tomcat-6.0.33/webapps/warriorteam/war/images/"
										+ path + "/" + entree.toString());
						if (file.getName()
								.matches(
										"^.*\\.(JPG|jpg|JPEG|BMP|bmp|png|PNG|GIF|gif)$")) {

							// File a laquelle rattacher le output stream
							FileOutputStream fos = new FileOutputStream(file);

							int len;
							byte[] buffer = new byte[8192];
							while ((len = zis.read(buffer, 0, buffer.length)) != -1) {
								fos.write(buffer, 0, len);
							}

							if (fos != null) {
								fos.close();
							}
							// zis.close();
							// fos.close();

							ImagesUtils.copyWithRedimImage(file, path);

							addAuthorAndDateOnDB(file.getName(), path, user_id);
						}

					}
					zis.close();

				}

			}
		} catch (Exception e) {
			logger.error("Erreur lors de l'upload du fichier "
					+ e.getStackTrace());
			throw new RuntimeException(e);
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void addAuthorAndDateOnDB(String imageName, String categorie,
			String userId) {

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// TODO - Mettre la bonne date dans la requ�te

			// Cr�ation de la requ�te
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO image (categorie_fk, nom_image, posteur, date) VALUES("
					+ "'"
					+ categorie
					+ "','"
					+ imageName
					+ "','"
					+ userId
					+ "','" + "2012-02-25" + "')");

			// Cr�ation d'un objet Statement
			java.sql.PreparedStatement state = connection
					.prepareStatement(query.toString());
			// L'objet ResultSet contient le r�sultat de la requ�te SQL
			int result = state.executeUpdate();
			// ResultSetMetaData resultMeta = result.getMetaData();

			// result.close();
			state.close();

			logger.debug("Image ajout�e en base : " + userId + " - "
					+ imageName + " - " + categorie);

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Probl�me interne du serveur");
		}

	}

}
