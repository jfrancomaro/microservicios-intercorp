package com.intercorp.microservicios.service;

import java.util.List;

import com.intercorp.microservicios.model.Cliente;
import com.intercorp.microservicios.util.DTOClientes;

public interface IClienteService {

	Cliente creaCliente(Cliente cliente);
	
	DTOClientes kpiClientes();
	
	List<Cliente> listClientes();
}
