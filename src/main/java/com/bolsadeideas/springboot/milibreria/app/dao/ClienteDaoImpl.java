package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bolsadeideas.springboot.milibreria.app.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> listaClientes() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void guardarCliente(Cliente cliente) {
		if(cliente.getId() >0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);	
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarCliente(int id) {
		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void delete(int id) {
		em.remove(buscarCliente(id));
		
	}
	
}
