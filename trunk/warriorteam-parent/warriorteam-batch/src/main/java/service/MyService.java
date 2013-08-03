package service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DaoImpl;
import dao.IDao;

@Service()
public class MyService implements IDao{
	private final Logger logger=Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;
	private DaoImpl daoImpl;
	@Autowired
	public void setDaoImpl(DaoImpl daoImpl) {
		this.daoImpl = daoImpl;
	}
	public String getClient(final String nom, final String prenom) {		
		String client= daoImpl.getClient(nom,prenom);
		logger.info("Client  '"+prenom+"','"+nom+"': "+client);
		return client;
	}
	public List<String> getClients() {		
		List<String> listeClients= daoImpl.getClients();
		for(String client:listeClients) {logger.info(client);}
		return listeClients;
	}
	
}