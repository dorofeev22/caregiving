package ru.dorofeev22.caregiving.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "caregiving")
public class UserRole extends BaseEntity {

	public UserRole() {
	}

	private String name;
	private String description;

	public UserRole(Long id, String name, String description) {
		this.setId(id);
		this.name = name;
		this.description = description;
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
}


