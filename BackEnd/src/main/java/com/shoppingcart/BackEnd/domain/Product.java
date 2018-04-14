package com.shoppingcart.BackEnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "product")
public class Product {
	@Id
	private String id;

	private String name;
	
	private String description;

	@Column(name = "category_id")
	private String categoryId;

	@Column(name = "price")
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "supplier_id")
	private String supplierId;

	@ManyToOne
	@JoinColumn(name = "category_id", updatable = false, insertable = false, nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "supplier_id", updatable = false, insertable = false, nullable = false)
	private Supplier supplier;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryid) {
		this.categoryId = categoryid;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}
