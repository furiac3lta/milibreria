package com.bolsadeideas.springboot.milibreria.app.dao;

import java.util.List;

import com.bolsadeideas.springboot.milibreria.app.entity.Cliente;

public interface IClienteDao {
	public List<Cliente> listaClientes();
	public void guardarCliente(Cliente cliente);
	public Cliente buscarCliente(int id);
	public void delete(int id);
	
}
