package fr.warriorteam.rpc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.rpc.CategorieService;
import fr.warriorteam.server.utils.DAOFactory;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class CategorieServiceImpl extends RemoteServiceServlet implements
		CategorieService {

	private final Logger logger = Logger.getLogger(CategorieServiceImpl.class);

	/**
	 * 
	 */
	// private LoginService loginService = new LoginServiceImpl();

	public String createCategorie(CategorieDTO categorie)
			throws IllegalArgumentException {

		// TODO a enlever - pour tester le d

		HttpSession session = getThreadLocalRequest().getSession();

		if (!LoginServiceImpl.checkSession(session)) {
			throw new IllegalArgumentException("Vous n'�tes pas connect� !");
		}

		String resultMessage = null;

		// Check si la cat�gorie existe d�j�
		if (checkIfExists(categorie)) {
			resultMessage = "La categorie existe deja !";
			return resultMessage;
		}

		// Ajout de la cat�gorie
		addCategorie(categorie);

		return resultMessage;
	}

	private void addCategorie(CategorieDTO categorie) {

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			try {
				// TODO - Mettre la bonne date dans la requ�te

				// Cr�ation de la requ�te
				StringBuilder query = new StringBuilder();
				query.append("INSERT INTO categories (nom_categorie, dossier_fichiers, date_creation) VALUES("
						+ "'"
						+ categorie.getNomCategorie()
						+ "','"
						+ categorie.getDossier()
						+ "','"
						+ categorie.getDate()
						+ "-" + Calendar.DAY_OF_MONTH + "')");

				// Cr�ation d'un objet Statement
				java.sql.PreparedStatement state = connection
						.prepareStatement(query.toString());
				// L'objet ResultSet contient le r�sultat de la requ�te SQL
				int result = state.executeUpdate();
				// ResultSetMetaData resultMeta = result.getMetaData();

				// result.close();
				state.close();

				logger.debug("Cat�gorie ajout�e en base : "
						+ categorie.getNomCategorie());
			} finally {
				connection.close();
			}
		} catch (SQLException e) {

			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Probl�me interne du serveur");
		}

	}

	private boolean checkIfExists(CategorieDTO categorie) {
		Connection connection;
		try {
			connection = DAOFactory.getConnection();
			try {

				// Cr�ation de la requ�te
				StringBuilder query = new StringBuilder();
				query.append("SELECT * FROM categories WHERE nom_categorie = '"
						+ categorie.getNomCategorie() + "'");

				// Cr�ation d'un objet Statement
				Statement state = connection.createStatement();
				// L'objet ResultSet contient le r�sultat de la requ�te SQL
				ResultSet result = state.executeQuery(query.toString());

				// Compteur de r�sultat;
				Integer nbResult = 0;
				while (result.next()) {
					nbResult++;
				}

				result.close();
				state.close();

				if (nbResult != 0) {
					return true;
				} else {
					return false;
				}
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("Erreur SQL : ", e);
		}
		return false;
	}

}
