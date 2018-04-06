package ru.dorofeev22.caregiving.entities;

import javax.persistence.*;

@Entity
@Table(schema = "caregiving", name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String login;
	private String password;

	@Enumerated(EnumType.STRING)
	private Type type;

	public enum Type {
		admin, user
	}

	public User() {
	}

	public User(Long id, String name, String login, String password, Type type) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}


