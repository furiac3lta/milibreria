package com.bolsadeideas.springboot.milibreria.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



@Entity
@Table(name = "libros")
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
	private int id;
	
	@Column(name = "isbn")
	@NotNull
    private long isbn;
	
    @Column(name = "titulo")
    @NotEmpty
    private String titulo;
    
    @Column(name = "anio")
    @NotNull
    private int anio;
    
    @Column(name = "ejemplares")
    @NotNull
    private int ejemplares;
    
    @Column(name = "ejemplaresp")
    @NotNull
    private int ejemplaresPrestados;
    
    @NotNull
    @Column(name = "ejemplaresr")
    private int ejemplaresRestantes;
    
    @Column(name = "alta")
    private boolean alta = true;
    
    @Column(name = "imagen")
    private String imagen;

@OneToOne// (cascade = {CascadeType.ALL}, orphanRemoval = true)

@JoinColumn(name="id_autor")
//@Column(name = "autor")
    private Autor autor;

@OneToOne// (cascade = {CascadeType.ALL}, orphanRemoval = true)

@JoinColumn(name="id_editorial")
//@Column(name = "id_editorial")
    private Editorial editorial;


    public Libro(int id, long isbn, String titulo, int anio, int ejemplares, int ejemplaresPrestados,
		int ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial, String imagen) {
	this.id = id;
	this.isbn = isbn;
	this.titulo = titulo;
	this.anio = anio;
	this.ejemplares = ejemplares;
	this.ejemplaresPrestados = ejemplaresPrestados;
	this.ejemplaresRestantes = ejemplaresRestantes;
	this.alta = alta;
	this.autor = autor;
	this.editorial = editorial;
	this.imagen=imagen;
}

	public Libro(long isbn, String titulo, int anio, int ejemplares, int ejemplaresPrestados, int ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial, String imagen) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
        this.imagen = imagen;
    }

    public Libro() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	public int getEjemplaresPrestados() {
		return ejemplaresPrestados;
	}

	public void setEjemplaresPrestados(int ejemplaresPrestados) {
		this.ejemplaresPrestados = ejemplaresPrestados;
	}

	public int getEjemplaresRestantes() {
		return ejemplaresRestantes;
	}

	public void setEjemplaresRestantes(int ejemplaresRestantes) {
		this.ejemplaresRestantes = ejemplaresRestantes;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares="
				+ ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes="
				+ ejemplaresRestantes + ", alta=" + alta + ", imagen=" + imagen + ", autor=" + autor + ", editorial="
				+ editorial + "]";
	}

    
    
   
}
