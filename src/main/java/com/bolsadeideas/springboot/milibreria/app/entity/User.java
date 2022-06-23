package com.bolsadeideas.springboot.milibreria.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username", unique = true)
	@NotEmpty	
    @Size(min=4, max=12)
	private String username;
	@Column(name = "password")
	@NotEmpty
	private String password;
	@NotNull
	private Boolean alta;
	
	
	public User() {
	
	}
	public User(@NotEmpty @Size(min = 4, max = 12) String username, @NotEmpty String password, @NotNull Boolean alta) {
		super();
		this.username = username;
		this.password = password;
		this.alta = alta;
	}
	public User(int id, @NotEmpty @Size(min = 4, max = 12) String username, @NotEmpty String password,
			@NotNull Boolean alta) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.alta = alta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAlta() {
		return alta;
	}
	public void setAlta(Boolean alta) {
		this.alta = alta;
	}
	
	
	
	
}
