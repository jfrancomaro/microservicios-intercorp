package com.intercorp.microservicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intercorp.microservicios.model.Cliente;
import com.intercorp.microservicios.service.IClienteService;
import com.intercorp.microservicios.util.DTOClientes;

@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService service;

	@PostMapping(value = "/creacliente")
	public ResponseEntity<Cliente> creaCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(service.creaCliente(cliente), HttpStatus.CREATED);
	}

	@GetMapping(value = "/kpideclientes")
	public ResponseEntity<DTOClientes> kpideClientes() {
		return new ResponseEntity<DTOClientes>(service.kpiClientes(), HttpStatus.OK);
	}

	@GetMapping(value = "/listclientes")
	public ResponseEntity<List<Cliente>> listClientes() {
		return new ResponseEntity<List<Cliente>>(service.listClientes(), HttpStatus.OK);
	}
}
