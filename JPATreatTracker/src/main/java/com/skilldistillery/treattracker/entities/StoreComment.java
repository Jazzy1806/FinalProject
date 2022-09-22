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
@Table(name="store_comment")
public class StoreComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@CreationTimestamp
	@Column(name="created_on")
	private LocalDateTime createdOn;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "in_reply_to_id")
	private StoreComment parentStoreComment;

	@OneToMany(mappedBy = "parentStoreComment")
	private Set<StoreComment> replyStoreComments = new HashSet<>();
	

	public StoreComment() {
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public StoreComment getParentStoreComment() {
		return parentStoreComment;
	}

	public void setParentStoreComment(StoreComment parentStoreComment) {
		this.parentStoreComment = parentStoreComment;
	}

	public Set<StoreComment> getReplyStoreComments() {
		return replyStoreComments;
	}

	public void setReplyStoreComments(Set<StoreComment> replyStoreComments) {
		this.replyStoreComments = replyStoreComments;
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
		StoreComment other = (StoreComment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "StoreComment [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
				+ ", store=" + store + ", createdOn=" + createdOn + ", parentStoreComment=" + parentStoreComment + "]";
	}

}
