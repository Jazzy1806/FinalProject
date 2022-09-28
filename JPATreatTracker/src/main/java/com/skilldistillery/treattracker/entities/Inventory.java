package com.skilldistillery.treattracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@CreationTimestamp
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

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
	

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
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
		return "Inventory [id=" + id + ", store=" + store + ", price=" + price + ", quantity=" + quantity
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", product=" + product.getId() + ", enabled="
				+ enabled + "]";
	}




	
	
	

}
