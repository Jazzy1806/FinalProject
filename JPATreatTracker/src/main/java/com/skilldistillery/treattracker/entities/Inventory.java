package com.skilldistillery.treattracker.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Inventory {
	
	@EmbeddedId
	private InventoryId id;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	@MapsId(value="storeId")
	private Store store;
	
	private Double price;
	
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="product_id")
	@MapsId(value="productId")
	private Product product;
	
	private Boolean enabled;
	
	public Inventory() {
	
	}

	public InventoryId getId() {
		return id;
	}


	public void setId(InventoryId id) {
		this.id = id;
	}


	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, store);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(product, other.product) && Objects.equals(store, other.store);
	}

	@Override
	public String toString() {
		return "Inventory [itore=" + store + ", price=" + price + ", quantity=" + quantity + ", product="
				+ product + ", enabled=" + enabled + "]";
	}


	
	
	

}
