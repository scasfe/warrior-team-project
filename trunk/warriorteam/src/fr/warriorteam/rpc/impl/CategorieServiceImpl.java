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
import fr.warriorteam.server.exception.WebFonctionnelleException;
import fr.warriorteam.server.servlet.WTRemoteService;
import fr.warriorteam.server.utils.DAOFactory;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class CategorieServiceImpl extends WTRemoteService implements
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
			throw new IllegalArgumentException("Vous n'êtes pas connecté !");
		}

		// Ajout du créateur
		categorie.setCreateur((String) session.getAttribute("pseudo"));

		String resultMessage = null;

		// Check si la catégorie existe déjà
		if (checkIfExists(categorie)) {
			resultMessage = "La categorie existe deja !";
			return resultMessage;
		}

		// Ajout de la catégorie
		addCategorie(categorie);

		return resultMessage;
	}

	private void addCategorie(CategorieDTO categorie) {

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			try {
				// TODO - Mettre la bonne date dans la requête

				// Création de la requête
				StringBuilder query = new StringBuilder();
				query.append("INSERT INTO categories (nom_categorie, dossier_fichiers, date_creation, createur) VALUES("
						+ "'"
						+ categorie.getNomCategorie()
						+ "','"
						+ categorie.getDossier()
						+ "','"
						+ categorie.getDate()
						+ "-"
						+ Calendar.DAY_OF_MONTH
						+ "','"
						+ categorie.getCreateur() + "')");

				// Création d'un objet Statement
				java.sql.PreparedStatement state = connection
						.prepareStatement(query.toString());
				// L'objet ResultSet contient le résultat de la requête SQL
				int result = state.executeUpdate();
				// ResultSetMetaData resultMeta = result.getMetaData();

				// result.close();
				state.close();

				logger.debug("Catégorie ajoutée en base : "
						+ categorie.getNomCategorie());
			} finally {
				connection.close();
			}
		} catch (SQLException e) {

			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Problème interne du serveur");
		}

	}

	private boolean checkIfExists(CategorieDTO categorie) {
		Connection connection;
		try {
			connection = DAOFactory.getConnection();
			try {

				// Création de la requête
				StringBuilder query = new StringBuilder();
				query.append("SELECT * FROM categories WHERE nom_categorie = '"
						+ categorie.getNomCategorie() + "'");

				// Création d'un objet Statement
				Statement state = connection.createStatement();
				// L'objet ResultSet contient le résultat de la requête SQL
				ResultSet result = state.executeQuery(query.toString());

				// Compteur de résultat;
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

	@Override
	public String deleteCategorie(CategorieDTO categorie)
			throws WebFonctionnelleException {
		// TODO a enlever - pour tester le d

		HttpSession session = getThreadLocalRequest().getSession();

		if (!LoginServiceImpl.checkSession(session)) {
			throw new IllegalArgumentException("Vous n'êtes pas connecté !");
		}

		// il n'y a que le créateur de la catégorie qui peut détuire une
		// catégorie
		String createur = (String) session.getAttribute("pseudo");

		if (!createur.equals(categorie.getCreateur())) {
			throw new WebFonctionnelleException(
					"Vous n'etes pas le createur de la categorie. Seul "
							+ categorie.getCreateur()
							+ " peut supprimer cette categorie !");
		}

		String resultMessage = null;

		// Check si la catégorie existe déjà
		if (checkIfExists(categorie)) {
			// Suppression de la catégorie
			deleteCategorieDB(categorie);
		}

		logger.debug("Catégorie déjà supprimée dans une session concurrente "
				+ categorie.getNomCategorie());
		return resultMessage;
	}

	private void deleteCategorieDB(CategorieDTO categorie) {
		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			try {
				// TODO - Mettre la bonne date dans la requête

				// Création de la requête
				StringBuilder query = new StringBuilder();
				query.append("DELETE FROM categories WHERE nom_categorie = '"
						+ categorie.getNomCategorie() + "'");

				// Création d'un objet Statement
				java.sql.PreparedStatement state = connection
						.prepareStatement(query.toString());
				// L'objet ResultSet contient le résultat de la requête SQL
				int result = state.executeUpdate();
				// ResultSetMetaData resultMeta = result.getMetaData();

				// result.close();
				state.close();

				logger.debug("Catégorie supprimée en base : "
						+ categorie.getNomCategorie());
			} finally {
				connection.close();
			}
		} catch (SQLException e) {

			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Problème interne du serveur");
		}

	}
}
