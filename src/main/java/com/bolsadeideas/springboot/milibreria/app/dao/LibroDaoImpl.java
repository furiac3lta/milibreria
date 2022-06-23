package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.milibreria.app.entity.Libro;

@Repository
public class LibroDaoImpl implements ILibroDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Libro> listaLibros() {
		return em.createQuery("from Libro").getResultList();
	}

	@Override
	@Transactional
	public void guardarLibro(Libro libro) {
		if(libro.getId()>0) {
			em.merge(libro);
		}else {
			em.persist(libro);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Libro buscarLibro(int id) {
		return em.find(Libro.class,id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		em.remove(buscarLibro(id));
		
	}

	@Override
	public void bajaLibro(int id) {
		Libro libro = buscarLibro(id);
		libro.setAlta(false);
		em.persist(libro);
		
	}

}
