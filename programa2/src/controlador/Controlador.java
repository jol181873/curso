package controlador;

import java.util.ArrayList;

import modelo.Modelo;
import modelo.PersonaBean;

public class Controlador {

	public void guardar() throws Exception {
		Modelo.getInstance().guardar();
	}

	public void cargar() throws Exception {
		Modelo.getInstance().cargar();
	}

	public ArrayList<PersonaBean> getListaPersonas() {
		return Modelo.getInstance().getListaPersonas();
	}
}
