package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.milibreria.app.entity.Prestamo;

public interface IPrestamoDao {
	public List<Prestamo> listaPrestamo();
	public void guardarPrestamo(Prestamo prestamo);
	public Prestamo buscarPrestamo(int id);
	public void delete(int id);
	public void bajaPrestamo(int id);
}
