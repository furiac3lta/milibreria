package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;
import com.bolsadeideas.springboot.milibreria.app.entity.Editorial;

public interface IEditorialDao {
	public List<Editorial> listaEditoriales();
	public void guardarEditorial(Editorial editorial); 
	public Editorial buscarEditorial(int id);
	public void deleteEditorial(int id);
	public void bajaEditorial(int id);
}
