package interfaz;

public class Heroe extends PersonajeAccion implements Luchador, Nadador, Volador {
	@Override
	public void volar() {
		System.out.println("El h�roe vuela.");		
	}

	@Override
	public void nadar() {				
		System.out.println("El h�roe nada.");
	}

	@Override
	public void luchar() {
		System.out.println("El h�roe lucha");		
	}
}
