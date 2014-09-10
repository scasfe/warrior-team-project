package fr.warriorteam.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

import fr.warriorteam.common.model.entity.Stock;

public class NewsResultDTO implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165655170723191811L;

	private Stock stock;

	// public Stock getStock() {
	// return stock;
	// }
	//
	// public void setStock(Stock stock) {
	// this.stock = stock;
	// }

}
