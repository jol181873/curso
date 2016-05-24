package multihilo;

public class Cocinero implements Runnable {
	private String nombre;
	private Plato plato;
	private long tiempoInicial;
	
	public Cocinero(String nombre) {
		this.nombre=nombre;
	}
	
	public Cocinero(String nombre,Plato plato,long tiempoInicial) {
		this.nombre=nombre;
		this.plato=plato;
		this.tiempoInicial=tiempoInicial;
	}

	public void cocinar(Plato plato,long tiempoActual) {
		System.out.println(this.nombre+" empieza a cocinar el pedido de "+plato.getNombre()+" desde el segundo "+(System.currentTimeMillis()-tiempoActual)/1000);
		
		for (int i=0;i<plato.getIngredientes().length;i++){
			// delay generico para que dure al menos un segundo
			// entre ingrediente e ingrediente
			
			this.delaySeconds(1);
			System.out.println(this.nombre+" ha tardado en procesar el ingrediente "+plato.getIngredientes()[i]+" -> tiempo: "+(System.currentTimeMillis()-tiempoActual)/1000+" segundos");
		}
		
		System.out.println(this.nombre+" ha tardado en cocinar el plato "+plato.getNombre()+": "+(System.currentTimeMillis()-tiempoActual)/1000+" segundos");
	}

	private void delaySeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}		
	}

	@Override
	public void run() {
		cocinar(plato,tiempoInicial);		
	}
}
