package com.intercorp.microservicios.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intercorp.microservicios.model.Cliente;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
