package fr.warriorteam.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.warriorteam.server.utils.MDCUtils;

public abstract class WTServlet extends HttpServlet {

	public final void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// NE PAS APPELER SUPER.doPost()
		// Overriding this method to support a GET request also automatically
		// supports an HTTP HEAD request.
		// A HEAD request is a GET request that returns no body in the response,
		// only the request header fields.
		// super.doPost(request, response);

		// session
		HttpSession session = request.getSession();

		// Nettoyage du MDC
		MDCUtils.cleanMDC(session);

		// appel de la suite du process
		doProcess(request, response);
	}

	/**
	 * A overrider lors de la création de servlet pour le projet WarriorTeam
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	protected abstract void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

}
