package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.milibreria.app.entity.Prestamo;

@Repository
public class PrestamoDaoImpl implements IPrestamoDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Prestamo> listaPrestamo() {
		return em.createQuery("from Prestamo").getResultList();
		
	}

	@Override
	@Transactional
	public void guardarPrestamo(Prestamo prestamo) {
		if(prestamo.getId() >0) {
			em.merge(prestamo);
		}else {
			em.persist(prestamo);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo buscarPrestamo(int id) {
		return em.find(Prestamo.class, id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		em.remove(buscarPrestamo(id));
		
	}

	@Override
	public void bajaPrestamo(int id) {
		Prestamo prestamo = buscarPrestamo(id);
		prestamo.setAlta(false);
		em.persist(prestamo);
		
	}

}
