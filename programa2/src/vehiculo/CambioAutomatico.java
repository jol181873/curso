package vehiculo;

public class CambioAutomatico implements CajaCambios {

	@Override
	public void meterMarcha() {
		System.out.println("Metiendo marcha");

	}

	@Override
	public void bajarMarcha() {
		System.out.println("Bajando marcha");

	}

	@Override
	public void puntoMuerto() {
		System.out.println("Punto muerto");

	}

}
