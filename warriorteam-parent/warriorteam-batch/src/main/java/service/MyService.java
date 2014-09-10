package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GenericDao;
import dao.IDao;
import fr.warriorteam.common.model.entity.Stock;

@Service()
public class MyService {
	// private final Logger logger=Logger.getLogger(this.getClass().getName());

	@Autowired
	private IDao dao;

	@Autowired
	private GenericDao stockDao;

	public void getClient(String nom, String prenom) {
		String client = dao.getClient(nom, prenom);
		System.out.println(client);
		Stock stock = new Stock();
		stock.setStockCode("7668");
		stock.setStockName("HAIO");
		stockDao.save(stock);

		/** select **/
		Stock stock2 = stockDao.findByStockCode("7668");
		System.out.println(stock2);

		/** update **/
		stock2.setStockName("HAIO-1");
		stockDao.update(stock2);

		/** delete **/
		// stockDao.delete(stock2);

		System.out.println("Done Stock BDD");

	}

}
