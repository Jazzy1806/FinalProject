package com.skilldistillery.treattracker.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "product_comment")
public class ProductComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	private Integer rating;
	
	@CreationTimestamp
	@Column(name = "created_on")
	private LocalDateTime dateCreated;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "in_reply_to_id")
	private ProductComment parentProductComment;

	@OneToMany(mappedBy = "parentProductComment")
	private Set<ProductComment> replyProductComments = new HashSet<>();

	public ProductComment() {
		
	}
	
	public void removeComment() {
		if (replyProductComments != null) {
			replyProductComments.removeAll(replyProductComments);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductComment getParentProductComment() {
		return parentProductComment;
	}

	public void setParentProductComment(ProductComment parentProductComment) {
		this.parentProductComment = parentProductComment;
	}

	public Set<ProductComment> getReplyProductComments() {
		return replyProductComments;
	}

	public void setReplyProductComments(Set<ProductComment> replyProductComments) {
		this.replyProductComments = replyProductComments;
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
		ProductComment other = (ProductComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProductComment [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
				+ ", dateCreated=" + dateCreated + ", product=" + product + ", parentProductComment="
				+ parentProductComment + "]";
	}

}
