package interfaz;

public class Aventurero {
	static void t(Luchador personaje){
		personaje.luchar();
	}
	static void u(Nadador personaje){
		personaje.nadar();
	}
	static void v(Volador personaje){
		personaje.volar();
	}
	static void w(PersonajeAccion personaje){
		personaje.actuar();
	}
	public static void main(String[] args) {
		Heroe miniheroe = new Heroe();
		t(miniheroe);
		u(miniheroe);
		v(miniheroe);
		w(miniheroe);
	}

}
