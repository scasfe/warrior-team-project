package fr.warriorteam.rpc.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import fr.warriorteam.rpc.LoginService;
import fr.warriorteam.rpc.dto.LoginDTO;
import fr.warriorteam.server.utils.DAOFactory;
import fr.warriorteam.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.DriverManager;
import java.util.HashSet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private static final HashSet<HttpSession> sessionActiveList = new HashSet<HttpSession>();

	public String login(LoginDTO input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input.getLogin())
				|| input.getPwdEncrypted().equals("")) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			throw new IllegalArgumentException(
					"Name and Password must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
		String login = escapeHtml(input.getLogin());
		userAgent = escapeHtml(userAgent);

		StringBuilder msg = new StringBuilder();

		Connection connection;

		try {

			connection = DAOFactory.getConnection();
			
			// Création de la requête
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM comptes where login = ");
			query.append("'" + login + "'");
			query.append(" and password = ");
			query.append("'" + input.getPwdEncrypted() + "'");

			// Création d'un objet Statement
			Statement state = connection.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery(query.toString());

			int nbResults = 0;
			while (result.next()) {
				nbResults++;
			}

			result.close();
			state.close();
			if (nbResults != 1) {
				throw new IllegalArgumentException(
						"Login et/ou password incorrect(s)");
			}
		} catch (SQLException e) {
			// TODO logger erreur
			throw new IllegalArgumentException("Problème interne du serveur");
		}

		HttpSession session = getThreadLocalRequest().getSession();
		sessionActiveList.add(session);

		return "Hello, " + input.getLogin() + "!<br><br>" + "I am running "
				+ serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent + msg.toString();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public boolean checkSession() {
		HttpSession session = getThreadLocalRequest().getSession();
		return sessionActiveList.contains(session);

	}

	public static boolean checkSession(HttpSession session) {
		return sessionActiveList.contains(session);
	}

	@Override
	public String logout() {

		HttpSession session = getThreadLocalRequest().getSession();
		if (sessionActiveList.contains(session)) {
			sessionActiveList.remove(session);
		}
		session.invalidate();

		return "Vous êtes maintenant déconnecté.";
	}
}
