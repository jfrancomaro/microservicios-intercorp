package com.intercorp.microservicios.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 60, nullable = false, name = "nombre")
	private String nombre;

	@Column(length = 60, nullable = false, name = "apellido")
	private String apellido;

	@Column(nullable = false, name = "edad")
	private Integer edad;

	@Column(nullable = false, name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	@Transient
	private LocalDate fechaProbableMuerte;
}