package fr.warriorteam.common.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {

	@Id
	@Column(name = "STOCK_ID", columnDefinition = "INT", nullable = true, unique = false)
	private int stockId;

	@Column(name = "STOCK_CODE", columnDefinition = "VARCHAR2(10)", length = 10, nullable = false, unique = false)
	private String stockCode;

	@Column(name = "STOCK_NAME", columnDefinition = "VARCHAR2(20)", length = 20, nullable = false, unique = false)
	private String stockName;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 18445454545L;

	public String toString() {
		return "ID : " + stockId + " - Name : " + stockName + " - Code : "
				+ stockCode;
	}

}
