package calculadora;

import java.util.Scanner;

public class Calulador_Consola {
	private double resultado =0;
	private int numeros=0;
	private Operacion op = new Operacion();
	protected boolean salir = false;
	protected boolean borrado= false;
	
	public Calulador_Consola(){}
	
	public String solicitarFuncion(){
		Scanner input = new Scanner(System.in);
		String funcion="";
		System.out.println("Escriba una función: ");
		funcion=input.nextLine();
		return funcion;
	}
	
	public double solicitarNumero(){
		Scanner input = new Scanner(System.in);
		String num="";
		System.out.println("Escriba un número o f para elegir una funcion: ");
		num=input.nextLine();
		
		if(((num.trim().charAt(0))=='o') || (num.trim().charAt(0)=='O')){
			salir=true;						
		} else if (num.trim().charAt(0)=='f' || num.trim().charAt(0)=='F') {
			// pedimos una funcion
			limpiar();
			System.out.println(
				"Todos los cálculos que empiecen despues de elegir la función hasta pulsar f en un operador, serviran como entrada a la función seleccionada.\nEl valor actual ha sido descartado");
			System.out.println("seno");
			System.out.println("coseno");
			System.out.println("tan");
			op.funcion=solicitarFuncion();
			
			return solicitarNumero();
		} else{
			if(Double.valueOf(num.trim())!=null){
				return Double.valueOf(num.trim());			
			}
			op.funcion="";
		}
		System.out.println("Error en número: "+num+" no reconocido");
		return 0.0;		
		
		
	}
	
	public char solicitarOperando(){
		Scanner input = new Scanner(System.in);
		char opera =0;
		System.out.println("Escriba un operando: [char]");
		if (!op.funcion.equals("")) {
			System.out.println("se aplicara "+op.funcion+" al pulsar 'f'"); 
		}
		opera = input.nextLine().charAt(0);
		if(op.isOperandoValido(opera)){
			if(opera=='o'||opera=='O'){
				salir=true;	
				return 0;
			}
			return opera;
		}
		else{
			System.out.println("Operación desconocida: "+opera);
			return 0;
		}			
	}
	
	public void limpiar() {
		op.error=false;
		op.resultado=0;
		op.numero=0;
		op.operando=0;
		op.funcion="";
	}
	
	public double realizaOperacion(){
		String operaciones ="";
		limpiar();
		
		while(op.operando != '='&& !salir){
			if(op.numero==0 && op.resultado==0){
				op.resultado = solicitarNumero();
				if(!salir){
					operaciones += op.resultado+" ";
				}
				op.operando=0;												
			} else{
				if(op.operando==0){
					op.operando = solicitarOperando();
					if(!op.isOperandoValido(op.operando)) {
						System.out.println("Operando no valido");
						op.operando=0;
					} else if (op.operando=='f') {
						switch (op.funcion) {
						case "coseno":
							System.out.println("cos("+op.resultado+")=");
							return Math.cos(op.resultado);
						case "seno":
							System.out.println("sin("+op.resultado+")=");
							return Math.sin(op.resultado);
						case "tan":
							System.out.println("tan("+op.resultado+")=");
							return Math.tan(op.resultado);
						}
						op.funcion="";
					
						return op.resultado;
					} else if(op.operando!='=') {
						operaciones += op.operando;
						op.numero=solicitarNumero();
						op.resultado= op.calcula(op);
						if(!salir){
							operaciones += op.numero+" = "+op.resultado;
							System.out.println(operaciones);
							operaciones = String.valueOf(op.resultado);
						}
					} else if(op.operando=='c') {
						limpiar();
						return op.resultado;
					} else {
						return op.resultado;
					}										
				}
			}						
		}
		return op.resultado;
	}
		
		
}

