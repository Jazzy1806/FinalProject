package com.skilldistillery.treattracker.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "created_on")
	private LocalDate createdOn;

	@Column(name = "updated_on")
	private LocalDate updatedOn;

	@Column(name = "user_quantity")
	private LocalDate userQuantity;

	private double price;

	@Column(name = "is_in_stock")
	private boolean isInStock;

	private String remark;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany(mappedBy = "store_id")
	private Store store;

	public ProductReport() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public LocalDate getUserQuantity() {
		return userQuantity;
	}

	public void setUserQuantity(LocalDate userQuantity) {
		this.userQuantity = userQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isInStock() {
		return isInStock;
	}

	public void setInStock(boolean isInStock) {
		this.isInStock = isInStock;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductReport other = (ProductReport) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProductReport [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", userQuantity="
				+ userQuantity + ", price=" + price + ", isInStock=" + isInStock + ", remark=" + remark + ", user="
				+ user + ", product=" + product + ", store=" + store + "]";
	}
}
