package com.skilldistillery.treattracker.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private Double weight;

	private String gender;

	private String image;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

////	@JsonIgnore
//	@OneToMany(mappedBy = "pet")
//	private Set<Breed> breeds;

//	@ManyToOne
//	@JoinColumn(name = "breed_id")
//	private Breed breed;
	
	@ManyToMany
	@JoinTable(name = "pet_has_breed",
			joinColumns = @JoinColumn(name = "pet_id"),
			inverseJoinColumns = @JoinColumn(name = "breed_id"))
	private Set<Breed> breeds;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "pet_has_dietary_needs", 
		joinColumns = @JoinColumn(name = "pet_id"), 
		inverseJoinColumns = @JoinColumn(name = "dietary_needs_id"))
	private Set<Diet> dietNeeds;

	public Pet() {
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Breed> getBreeds() {
		return breeds;
	}

	public void setBreeds(Set<Breed> breeds) {
		this.breeds = breeds;
	}

	public Set<Diet> getDietNeeds() {
		return dietNeeds;
	}

	public void setDietNeeds(Set<Diet> dietNeeds) {
		this.dietNeeds = dietNeeds;
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
		Pet other = (Pet) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", weight=" + weight + ", gender=" + gender + ", image=" + image
				+ ", birthDate=" + birthDate + ", user=" + user + "]";
	}
}
