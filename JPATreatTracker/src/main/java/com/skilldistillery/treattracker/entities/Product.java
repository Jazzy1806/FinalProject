package com.skilldistillery.treattracker.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String brand;
	private String description;
	private String image;

//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;

	@CreationTimestamp
	@Column(name = "created_on")
	private LocalDateTime dateCreated;

	@UpdateTimestamp
	@Column(name = "updated_on")
	private LocalDateTime dateUpdated;

//	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "ingredient_has_product", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductComment> comments;

//	@OneToMany(mappedBy = "product")
//	private List<Inventory> inventories;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductReport> reports;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Inventory> inventoryItems;

	public Product() {
	}
	

	public void removeComment(ProductComment comment) {
		if (comments != null && comments.contains(comment)) {
			comment.removeComment();
		}
	}
	
	public void removeIngredient(Ingredient ingredient) {
        if (ingredients != null && ingredients.contains(ingredient)) {
        	ingredients.remove(ingredient);
        	ingredient.removeProduct(this);
        }
    }

	public void removeIngredients() {
		if (ingredients != null) {
            List<Ingredient> ingredientsToRemove = new ArrayList<>(ingredients);
            for (Ingredient ingredientToRemove : ingredientsToRemove) {
            	ingredientToRemove.removeProduct(this);
            }
            ingredients.clear();
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<ProductComment> getComments() {
		return comments;
	}

	public void setComments(List<ProductComment> comments) {
		this.comments = comments;
	}

	public List<Inventory> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<Inventory> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public List<ProductReport> getReports() {
		return reports;
	}

	public void setReports(List<ProductReport> reports) {
		this.reports = reports;
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
		Product other = (Product) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description + ", image="
				+ image + ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", ingredients="
				+ ingredients + "]";
	}

}
