package interfaz;

public class Heroe extends PersonajeAccion implements Luchador, Nadador, Volador {
	@Override
	public void volar() {
		System.out.println("El héroe vuela.");		
	}

	@Override
	public void nadar() {				
		System.out.println("El héroe nada.");
	}

	@Override
	public void luchar() {
		System.out.println("El héroe lucha");		
	}
}
