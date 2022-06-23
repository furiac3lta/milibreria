package com.bolsadeideas.springboot.milibreria.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="roles")
public class Rol {
	
	@Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//@Column(name = "user")
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	@Column(name = "rol")
	@NotNull
	private String rol;
	
	
	
	public Rol() {
		super();
	}
	public Rol(User user, @NotNull String rol) {
		super();
		this.user = user;
		this.rol = rol;
	}
	public Rol(int id, User user, @NotNull String rol) {
		super();
		this.id = id;
		this.user = user;
		this.rol = rol;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
