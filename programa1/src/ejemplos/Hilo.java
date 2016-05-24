package ejemplos;

public class Hilo implements Runnable {
	public String nombre;
	public Hilo(String nombre) {
		this.nombre=nombre;
	}
	public Hilo() {		}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			System.out.println("Hilo corriendo "+nombre);
		}
	}	
}
