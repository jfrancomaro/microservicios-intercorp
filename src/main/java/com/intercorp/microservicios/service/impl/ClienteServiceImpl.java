package com.intercorp.microservicios.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.intercorp.microservicios.model.Cliente;
import com.intercorp.microservicios.repo.IClienteRepo;
import com.intercorp.microservicios.service.IClienteService;
import com.intercorp.microservicios.util.DTOClientes;

@Service
public class ClienteServiceImpl implements IClienteService {

	private static final Integer EDAD_MUERTE = 80;

	@Autowired
	private IClienteRepo repo;

	@Override
	public Cliente creaCliente(Cliente cliente) {
		return repo.save(cliente);
	}

	@Override
	public DTOClientes kpiClientes() {
		List<Cliente> clientes = repo.findAll();
		List<Integer> edades = new ArrayList<>();
		Double promedioEdad = 0.0;
		Double desviacionEstandarEdades = 0.0;
		Double sum = 0.0;

		for (Cliente cliente : clientes) {
			edades.add(cliente.getEdad());
		}

		for (Integer edad : edades) {
			promedioEdad += edad;
		}

		promedioEdad /= edades.size();

		for (Integer edad : edades) {
			sum += Math.pow(edad - promedioEdad, 2);
		}

		desviacionEstandarEdades = Math.sqrt(sum / (double) (edades.size() - 1));

		DecimalFormat df = new DecimalFormat("#.0000");

		DTOClientes dtoClientes = new DTOClientes();
		dtoClientes.setPromedioEdad(Double.valueOf(df.format(promedioEdad)));
		dtoClientes.setDesviacionEstandarEdades(Double.valueOf(df.format(desviacionEstandarEdades)));
		return dtoClientes;
	}

	@Override
	public List<Cliente> listClientes() {
		List<Cliente> clientes = repo.findAll(sortByIdAsc());

		for (Cliente cliente : clientes) {
			cliente.setFechaProbableMuerte(calcularFechaProbableMuerte(cliente.getFechaNacimiento()));
		}

		return clientes;
	}

	private Sort sortByIdAsc() {
		return Sort.by(Sort.Direction.ASC, "id");
	}

	private LocalDate calcularFechaProbableMuerte(final LocalDate fechaNacimiento) {
		return fechaNacimiento.plusYears(EDAD_MUERTE);
	}
}
