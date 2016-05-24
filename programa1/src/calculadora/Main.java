package calculadora;

public class Main {
	
	public static void main(String[] args){
		double resultado;
		Calulador_Consola c=new Calulador_Consola();
		while(!c.salir)
		{
			resultado= c.realizaOperacion();
			System.out.println("Resultado = "+resultado);
			
		}
		System.out.println("Finalizadas las operaciones");
	}

}
