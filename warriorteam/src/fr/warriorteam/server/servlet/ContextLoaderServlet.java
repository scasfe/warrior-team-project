package fr.warriorteam.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.warriorteam.server.context.ContextLoader;

/**
 * Bootstrap servlet to start up Spring's root {@link WebApplicationContext}.
 * Simply delegates to {@link ContextLoader}.
 * 
 * <p>
 * This servlet should have a lower <code>load-on-startup</code> value in
 * <code>web.xml</code> than any servlets that access the root web application
 * context.
 * 
 * <p>
 * <i>Note that this class has been deprecated for containers implementing
 * Servlet API 2.4 or higher, in favor of {@link ContextLoaderListener}.</i><br>
 * According to Servlet 2.4, listeners must be initialized before
 * load-on-startup servlets. Many Servlet 2.3 containers already enforce this
 * behavior. If you use such a container, this servlet can be replaced with
 * ContextLoaderListener.
 * 
 * <p>
 * Servlet 2.3 containers known to work with bootstrap listeners are:
 * <ul>
 * <li>Apache Tomcat 4.x+
 * <li>Jetty 4.x+
 * <li>Resin 2.1.8+
 * <li>Orion 2.0.2+
 * <li>BEA WebLogic 8.1 SP3
 * </ul>
 * For working with any of them, ContextLoaderListener is recommended.
 * 
 * <p>
 * Servlet 2.3 containers known <i>not</i> to work with bootstrap listeners are:
 * <ul>
 * <li>BEA WebLogic up to 8.1 SP2
 * <li>IBM WebSphere 5.x
 * <li>Oracle OC4J 9.0.3
 * </ul>
 * If you happen to work with such a server, this servlet has to be used.
 * 
 * <p>
 * So unfortunately, the only context initialization option that is compatible
 * with <i>all</i> Servlet 2.3 containers is this servlet.
 * 
 * <p>
 * Note that a startup failure of this servlet will not stop the rest of the web
 * application from starting, in contrast to a listener failure. This can lead
 * to peculiar side effects if other servlets get started that depend on
 * initialization of the root web application context.
 * 
 * @author Juergen Hoeller
 * @author Darren Davison
 * @see ContextLoaderListener
 * @see org.springframework.web.util.Log4jConfigServlet
 */
public class ContextLoaderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156542587031520576L;
	private ContextLoader contextLoader;

	/**
	 * Initialize the root web application context.
	 */
	public void init() throws ServletException {
		this.contextLoader = createContextLoader();
		this.contextLoader.initWebApplicationContext(getServletContext());
	}

	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * 
	 * @return the new ContextLoader
	 */
	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	/**
	 * Return the ContextLoader used by this servlet.
	 * 
	 * @return the current ContextLoader
	 */
	public ContextLoader getContextLoader() {
		return this.contextLoader;
	}

	/**
	 * Close the root web application context.
	 */
	public void destroy() {
		if (this.contextLoader != null) {
			this.contextLoader.closeWebApplicationContext();
		}
	}

	/**
	 * This should never even be called since no mapping to this servlet should
	 * ever be created in web.xml. That's why a correctly invoked Servlet 2.3
	 * listener is much more appropriate for initialization work
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		getServletContext().log(
				"Attempt to call service method on ContextLoaderServlet as ["
						+ request.getRequestURI() + "] was ignored");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	public String getServletInfo() {
		return "ContextLoaderServlet for Servlet API 2.3 "
				+ "(deprecated in favor of ContextLoaderListener for Servlet API 2.4)";
	}

}