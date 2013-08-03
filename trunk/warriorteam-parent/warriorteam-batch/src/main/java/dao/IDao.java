package dao;

import java.io.Serializable;
import java.util.List;

public interface IDao extends Serializable {
	  String getClient(final String nom, final String prenom);
	  List<String> getClients(); 
	 }