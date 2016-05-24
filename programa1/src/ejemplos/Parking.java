package ejemplos;

public class Parking {
	String nombreAparcaCoches;
	int plazasAparcamiento;
	
	public Parking(String n,int p) {
		this.nombreAparcaCoches=n;
		this.plazasAparcamiento=p;
	}
	
	public void aparcar(Vehiculo_Motor v) {
		System.out.println(this.nombreAparcaCoches+" aparca un/una "+v.tipo_vehiculo);
		
	}
	
	public void conducirVehiculo(Vehiculo_Motor v) {
		System.out.println(this.nombreAparcaCoches+" conduce un/una "+v.tipo_vehiculo);
		v.conducir();
		System.out.println();
	}
	
	public static void main(String[] args) {
		Parking parking=new Parking("Pedro", 500);
		
		Submarino submarino=new Submarino(10000, 200, 300, "submarino", 10000, "ruso", 50, false);		
		Trailer trailer=new Trailer(200, 50, 100, "trailer", 8, 100, 200000, 50);
		Coche coche=new Coche(1000, 150, 2000, 4, 4, 5, "gasolina");
		Tranvia tranvia=new Tranvia(1000,120,100,"tranvia",1,2,3,4,5,6);
		Tren tren=new Tren(1, 1, 100, 200, 1000, 100, 8, 23, "tren");
		vehiculo_Especial vehiculoEspecial=new vehiculo_Especial(100, 200, 100, "cortacesped", "sss");
		Bus bus=new Bus(100,100,100,"Bus",1,1,"marca","modelo","linea",true);
		Avion avion=new Avion(100,100,100,"Avion","","","",1,1,1);
		
		parking.conducirVehiculo(submarino);
		
		//trailer.encenderVehiculo();
		parking.conducirVehiculo(trailer);
		
		parking.conducirVehiculo(coche);
		
		parking.conducirVehiculo(tranvia);
		
		parking.conducirVehiculo(tren);
		
		parking.conducirVehiculo(vehiculoEspecial);
		
		parking.conducirVehiculo(bus);
		
		parking.conducirVehiculo(avion);
	}
}
