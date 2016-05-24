package multihilo;

public class Plato {
	private String nombre;
	private String[] ingredientes;
		
	public Plato(String nombre,String[] ingredientes) {
		this.nombre=nombre;
		this.ingredientes=ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public String[] getIngredientes() {
		return ingredientes;
	}
}
