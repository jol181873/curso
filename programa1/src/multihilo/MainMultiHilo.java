package multihilo;

public class MainMultiHilo {
	public static void main(String[] args) {
		Plato plato1=new Plato("garbanzos",new String[]{"arroz","garbanzos","chorizo","cebolla","pimenton"});
		Plato plato2=new Plato("pasta",new String[]{"macarrones","queso","tomate","carne picada"});
		
		long comienzaACocinar=System.currentTimeMillis();
		
		Cocinero juan=new Cocinero("Juan",plato1,comienzaACocinar);
		Cocinero paco=new Cocinero("Paco",plato2,comienzaACocinar);
		
		// simil tenemos dos cocinas con utensilios para que los dos trabajen
		Thread hilo1=new Thread(juan);
		Thread hilo2=new Thread(paco); 
		
		hilo1.start();
		hilo2.start();	
	}
}
