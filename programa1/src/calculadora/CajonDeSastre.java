package calculadora;

import java.util.ArrayList;

import ejemplos.Coche;
import ejemplos.Rectangulo;
import ejemplos.Tren;
import ejemplos.Vehiculo_Motor;
import mamiferos.Perro;

public class CajonDeSastre {
	public static void main(String[] args) {
		ArrayList<Object> almacen=new ArrayList<Object>();
		
		Perro p=new Perro("macho");
		Rectangulo r=new Rectangulo(0, 0, 10, 20);
		Coche c=new Coche(0, 0.0, 150.0, 4, 5, 5, "gasolina");
		Tren tren=new Tren(0, 0, 0, 100, 100, 500, 0, 23, "tren");
		
		almacen.add(p);
		almacen.add(r);
		almacen.add(c);
		almacen.add(tren);
		
		for(int i=0;i<almacen.size();i++){
			if (almacen.get(i) instanceof Vehiculo_Motor) {
				System.out.println("El valor "+i+" del arraylist es un vehiculo a motor");
				if (almacen.get(i) instanceof Coche) {
					System.out.println("es un coche");
					Coche miCoche=(Coche) almacen.get(i);
					System.out.println("El coche tiene "+miCoche.getNumeroAsientos()+" asientos");
				} else if (almacen.get(i) instanceof Tren) {
					System.out.println("es un tren");
					Tren miTren=(Tren) almacen.get(i);
					System.out.println("El tren va a "+miTren.verVelocidad()+" km/h");
				}
			} else if (almacen.get(i) instanceof Perro) {
				Perro miPerro=(Perro) almacen.get(i);
				System.out.println("El valor "+i+" es "+miPerro.getNombreCientifico());
				
			} else if (almacen.get(i) instanceof Rectangulo) {
				Rectangulo miRectangulo=(Rectangulo) almacen.get(i);
				System.out.println("El valor "+i+" es un rectangulo de area"+miRectangulo.area());
			}
		}
	}

}
