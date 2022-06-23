package com.bolsadeideas.springboot.milibreria.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_prestamo")
	private int id;
	@Column(name="fecha_prestamo")
	@Temporal(TemporalType.DATE)
	private Date fechaPrestamo;
	@Column(name="fecha_devolucion")
	@Temporal(TemporalType.DATE)
	private Date fechaDevolucion;
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	@Column(name = "alta")
    private boolean alta = true;
	
	public Prestamo() {
		
	}
	public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Libro libro, User user, boolean alta) {
		super();
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.libro = libro;
		this.user = user;
		this.alta = alta;
	}
	public Prestamo(int id, Date fechaPrestamo, Date fechaDevolucion, Libro libro, User user, boolean alta) {
		super();
		this.id = id;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.libro = libro;
		this.user = user;
		this.alta = alta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}
	
	
}
