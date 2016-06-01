package org.jsf.jol181873.modelo.dto;

/**
 * The persistent class for the PELUQUERIA database table.
 * 
 */

public class PeluqueriaDTO // implements Serializable {
		extends Bean<PeluqueriaDTO> {
	private static final long serialVersionUID = 1L;

	private long peluId;

	private String peluDireccion;

	private String peluNombre;

	private String peluTelefono;

	public PeluqueriaDTO() {
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