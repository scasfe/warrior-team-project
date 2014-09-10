package fr.warriorteam.common.utils.spring;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderServlet;

public class WTContextLoader extends ContextLoaderServlet {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	/** LOGGER */
	private static final Logger LOGGER = Logger.getRootLogger();

	public void init() throws ServletException {
		// Initialisation des paramètres de configuration des logs.

		LOGGER.debug("Start de WarriorTeam Web !");

		super.init();
		// WTContextUtils.setContext(WebApplicationContextUtils.getWebApplicationContext(getServletContext()));

		// J7SpringContextUtils.setContext(WebApplicationContextUtils.getWebApplicationContext(getServletContext()));
	}

	public void destroy() {
		super.destroy();

		// Initialisation des paramètres de configuration des logs.

		LOGGER.debug("Arrêt de WarriorTeam Web !");
	}
}
