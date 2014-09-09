package fr.cr.warriorteam.common.dao;

import org.springframework.transaction.annotation.Transactional;

import fr.cr.warriorteam.common.model.entity.Stock;

public interface StockDao {

	@Transactional
	public void save(Stock stock);

	@Transactional
	public void update(Stock stock);

	@Transactional
	public void delete(Stock stock);

	@Transactional
	public Stock findByStockCode(String stockCode);

}
