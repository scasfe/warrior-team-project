package fr.warriorteam.server.context;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import fr.warriorteam.server.enums.ApplicationContextAttribute;
import fr.warriorteam.server.utils.PropertiesUtils;

public class ContextLoader {

	/**
	 * Name of the class path resource (relative to the ContextLoader class)
	 * that defines ContextLoader's default strategy names.
	 */
	private static final String DEFAULT_STRATEGIES_PATH =  "/dataSource.properties";
	

	private static final Logger logger = Logger.getLogger(ContextLoader.class);

	/**
	 * Map from (thread context) ClassLoader to WebApplicationContext. Often
	 * just holding one reference - if the ContextLoader class is deployed in
	 * the web app ClassLoader itself!
	 */
	// private static final Map currentContextPerThread = CollectionFactory
	// .createConcurrentMapIfPossible(1);

	/**
	 * The root WebApplicationContext instance that this loader manages.
	 */
	private ServletContext context;

	public ServletContext initWebApplicationContext(
			ServletContext servletContext) throws IllegalStateException {

		context = servletContext;

		if (servletContext
				.getAttribute(ApplicationContextAttribute.PROPERTIES_OK
						.toString()) != null) {
			throw new IllegalStateException(
					"Cannot initialize context because there is already a root application context present - "
							+ "check whether you have multiple ContextLoader* definitions in your web.xml!");
		}

		servletContext
				.log("Initializing CONTEXT LOADER root WebApplicationContext");
		if (logger.isInfoEnabled()) {
			logger.info("Root WebApplicationContext: initialization started");
		}
		long startTime = System.currentTimeMillis();

		try {

			createWebApplicationProperties();
			servletContext.setAttribute(
					ApplicationContextAttribute.PROPERTIES_OK.toString(),
					this.context);

			if (logger.isDebugEnabled()) {
				logger.debug("Published root WebApplicationContext as ServletContext attribute with name ["
						+ ApplicationContextAttribute.PROPERTIES_OK.toString()
						+ "]");
			}
			if (logger.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				logger.info("Root WebApplicationContext: initialization completed in "
						+ elapsedTime + " ms");
			}

			return this.context;
		} catch (RuntimeException ex) {
			logger.error("Context initialization failed", ex);
			servletContext
					.setAttribute(ApplicationContextAttribute.PROPERTIES_ERROR
							.toString(), ex);
			throw ex;
		} catch (Error err) {
			logger.error("Context initialization failed", err);
			servletContext.setAttribute(
					ApplicationContextAttribute.PROPERTIES_ERROR.toString(),
					err);
			throw err;
		}
	}

	private void createWebApplicationProperties() {
		try {
			
	//		PropertiesUtils.loadProperties(DEFAULT_STRATEGIES_PATH);
			
			logger.debug("Chargement de external-properties");
			
			
		String path = context.getRealPath("/WEB-INF/classes/datasource.properties");
			logger.debug("CONTEXT PATH : "+path);
			PropertiesUtils.loadProperties(path);
			String externalPropPath = PropertiesUtils.getProperties("path.external.properties")+"/wt-external.properties";
			PropertiesUtils.loadProperties(externalPropPath);
		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			logger.debug("Context initialization failed with default, FILE NOT FOUND", e);
//			try {
//				PropertiesUtils.loadProperties(DEVPT_STRATEGIES_PATH);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				logger.error("Context initialization failed with second", e1);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Context initialization failed", e);
		}
	}

	public void closeWebApplicationContext() {

	}

}
