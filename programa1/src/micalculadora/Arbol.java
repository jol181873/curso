package micalculadora;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Arbol {
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
	private Hoja procesar(String expresion) throws Exception {	
		MiEscaner mi=new MiEscaner(expresion);
		
		Hoja izquierda=null;
		Hoja derecha=null;	
	
		double izquierdaDouble=Double.NaN;
		double derechaDouble=Double.NaN;
		String op=null;
		
		if (!mi.hayNextToken()) throw new Exception("Fin de expresion inesperado");
		
		if (mi.hayNextDouble()) {
			izquierdaDouble=mi.nextDouble();
			izquierda=new HojaValor(izquierdaDouble);
			
			if (mi.hayNextOperador()) {
				op=mi.nextOperador();					
				derecha=procesar(mi.getSubcadenaRestante());				
			} else {
				// es un numero real y no se puede procesar nada mas
				
				return izquierda;
			}
		} else {
			// caso 2: izquierda no es un numero: sen-10+5-3
					
			String izquierdaString=mi.nextToken(); // sen-10			
			MiEscaner mi2=new MiEscaner(izquierdaString);
			String funcionOConstante=mi2.nextFuncion(); //sen
			
			if (mi2.hayNextDouble()) {				
				double centro=mi2.nextDouble(); //-10
				
				int negativo=1;
				if (funcionOConstante.charAt(0)=='-') {
					negativo=-1;
					funcionOConstante=funcionOConstante.substring(1);
				}
				switch (funcionOConstante) {
					case "sin":
					case "sen": izquierda=new OpUnaria(new HojaValor(negativo*Math.sin(centro)), funcionOConstante, Integer.MAX_VALUE);
								break;					
					case "cos": izquierda=new OpUnaria(new HojaValor(negativo*Math.cos(centro)), funcionOConstante, Integer.MAX_VALUE);
								break;					
					case "tan":
					case "tg" : izquierda=new OpUnaria(new HojaValor(negativo*Math.tan(centro)), funcionOConstante, Integer.MAX_VALUE);
								break;					
					case "log": izquierda=new OpUnaria(new HojaValor(negativo*Math.log(centro)), funcionOConstante, Integer.MAX_VALUE);
								break;					
					case "exp": izquierda=new OpUnaria(new HojaValor(negativo*Math.exp(centro)), funcionOConstante, Integer.MAX_VALUE);
								break;
					default:throw new Exception("No entiendo nada, la funcion no forma parte de mi \"enorme\" repertorio, o Jol me ha programado mal :-)");
				}			
			} else {
				// es una constante textual
				
				int negativo=1;
				if (funcionOConstante.charAt(0)=='-') {
					negativo=-1;
					funcionOConstante=funcionOConstante.substring(1);
				}
				if (hashConstantes.containsKey(funcionOConstante)) {					
					izquierda=new OpUnaria(new HojaValor(negativo*hashConstantes.get(funcionOConstante)),funcionOConstante,prioridad);
				} else {
					throw new Exception("No entiendo nada, la constante no forma parte de mi \"enorme\" repertorio (pi y e), está usando (o es un resultado intermedio de un cálculo) notacion exponencial (por ej: 3.451E-5) o Jorge me ha programado mal :-)");
				}								
			}
			
			if (mi.hayNextOperador()) {
				op=mi.nextOperador();					
				derecha=procesar(mi.getSubcadenaRestante());				
			} else {				
				return izquierda;
			}
		}
																		
		OpBinaria resultado=null;
		if (op==null) {
			return izquierda;
		} else if (op.equals("*")) {
			// la operacion tiene prioridad
			
			prioridad++;
			if (izquierdaDouble==Double.NaN) { 
				izquierda=new HojaValor((new Producto()).getElementoNeutro());
			}
			if (derechaDouble==Double.NaN) { 
				derecha=new HojaValor((new Producto()).getElementoNeutro());
			}
			
			resultado=new Producto(izquierda,derecha,prioridad);			
		} else if (op.equals("/")) {
			// la operacion tiene prioridad
			
			prioridad++;
			if (izquierdaDouble==Double.NaN) { 
				izquierda=new HojaValor((new Division()).getElementoNeutro());
			}
			if (derechaDouble==Double.NaN) { 
				derecha=new HojaValor((new Division()).getElementoNeutro());
			}
			
			resultado=new Division(izquierda,derecha,prioridad);			
		} else if (op.equals("+")) {
			if (izquierdaDouble==Double.NaN) { 
				izquierda=new HojaValor((new Suma()).getElementoNeutro());
			}
			if (derechaDouble==Double.NaN) { 
				derecha=new HojaValor((new Suma()).getElementoNeutro());
			}
			
			resultado=new Suma(izquierda,derecha,prioridad);
		} else if (op.equals("-")) { 
			// NO SE USA, NO SE CONTEMPLA LA OPERACION RESTA
			// LA DEJO NO VAYA A SE QUE, SI LA QUITO, FALLE TODO
			
			if (izquierdaDouble==Double.NaN) { 
				izquierda=new HojaValor((new Suma()).getElementoNeutro());
			}
			if (derechaDouble==Double.NaN) { 
				derecha=new HojaValor((new Suma()).getElementoNeutro());
			}
			
			resultado=new Resta(izquierda,derecha,prioridad);
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
	public Arbol(String expresion) throws Exception {
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
	public static double calcular(String expresion) throws Exception {						
		int ultimoAbierto=expresion.lastIndexOf('(');
		int primerCerrado=expresion.indexOf(')',ultimoAbierto);
		
		double resultadoSubArbol=Double.NaN;
		if (ultimoAbierto==-1) {
			// no hay parentesis
			Arbol subarbol=new Arbol(expresion);
			resultadoSubArbol=subarbol.calcularTodoElArbol();
			return resultadoSubArbol;
		} else {
			// calculo el valor del parentesis
			String subexpresion=expresion.substring(ultimoAbierto+1, primerCerrado);
			Arbol subarbol=new Arbol(subexpresion);
			resultadoSubArbol=subarbol.calcularTodoElArbol();
			
			// creo un string con el valor del parentesis sustituido por el double calculado								
			String resultado=expresion.substring(0,ultimoAbierto)+resultadoSubArbol+expresion.substring(primerCerrado+1);										
			return calcular(resultado);
		}			
	}
	
	public static void main(String[] args) throws Exception {				
		while (true) {
			System.out.println("Introduzca una expresion matemática, pulse return y buena suerte: ");
			Scanner sc=new Scanner(System.in);
			String expresion=sc.nextLine();
			
			try {
				System.out.println(Arbol.calcular(expresion));
			} catch (StringIndexOutOfBoundsException ex0) {
				System.out.println("Ha introducido una expresion mal formada, repase su ortografía, por favor");
			} catch (Exception ex) {
				System.out.println("Se ha producido un error inevitable. El texto del mismo es:");
				System.out.println(ex.getMessage());
			}
		}
	}
}
