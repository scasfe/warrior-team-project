package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository 
public class DaoImpl implements IDao{
	private static final long serialVersionUID = 1L;
	final private Logger logger = Logger.getLogger(getClass().getName());
	/** * spring template for jdbc (jdk 5ou+)*/
	private SimpleJdbcTemplate jt=null;
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jt=new SimpleJdbcTemplate(dataSource);
	}
	
	public String getClient(final String nom, final String prenom) {
		if(nom==null || "".equals(nom.trim()) ) return null;
		String client=null;
//		String sql=Constants. + "  WHERE lower(CLINOM)='"+nom.toLowerCase()+"'";
//		if(prenom!=null &&  "".equals(prenom.trim()) ) {
//			sql+=" AND lower(CLIPRENOM)='"+prenom.toLowerCase()+"'";
//		}
//		List<Client> clients=jt.query(sql, new Mapper(), new Object[]{});
//		if(clients.size()>0) client=clients.get(0);
		logger.info("get Client = "+client);
		System.out.println("get Client = "+client);
		return "Test client 1";
      }
	
      public List<String> getClients(){		
//		String sql=Constants.SQL_REQUETE_CLIENT ;
//		List<Client> clients=jt.query(sql, new Mapper(), new Object[]{});		
    	  List<String> clients = new ArrayList<String>();
    	  clients.add("Test 1");
    	  clients.add("Test 2");
    	  logger.debug(clients.toString());
		return clients;
      }
 
      private  final class Mapper implements ParameterizedRowMapper  {
	    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return populateClient(rs);
	    }
	}
	private String populateClient(final ResultSet rs)	throws SQLException {
		if(rs==null) return null;
		String client=new String();
//		client.setCliId    (  rs.getLong("cliId")      );
//		client.setNom   (  rs.getString("cliNom")   );
//		client.setPrenom(  rs.getString("cliPrenom"));
//		client.setEmail (  rs.getString("cliEmail") );
		client = "Test populate client";
		return client;
	}
}