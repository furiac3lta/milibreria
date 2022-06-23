package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.milibreria.app.entity.Libro;

public interface ILibroDao {
	public List<Libro> listaLibros();
	public void guardarLibro(Libro libro); 
	public Libro buscarLibro(int id);
	public void delete(int id);
	public void bajaLibro(int id);
}
