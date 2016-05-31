package org.jsf.jol181873.servicio;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//import javax.annotation.ManagedBean;
//import javax.enterprise.context.SessionScoped;

@ManagedBean
@SessionScoped
public class Visitor implements Serializable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}