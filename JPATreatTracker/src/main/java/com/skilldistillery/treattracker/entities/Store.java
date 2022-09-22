package com.skilldistillery.treattracker.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	private String description;
	
	@Column(name="website_url")
	private String websiteUrl;
	
	@Column(name="logo_url")
	private String logoUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy="store")
	private List<Inventory> inventories;
	
	
	@OneToMany(mappedBy="store")
	private List<ProductReport> productReports;
	
	@ManyToOne
	@JoinColumn(name="chain_id")
	private Chain chain;
	

	@JsonIgnore
	@OneToMany(mappedBy="store")
	private List<StoreComment> comments;


	public Store() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductReport> getProductReports() {
		return productReports;
	}

	public void setProductReports(List<ProductReport> productReports) {
		this.productReports = productReports;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Chain getChain() {
		return chain;
	}

	public void setChain(Chain chain) {
		this.chain = chain;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<StoreComment> getComments() {
		return comments;
	}

	public void setComments(List<StoreComment> comments) {
		this.comments = comments;
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
		Store other = (Store) obj;
		return id == other.id;
	}


	
	

}
