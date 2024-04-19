package com.se.ReminderManagement.entity;


import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class Category {
	private static final AtomicInteger count = new AtomicInteger(0); // Atomic counter to generate IDs
	

	@Id
	@Column(name = "category_id")
	private String categoryId;
	
	@Column(name = "category_name")
	@NotNull(message = "Category name should not be null ot empty")
	@NotBlank(message = "Category name should not be null ot empty")
	private String categoryName;
	@Column(name = "category_description")
	private String categoryDescription;

	@PrePersist
	protected void onCreate() {
		if (categoryId == null || categoryId.length() == 0) {
			String prefix = generatePrefixFromName(categoryName);
			this.categoryId = String.format("%s%03d", prefix, count.incrementAndGet());

		}
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	

	public Category(String categoryId,
			@NotNull(message = "Category name should not be null ot empty") @NotBlank(message = "Category name should not be null ot empty") String categoryName,
			String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + "]";
	}

	public Category() {
		super();
	}

	private String generatePrefixFromName(String name) {
		if (name == null || name.isEmpty())
			return "DEFAULT";

		return name.substring(0, Math.min(name.length(), 2)).toUpperCase();

	}

}
