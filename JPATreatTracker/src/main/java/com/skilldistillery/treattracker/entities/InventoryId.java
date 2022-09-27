package com.skilldistillery.treattracker.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventoryId implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Column(name="product_id")
	private int productId;
	
	@Column(name="store_id")
	private int storeId;
	

	public InventoryId() {
	}

	public InventoryId(int productId, int storeId) {
		this.productId = productId;
		this.storeId = storeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, storeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryId other = (InventoryId) obj;
		return productId == other.productId && storeId == other.storeId;
	}
	
	
}
