package fr.warriorteam.server.servlet;

import javax.servlet.ServletException;

import fr.cr.warriorteam.common.utils.spring.WTContextLoader;

public class WTContextLoaderServlet extends WTContextLoader{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1158194324918477329L;
	
	public void init() throws ServletException{
		super.init();
	}
	
	public void destroy() {
		super.destroy();
	}
	
}
