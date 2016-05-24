package micalculadora;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Arbol2 {
	Hoja raiz;
	
	private HashMap<String,Double> hashConstantes;
	
	/**
	 * Mirar la supresion de esta variable, es de uso interno de un metodo
	 */
	private int pos=0;
	
	/**
	 * Mirar la supresion de esta variable, es de uso interno de un metodo
	 */
	private int prioridad=0;
	
	/**
	 * Metodo que despedaza un String 
	 * TODO mejorar de alguna manera, ¿alguna utilidad de Java hace lo que hace este metodo?
	 * 
	 * @param expresion el String a despedazar
	 * @return el primer token encontrado
	 */
	private String procesarTexto(String expresion) {
		boolean negativo=false;
		for (int i=pos;i<expresion.length();i++) {
			if (expresion.charAt(i)=='-') {
				pos=pos+1;
				negativo=!negativo;
			} else {
				break;
			}
		}
		
		String resultado="";
		boolean esToken=false;
		for (int i=pos;i<expresion.length();i++) {
			pos=i;
			if (!Character.isDigit(expresion.charAt(i)) && !Character.isAlphabetic(expresion.charAt(i)) && expresion.charAt(i)!='.') {	
				// el siguiente caracter es un operador (TODO :supuestamente)
				
				break;
			} else {
				// se esta ante un numero o una palabra reservada				
				
				resultado=resultado+Character.valueOf(expresion.charAt(i)).toString();
			}
		}
		if (negativo) {
			try {
				Double.valueOf(resultado);
				return "-"+resultado;
			} catch (Exception ex) {
				// no es un numero
				return resultado;
			}			
		} else {
			return resultado;
		}		
	}		
		
	/**
	 * Procesa un String y obtiene la estructura de arbol binario que lo representa
	 * Esta representacion es erronea, en el sentido de que no tiene en cuenta las prioridades
	 * de las operaciones ni parentesis.
	 * Dichas prioridades se miran get(): el calculo efectivo de la expresion (caso de las operaciones)
	 * y, recursivamente, en caso de parentesis anidados 
	 * 
	 * TODO :mejorar
	 * 
	 * @param expresion el String con la expresion matematica a evaluar
	 * @return la estructura (posiblemente erronea) de arbol binario que la representa
	 */
	private Hoja procesar(String expresion) {	
		pos=0;
		String izquierdaString=procesarTexto(expresion);
		String derechaString=expresion.substring(pos+1);
		String operacionString=Character.valueOf(expresion.charAt(pos)).toString();
		if (!"+*/".contains(operacionString) && !Character.isDigit(operacionString.charAt(0))) {
			// no entiendo la operacion,
			// izquierda debe ser una palabra reservada
			if (hashConstantes.containsKey(izquierdaString)) {					
				return new OpUnaria(new HojaValor(hashConstantes.get(izquierdaString)),izquierdaString,prioridad);
			} 
		}
		
		Hoja izquierda=null;
		Hoja derecha=null;	
		
		if (!derechaString.equals("")) {					
			try {			
				if (operacionString.equals("-")) {
					derecha=new HojaValor(-Double.valueOf(derechaString));
					operacionString="+";
				} else {
					derecha=new HojaValor(Double.valueOf(derechaString));
				}
			} catch (Exception ex) {
				if (operacionString.equals("-")) {
					derechaString="-"+derechaString;
					operacionString="+";
					derecha=procesar(derechaString);
				} else {
					derecha=procesar(derechaString);
				}
			}		
		}
						
		if (!izquierdaString.equals("")) {
			try {		
				izquierda=new HojaValor(Double.valueOf(izquierdaString));
			} catch (Exception ex) {
				StringTokenizer tk=new StringTokenizer(izquierdaString);
				String palabro=tk.nextToken("01234567890.-");
				String numero=tk.nextToken("");
											
				switch (palabro) {
					case "sen":izquierda=new OpUnaria(new HojaValor(Math.sin(Double.valueOf(numero))),"sen",prioridad);
								break;
					case "cos":izquierda=new OpUnaria(new HojaValor(Math.cos(Double.valueOf(numero))),"cos",prioridad);
								break;
					default :izquierda=procesar(izquierdaString);
								break;
				}			
			}									
		}
						
		OpBinaria resultado=null;
		if (operacionString.equals("*")) {
			// la operacion tiene prioridad	
			prioridad++;
			if (izquierdaString.equals("")) { 
				izquierda=new HojaValor((new Producto()).getElementoNeutro());
			}
			if (derechaString.equals("")) { 
				derecha=new HojaValor((new Producto()).getElementoNeutro());
			}
			
			resultado=new Producto(izquierda,derecha,prioridad);			
		} else if (operacionString.equals("/")) {
			// la operacion tiene prioridad
			prioridad++;
			if (izquierdaString.equals("")) { 
				izquierda=new HojaValor((new Division()).getElementoNeutro());
			}
			if (derechaString.equals("")) { 
				derecha=new HojaValor((new Division()).getElementoNeutro());
			}
			
			resultado=new Division(izquierda,derecha,prioridad);			
		} else {
			if (izquierdaString.equals("")) { 
				izquierda=new HojaValor((new Suma()).getElementoNeutro());
			}
			if (derechaString.equals("")) { 
				derecha=new HojaValor((new Suma()).getElementoNeutro());
			}
			
			resultado=new Suma(izquierda,derecha,prioridad);
		}
								
		return resultado;
	}
	
		
	/**
	 * Calcular el valor real de la expresion
	 * 
	 * @return el valor de la expresion con la que se ha creado el objeto
	 */
	public double calcularTodoElArbol() {
		return raiz.get();		
	}
	
	/**
	 * Constructor, llama a procesar() para obtener la estructura de arbol
	 * El no admitir parentesis, es una invitacion a hacer este constructor privado, ya que
	 * solo funcionara en expresiones simples.
	 * Para el calculo con parentesis utilizar calcular(String) 
	 * 
	 * @param expresion la expresion matematica a calcular, NO ADMITE PARENTESIS
	 */
	public Arbol2(String expresion) {
		hashConstantes=new HashMap<String,Double>();
		hashConstantes.put("pi", Math.PI);
		hashConstantes.put("e", Math.E);
		
		raiz=procesar(expresion);
	}
	
	/**
	 * Metodo estatico para calcular el valor de una expresion matematica posiblemente CON PARENTESIS
	 *  
	 * @param expresion la expresion matematica a calcular, ADMITE PARENTESIS
	 */
	public static double calcular(String expresion) {						
		int ultimoAbierto=expresion.lastIndexOf('(');
		int primerCerrado=expresion.indexOf(')',ultimoAbierto);
		
		double resultadoSubArbol=Double.NaN;
		if (ultimoAbierto==-1) {
			// no hay parentesis
			Arbol2 subarbol=new Arbol2(expresion);
			resultadoSubArbol=subarbol.calcularTodoElArbol();
			return resultadoSubArbol;
		} else {
			// calculo el valor del parentesis
			String subexpresion=expresion.substring(ultimoAbierto+1, primerCerrado);
			Arbol2 subarbol=new Arbol2(subexpresion);
			resultadoSubArbol=subarbol.calcularTodoElArbol();
			
			// creo un string con el valor del parentesis sustituido por el double calculado								
			String resultado=expresion.substring(0,ultimoAbierto)+resultadoSubArbol+expresion.substring(primerCerrado+1);										
			return calcular(resultado);
		}			
	}
	
	public static void main(String[] args) {
		/*System.out.println("1+2*3-4");
		Arbol arbol=new Arbol("1+2*3-4");
		System.out.println(arbol.raiz);		
		System.out.println(arbol.calcularTodoElArbol());
		
		System.out.println("2*3+4*3+1=19");
		arbol=new Arbol("2*3+4*3+1");
		System.out.println(arbol.raiz);		
		System.out.println(arbol.calcularTodoElArbol());*/	
		
		while (true) {
			Scanner sc=new Scanner(System.in);
			String expresion=sc.nextLine();
			//Arbol arbol=new Arbol(expresion);
			
			//System.out.println(arbol.raiz);		
			//System.out.println(arbol.calcularTodoElArbol());
			
			//Arbol arbol=new Arbol(expresion);
			//System.out.println(arbol.raiz);
			//System.out.println("1+(5*3-4*(2+5))");
			System.out.println(Arbol2.calcular(expresion));
		}
	}
}
