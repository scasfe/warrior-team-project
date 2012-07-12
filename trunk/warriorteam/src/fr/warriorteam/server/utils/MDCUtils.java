package fr.warriorteam.server.utils;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class MDCUtils {
	private final static Logger logger = Logger.getLogger(MDCUtils.class);

	public static synchronized void cleanMDC(HttpSession session) {

		// Nettoyage du MDC
		String sessionIdMdc = (String) MDC.get("clientIdSession");
		String sessionIdReal = (String) session.getAttribute("session_id");

		// Si la session réelle est nulle alors on vérifie que en MDC elle
		// n'existe pas
		if (sessionIdReal == null && sessionIdMdc != null) {
			MDC.remove("clientIdSession");
			logger.info("Removing MDC Log4j because of null session detected");

		}

		// Si la session réelle n'est pas nulle on la met dans le MDC si elle
		// n'existe pas
		if (sessionIdReal != null) {
			if (sessionIdMdc != null
					&& !sessionIdMdc.equals('[' + sessionIdReal + ']')) {
				MDC.remove("clientIdSession");
				logger.info("Removing " + sessionIdMdc + " in MDC log4j");
			}

			MDC.put("clientIdSession", '[' + sessionIdReal + ']');
			// logger.info("Adding " + sessionIdReal + " in MDC log4j for user "
			// + session.getAttribute("pseudo"));
		}
	}

}
