package multihilo;

public class Main {

	public static void main(String[] args) {
		Plato plato1=new Plato("garbanzos",new String[]{"arroz","garbanzos","chorizo","cebolla","pimenton"});
		Plato plato2=new Plato("pasta",new String[]{"macarrones","queso","tomate","carne picada"});
		
		Cocinero juan=new Cocinero("Juan");
		Cocinero paco=new Cocinero("Paco");
		
		long comienzaACocinar=System.currentTimeMillis();
		
		// simil es que solo tenemos cocina y utensilios para que trabaje uno
		juan.cocinar(plato1, comienzaACocinar);
		paco.cocinar(plato2, comienzaACocinar);
	}

}
