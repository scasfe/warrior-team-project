package fr.warriorteam.common.utils.spring;

import java.util.Map;

import org.springframework.context.ApplicationContext;

public class WTContextUtils {

	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext c) {
		// LOGGER.debug("CONTEXT SPRING CHARGE");
		context = c;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> c) {
		Map<?, ?> beans = context.getBeansOfType(c);
		if (beans == null || beans.size() != 1) {
			// throw new
			// UnexpectedException("Pas de bean ou plusieurs instances pour " +
			// c.getName());
		}
		return (T) beans.values().iterator().next();
	}

	/**
	 * FIXED :Constructeur en private permettant d'éviter l'instanciation de la
	 * classe
	 */
	private WTContextUtils() {
	}
}
