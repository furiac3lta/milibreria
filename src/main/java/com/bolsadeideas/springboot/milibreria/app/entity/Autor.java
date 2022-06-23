package com.bolsadeideas.springboot.milibreria.app.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "autores")
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;



	@Id
    @Column(name = "id_autor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   	@Column(name = "nombre")
    @NotEmpty
    @Size(min=4, max=12)
    private String nombre;
    @Column(name = "alta")
    @NotNull
    private Boolean alta = true;


	public Autor() {
    }
    

    public Autor(int id, String nombre, Boolean alta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alta = alta;
	}


	public Autor(String nombre, Boolean alta) {
		super();
		this.nombre = nombre;
		this.alta = alta;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }

}
