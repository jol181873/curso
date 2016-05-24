package mamiferos;
import java.util.Random;

public class Acuatico extends Mamifero{

	private int aguante;
	
	public Acuatico(String nombreComun, String nombreCientifico, String sexo, String tipoAlimentacion, int aguante) {
		super(nombreComun, nombreCientifico, sexo, tipoAlimentacion);
		this.aguante = aguante;
		
	}
	
	public Acuatico(String nombreComun, String nombreCientifico, String tipoAlimentacion, int aguante) {
		super(nombreComun, nombreCientifico, tipoAlimentacion);
		this.aguante = aguante;
		
	}

	public int getAguante() {
		return aguante;
	}

	public void setAguante(int aguante) {
		this.aguante = aguante;
	}

	public void nadar(){
		System.out.println("¿qué le dice un pez a otro? nada");
	}
	
	public void bucear(){
		System.out.println("glú, glú, glú");
	}
	
	@Override
	public void nacer() {
		System.out.println("que viene, que viene");
	}

	@Override
	public void crecer() {
		System.out.println("creciendo voy");
	}

	@Override
	public void morir() {
		System.out.println("me morí");
	}

	@Override
	public void comer() {
		System.out.println("ñam ñam");
	}

	@Override
	public void dormir() {
		System.out.println("zzzzzzzzzzzzzzz");		
	}

	@Override
	public Acuatico[] reproducir(Mamifero madre) {
		System.out.println("Me estoy reproduciendo");
		int nHijos=(new Random()).nextInt(10)+1;
		System.out.println("He tenido "+nHijos+" descendientes");
		Acuatico[] camada=new Acuatico[nHijos];
		
		for (int i=0;i<nHijos;i++) {
			camada[i]=new Acuatico(getNombreComun(), getNombreCientifico(), getTipoAlimentacion(), getAguante());			
		}
		
		return camada;
	}

}
