package ru.dorofeev22.caregiving.entities;

import javax.persistence.*;

@Entity
@Table(schema = "caregiving", name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String login;
 
	protected User() {
	}
 
	public User(String name, String login) {
		this.name = name;
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}


