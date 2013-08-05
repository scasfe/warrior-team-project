package fr.warriorteam.server.rpc;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.client.rpc.RpcNewsService;
import fr.warriorteam.dto.NewsResultDTO;
import fr.warriorteam.server.service.NewsService;

public class RpcNewsServiceImpl extends RemoteServiceServlet implements RpcNewsService{

	private NewsService newsService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4139461489232212513L;

	public NewsResultDTO processSuccess() {
		newsService  = (NewsService) WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBeansOfType(NewsService.class).values().iterator().next();
		
		return newsService.processSuccess();
	}

}
