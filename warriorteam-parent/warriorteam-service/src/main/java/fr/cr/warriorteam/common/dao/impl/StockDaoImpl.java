package fr.cr.warriorteam.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import fr.cr.warriorteam.common.dao.StockDao;
import fr.cr.warriorteam.common.model.entity.Stock;


public abstract class StockDaoImpl extends HibernateDaoSupport implements StockDao{
	
	@Autowired
    public final void init(final HibernateTemplate hibernateTemplate) {
        setHibernateTemplate(hibernateTemplate);
	}
	
	@Transactional
			public void save(Stock stock){
				getHibernateTemplate().save(stock);
			}
	@Transactional
			public void update(Stock stock){
				getHibernateTemplate().update(stock);
			}
	@Transactional
			public void delete(Stock stock){
				getHibernateTemplate().delete(stock);
			}
	@Transactional
			public Stock findByStockCode(String stockCode){
				List list = getHibernateTemplate().find(
		                      "from Stock where stockCode=?",stockCode
		                );
				return (Stock)list.get(0);
			}
}
