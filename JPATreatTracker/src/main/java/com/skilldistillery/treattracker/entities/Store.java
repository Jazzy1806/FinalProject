package com.skilldistillery.treattracker.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
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

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", websiteUrl=" + websiteUrl + ", logoUrl=" + logoUrl + ", chain=" + chain + "]";
	}
	
	

}
