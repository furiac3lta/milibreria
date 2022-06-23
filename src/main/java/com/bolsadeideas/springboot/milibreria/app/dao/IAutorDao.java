package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.milibreria.app.entity.Autor;

public interface IAutorDao {

	public List<Autor> listaAutores();
	public void guardarAutor(Autor autor); 
	public Autor buscarAutor(int id);
	public void delete(int id);
	public void bajaAutor(int id);
	
}
