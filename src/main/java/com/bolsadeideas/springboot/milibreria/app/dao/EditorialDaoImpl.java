package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.milibreria.app.entity.Editorial;

@Repository
public class EditorialDaoImpl implements IEditorialDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Editorial> listaEditoriales() {		
		return em.createQuery("from Editorial").getResultList();
	}

	@Override
	@Transactional
	public void guardarEditorial(Editorial editorial) {
		if(editorial.getId()>0) {
			em.merge(editorial);
		}else {
			em.persist(editorial);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Editorial buscarEditorial(int id) {
		return em.find(Editorial.class, id);
	}

	@Override
	@Transactional
	public void deleteEditorial(int id) {
		em.remove(buscarEditorial(id));
		
	}

	@Override
	public void bajaEditorial(int id) {
		Editorial editorial = buscarEditorial(id);
		editorial.setAlta(false);
		em.persist(editorial);
		
		
	}

}
