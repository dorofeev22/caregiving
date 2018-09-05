package ru.dorofeev22.caregiving.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "caregiving")
public class Product {

	public Product() {
	}

	@Id
	private String number;
	private String name;

	public Product(String number, String name) {
		this.number = number;
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}
}


