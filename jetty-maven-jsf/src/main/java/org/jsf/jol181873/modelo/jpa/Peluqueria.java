package org.jsf.jol181873.modelo.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the PELUQUERIA database table.
 * 
 */
@Entity
@NamedQuery(name = "Peluqueria.findAll", query = "SELECT p FROM Peluqueria p")
public class Peluqueria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PELUQUERIA_PELUID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PELUQUERIA_PELUID_GENERATOR")
	@Column(name = "PELU_ID")
	private long peluId;

	@Column(name = "PELU_DIRECCION")
	private String peluDireccion;

	@Column(name = "PELU_NOMBRE")
	private String peluNombre;

	@Column(name = "PELU_TELEFONO")
	private String peluTelefono;

	public Peluqueria() {
	}

	public long getPeluId() {
		return this.peluId;
	}

	public void setPeluId(long peluId) {
		this.peluId = peluId;
	}

	public String getPeluDireccion() {
		return this.peluDireccion;
	}

	public void setPeluDireccion(String peluDireccion) {
		this.peluDireccion = peluDireccion;
	}

	public String getPeluNombre() {
		return this.peluNombre;
	}

	public void setPeluNombre(String peluNombre) {
		this.peluNombre = peluNombre;
	}

	public String getPeluTelefono() {
		return this.peluTelefono;
	}

	public void setPeluTelefono(String peluTelefono) {
		this.peluTelefono = peluTelefono;
	}

}