package vehiculo;

public class VehiculoBuilder {

	public static void main(String[] args) {
		Vehiculo vehiculo = new CocheDeportivo();
		CajaCambios cambio = new CambioAutomatico();

		VehiculoBuilder vehiculoBuilder = new VehiculoBuilder();
		vehiculoBuilder.construirVehiculoMotor(vehiculo, cambio);
	}

	public void construirVehiculoMotor(Vehiculo vehiculo, CajaCambios cambio) {
		cambio.puntoMuerto();
		cambio.meterMarcha();
		cambio.bajarMarcha();
		cambio.puntoMuerto();
		vehiculo.addCajaCambios(cambio);
	}
}
