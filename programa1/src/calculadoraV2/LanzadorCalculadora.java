package calculadoraV2;

import java.util.ArrayList;
import java.util.Scanner;

public class LanzadorCalculadora {	
	private boolean stop=false;
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Operaciones> opCalc = new ArrayList<Operaciones>();
	
	public void lanzador() {
		double result;
		Suma suma= new Suma();
		Resta resta = new Resta();
		Division division=new Division();
		Coseno coseno = new Coseno();
		Sqrt sqrt=new Sqrt();
		Constante pi=new Constante("pi",Math.PI);
		Constante e=new Constante("e",Math.E);
		
		opCalc.add(suma);
		opCalc.add(resta);
		opCalc.add(division);
		
		opCalc.add(coseno);
		opCalc.add(sqrt);
		
		opCalc.add(pi);
		opCalc.add(e);
		
		System.out.println("Pulsa off para salir: ");
		
		while(!stop){			
			result = solicitaNumero();
			if(!stop){
				result= solicitaOperacion(result);
				System.out.println("El resultado es = "+result);
			}
		}
	}
			
	public static void main(String[] args) {
		LanzadorCalculadora lanzador=new LanzadorCalculadora();
		lanzador.lanzador();				
	}
	
	public double solicitaNumero(){
		String dato;
		System.out.println("inserte un numero: ");
		dato=sc.nextLine();
		if(dato.toUpperCase().equals("OFF")){
			stop=true;
			return 0;
		} else {
			for (Operaciones op:opCalc) {
				if (op instanceof Constante && op.getSimboloOperacion().toUpperCase().equals(dato.toUpperCase())) {
					return ((Constante) op).getValor();
				}
			}
			try{
				return Double.valueOf(dato);
				
			} catch(NumberFormatException e){
				System.out.println("Error en número ingresado");
				
				return solicitaNumero();
			}
		}		
	}
	
	/**
	 * Devuelve el resultado de la operacion
	 * @param num
	 * @return NaN si ha habido algun error (sqrt de un negativo o real/0 por ejemplo)
	 */
	public double solicitaOperacion(double num){
		String dato;
		System.out.println("Inserte operador");
		dato = sc.nextLine();
		double num2;
		if(dato.toUpperCase().equals("OFF")){
			stop=true;
			return 0;
		} else {
			for(int i=0;i<opCalc.size();i++){
				if ((dato.toUpperCase().equals(opCalc.get(i).getSimboloOperacion().toUpperCase()))||(dato.toUpperCase().equals(opCalc.get(i).getTipoOperacion().toUpperCase()))){
					if(!opCalc.get(i).isEspecial()){
						num2=solicitaNumero();
						if(!stop){
							double resultado= opCalc.get(i).realizaOperacion(num, num2);
							String error=opCalc.get(i).getError();
							if (error.equals("")) {
								// todo ha ido bien
								
								return resultado;
							} else if (resultado==Double.NaN) {
								System.out.println("ERROR desconocido");
								
								return Double.NaN;
							} else {
								System.out.println(error);
								
								return Double.NaN;
							}	
						} else {
							return 0;
						}
					} else {
						if (!stop){
							double resultado=opCalc.get(i).realizaOperacionEspecial(num);
							String error=opCalc.get(i).getError();
							if (error.equals("")) {
								// todo ha ido bien
								
								return resultado;
							} else if (resultado==Double.NaN) {
								System.out.println("ERROR desconocido");
								
								return Double.NaN;
							} else {
								System.out.println(error);
								
								return Double.NaN;
							}							 
						} else {
							return 0;
						}
					}
									
				}
			}
			
			System.out.println("Operador desconocido");
			return 0;		
		}
	}
}
