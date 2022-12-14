package com.skilldistillery.treattracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product_report")
public class ProductReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@CreationTimestamp
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "user_quantity")
	private Integer userQuantity;

	private Double price;

	@Column(name = "is_in_stock")
	private Boolean isInStock;

	private String remark;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "store_id")
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

	public Integer getUserQuantity() {
		return userQuantity;
	}

	public void setUserQuantity(Integer userQuantity) {
		this.userQuantity = userQuantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean isInStock() {
		return isInStock;
	}

	public void setInStock(Boolean isInStock) {
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

	public Boolean getIsInStock() {
		return isInStock;
	}

	public void setIsInStock(Boolean isInStock) {
		this.isInStock = isInStock;
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