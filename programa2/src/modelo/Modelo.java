package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Modelo {
	private static Modelo instancia;
	private ArrayList<PersonaBean> listaPersonas = new ArrayList<>();

	private Modelo() {
		listaPersonas = new ArrayList<>();
	}

	public static Modelo getInstance() {
		if (instancia == null) {
			instancia = new Modelo();
		}

		return instancia;
	}

	public ArrayList<PersonaBean> getListaPersonas() {
		return listaPersonas;
	}

	public void guardar() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("modelo.ser"));
		out.writeObject(listaPersonas);
		out.close();
	}

	public void cargar() throws Exception {
		File fic = new File("modelo.ser");
		if (!fic.exists())
			return;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("modelo.ser"));
		Object readObject = in.readObject();
		listaPersonas = (ArrayList<PersonaBean>) readObject;
		in.close();
	}

	public PersonaBean buscarPorNombre(String nombre) {
		int indice = listaPersonas.indexOf(nombre);
		if (indice == -1) {
			return null;
		}

		return listaPersonas.get(indice);
	}
}
