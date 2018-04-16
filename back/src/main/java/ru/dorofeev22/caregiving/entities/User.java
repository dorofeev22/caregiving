package ru.dorofeev22.caregiving.entities;

import javax.persistence.*;

@Entity
@Table(schema = "caregiving", name = "users")
public class User extends BaseEntity {

	private String name;
	private String login;
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_role")
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private Type type;

	public enum Type {
		admin, user
	}

	public User() {
	}

	public User(Long id, String name, String login, String password, UserRole role, Type type) {
		this.setId(id);
		this.name = name;
		this.login = login;
		this.password = password;
		this.role = role;
		this.type = type;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}


