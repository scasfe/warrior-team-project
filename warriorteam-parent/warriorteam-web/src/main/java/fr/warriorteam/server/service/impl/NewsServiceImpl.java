package fr.warriorteam.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.warriorteam.common.model.entity.Stock;
import fr.warriorteam.dto.NewsResultDTO;
import fr.warriorteam.server.dao.WebGenericDao;
import fr.warriorteam.server.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private WebGenericDao stockDao;

	public NewsResultDTO processSuccess() {
		// TODO Auto-generated method stub
		// stockDao = (WebGenericDao)
		// WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getBeansOfType(WebGenericDao.class).values().iterator().next();

		/** insert **/
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

		System.out.println("Done");

		NewsResultDTO dto = new NewsResultDTO();
		// dto.setStock(stock);

		return dto;

	}

}
