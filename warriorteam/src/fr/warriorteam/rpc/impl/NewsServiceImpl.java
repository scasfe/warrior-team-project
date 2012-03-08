package fr.warriorteam.rpc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.dto.CategorieDTO;
import fr.warriorteam.rpc.NewsService;
import fr.warriorteam.rpc.dto.CategoriesDTO;
import fr.warriorteam.rpc.dto.NewsDTO;
import fr.warriorteam.server.utils.DAOFactory;

/**
 * The server side implementation of the RPC service.
 */

@SuppressWarnings("serial")
public class NewsServiceImpl extends RemoteServiceServlet implements
		NewsService {

	private final Logger logger = Logger.getLogger(NewsServiceImpl.class);

	/**
	 * 
	 */
	// private LoginService loginService = new LoginServiceImpl();

	public List<NewsDTO> searchLastNews() throws IllegalArgumentException {

		ArrayList<NewsDTO> resultDTOs = new ArrayList<NewsDTO>();

		HttpSession session = getThreadLocalRequest().getSession();

		// Si l'utilisaéteur est connecté, il peut voir les news réservées aux
		// connectés
		boolean sessionValide = LoginServiceImpl.checkSession(session);

		Connection connection;

		try {

			connection = DAOFactory.getConnection();

			// Création de la requête
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(id) FROM news");

			// Création d'un objet Statement
			Statement state = connection.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery(query.toString());
			ResultSetMetaData resultMeta = result.getMetaData();

			Integer nbNews = 0;
			while (result.next()) {

				for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
					nbNews = Integer.valueOf(result.getObject(i).toString());
				}
			}

			result.close();
			state.close();

			logger.debug(nbNews + " news trouvées");

			// On limite à 5 news l'affichage
			if (nbNews > 5) {
				nbNews -= 5;
			}
			query = new StringBuilder();
			query.append("SELECT * FROM news WHERE id > " + nbNews);
			if (!sessionValide) {
				query.append(" AND reservee = 0");
			}

			query.append(" ORDER BY id DESC");

			// Création d'un objet Statement
			state = connection.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			result = state.executeQuery(query.toString());
			resultMeta = result.getMetaData();

			while (result.next()) {
				NewsDTO newsDto = new NewsDTO();

				newsDto.setDate(result.getObject(1).toString());
				newsDto.setTitre(result.getObject(2).toString());
				newsDto.setTexte(result.getObject(3).toString());

				// Traitement du booléen
				int reservee = Integer.valueOf(result.getObject(4).toString());
				if (reservee == 1) {
					newsDto.setReservee(true);
				} else {
					newsDto.setReservee(false);
				}

				resultDTOs.add(newsDto);
				// for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
				// String news = result.getObject(i).toString();
				// results.getLogin().add(news);
				// }
			}

			result.close();
			state.close();

		} catch (SQLException e) {

			// TODO logger erreur
			logger.error("Erreur SQL : ", e);

			throw new IllegalArgumentException("Problème interne du serveur");
		}

		return resultDTOs;
	}

	public CategoriesDTO searchCategories() throws IllegalArgumentException {

		CategoriesDTO results = new CategoriesDTO();
		HttpSession session = getThreadLocalRequest().getSession();

		if (LoginServiceImpl.checkSession(session)) {
			Connection connection;

			try {

				connection = DAOFactory.getConnection();

				// Création de la requête
				StringBuilder query = new StringBuilder();
				query.append("SELECT * FROM categories ORDER BY id DESC");

				// Création d'un objet Statement
				Statement state = connection.createStatement();
				// L'objet ResultSet contient le résultat de la requête SQL
				ResultSet result = state.executeQuery(query.toString());
				ResultSetMetaData resultMeta = result.getMetaData();

				// Création d'un objet Statement
				state = connection.createStatement();
				// L'objet ResultSet contient le résultat de la requête SQL
				result = state.executeQuery(query.toString());

				while (result.next()) {

					CategorieDTO categorie = new CategorieDTO();

					categorie.setId(new Long((Integer) result.getObject(1)));
					categorie.setNomCategorie(result.getObject(2).toString());
					categorie.setDossier(result.getObject(3).toString());
					categorie.setDate(result.getObject(4).toString());
					categorie.setCreateur(result.getObject(5).toString());

					results.getCategories().add(categorie);

				}

				result.close();
				state.close();

			} catch (SQLException e) {

				// TODO logger erreur
				logger.error("Erreur SQL : ", e);

				throw new IllegalArgumentException(
						"Problème interne du serveur");
			}

		}

		return results;
	}

}
