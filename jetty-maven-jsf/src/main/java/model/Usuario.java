package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_USUAID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_USUAID_GENERATOR")
	@Column(name="USUA_ID")
	private long usuaId;

	@Column(name="USUA_NOMBRE")
	private String usuaNombre;

	@Column(name="USUA_PASSWORD")
	private String usuaPassword;

	public Usuario() {
	}

	public long getUsuaId() {
		return this.usuaId;
	}

	public void setUsuaId(long usuaId) {
		this.usuaId = usuaId;
	}

	public String getUsuaNombre() {
		return this.usuaNombre;
	}

	public void setUsuaNombre(String usuaNombre) {
		this.usuaNombre = usuaNombre;
	}

	public String getUsuaPassword() {
		return this.usuaPassword;
	}

	public void setUsuaPassword(String usuaPassword) {
		this.usuaPassword = usuaPassword;
	}

}