package com.skilldistillery.treattracker.entities;

import java.time.LocalDateTime;

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
	
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@Column(name="create_on")
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@ManyToOne //is this the right way to map this???
	@JoinColumn(name="in_reply_to_id")
	private int inReplyToId;
	
	
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
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

	public int getInReplyToId() {
		return inReplyToId;
	}

	public void setInReplyToId(int inReplyToId) {
		this.inReplyToId = inReplyToId;
	}

	@Override
	public String toString() {
		return "StoreComment [id=" + id + ", title=" + title + ", description=" + description + ", rating=" + rating
				+ ", store=" + store + ", createdOn=" + createdOn + ", inReplyToId=" + inReplyToId + "]";
	}
	
	

}
