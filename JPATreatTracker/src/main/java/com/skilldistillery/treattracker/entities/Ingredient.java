package com.skilldistillery.treattracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String type;

	@JsonIgnore
	@ManyToMany(mappedBy = "ingredients")
	private List<Product> products;

	public Ingredient() {
	}

	public void removeProduct(Product product) {
		if (products != null && products.contains(product)) {
			products.remove(product);
			product.removeIngredient(this);
		}
	}

	public void removeProducts() {
		if (products != null) {
			List<Product> productsToRemove = new ArrayList<>(products);
			for (Product productToRemove : productsToRemove) {
				productToRemove.removeIngredient(this);
			}
			products.clear();
		}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
		Ingredient other = (Ingredient) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
}
