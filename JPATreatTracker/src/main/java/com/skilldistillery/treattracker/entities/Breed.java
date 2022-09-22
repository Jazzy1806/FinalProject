package com.skilldistillery.treattracker.entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Breed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String color;

//	@ManyToOne
//	@JoinColumn(name = "pet_id")
//	private Pet pet;

//	@JsonIgnore
	@OneToMany(mappedBy = "breed")
	private Set<Pet> pets;

	public Breed() {
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
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
		Breed other = (Breed) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Breed [id=" + id + ", name=" + name + ", color=" + color + "]";
	}

}
