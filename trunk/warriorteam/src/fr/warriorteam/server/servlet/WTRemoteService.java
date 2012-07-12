package fr.warriorteam.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.warriorteam.rpc.impl.LoginServiceImpl;
import fr.warriorteam.server.utils.MDCUtils;

public class WTRemoteService extends RemoteServiceServlet{
	
	private final Logger logger = Logger.getLogger(WTRemoteService.class);
	
	 public String processCall(String payload) throws SerializationException {
		
		 MDCUtils.cleanMDC(getThreadLocalRequest().getSession());
		 	 
		 return super.processCall(payload);
		 
	 }
}
