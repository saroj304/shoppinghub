package com.learner.shoppinghub.models;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Product> product;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
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

}
