package com.learner.shoppinghub.models;

import jakarta.persistence.*;



@Entity
@Table(name = "product")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	@Column
	private String name;
	@Column
	private int price;
	@Column
	private int weight;
	@Column
	private String description;
	@Column
	private String imageName;
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
