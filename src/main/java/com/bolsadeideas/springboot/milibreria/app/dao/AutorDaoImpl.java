package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.milibreria.app.entity.Autor;

@Repository
public class AutorDaoImpl implements IAutorDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Autor> listaAutores() {
		
		return em.createQuery("from Autor").getResultList();
	}

	@Override
	@Transactional
	public void guardarAutor(Autor autor) {
		if(autor.getId() >0) {
			em.merge(autor);
		}else {
			em.persist(autor);	
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public Autor buscarAutor(int id) {
		return em.find(Autor.class, id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		em.remove(buscarAutor(id));
		
	}

	@Override
	public void bajaAutor(int id) {
		Autor autor = buscarAutor(id);
		autor.setAlta(false);
		em.persist(autor);
		
		
		
	}

	
}
