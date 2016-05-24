package mamiferos;

public class Tester {

	public static void main(String[] args) {
		System.out.println("==Terrestres =======================================");
		System.out.println("Un@ Perr@:");
		Perro perro=new Perro();
		System.out.println(perro);
		
		System.out.println("Métodos perro:");
		Perro padre=new Perro("macho");
		padre.nacer();
		padre.crecer();
		padre.comer();
		padre.dormir();
		System.out.println("Creamos perro y perra, los juntamos y ...");
		Perro madre=new Perro("hembra");
		
		Perro[] camada=padre.reproducir(madre);		
		padre.morir();
		
		System.out.println("==Acuaticos ========================================");
		System.out.println("Métodos morsa:");
		Morsa morsa = new Morsa("hembra");
		Morsa morso = new Morsa("macho");
		morsa.nacer();
		morsa.bucear();
		morsa.comer();
		morsa.crecer();
		morsa.dormir();
		morsa.nadar();
		morsa.morir();
		System.out.println("Intentamos cruzar un perro y una morsa:");
		Perro[] camada2 = padre.reproducir(morsa);
		
		System.out.println("Intentamos cruzar una morsa macho y una perro hembra:");
		Morsa[] camada3 = morso.reproducir(madre);
		
		
		System.out.println("==Voladores ========================================");
		System.out.println("Intentamos cruzar un murciélago hembra y un perro hembra:");
		Murcielago murcielago=new Murcielago("hembra");		
		murcielago.reproducir(madre);
		
		System.out.println("Intentamos cruzar dos murciélagos hembra:");
		Murcielago murcielago2=new Murcielago("hembra");
		murcielago.reproducir(murcielago2);
		
		Ardilla_Voladora ardilla=new Ardilla_Voladora();
		ardilla.nacer();
		ardilla.comer();
		ardilla.crecer();
		ardilla.dormir();
		ardilla.volar();
		ardilla.planear();
		ardilla.morir();
	}

}
