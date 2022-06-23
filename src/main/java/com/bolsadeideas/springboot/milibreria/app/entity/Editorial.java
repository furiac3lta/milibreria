package com.bolsadeideas.springboot.milibreria.app.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "editoriales")
public class Editorial implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id_editorial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "nombre")
    @NotNull
    private String nombre;
    @Column(name = "alta")
    private boolean alta = true;
    
    public Editorial() {
    }
 
	public Editorial(String nombre, boolean alta) {
		super();
		this.nombre = nombre;
		this.alta = alta;
	}


	public Editorial(int id, String nombre, boolean alta) {
		super();
		this.id = id;
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

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }
    
    
}
