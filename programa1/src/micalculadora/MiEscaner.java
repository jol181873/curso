package micalculadora;

import java.io.StringReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MiEscaner {
	private String cadenaOriginal;
	private String subcadenaRestante;
	private String subcadenaProcesada;
	private int pos;

	public MiEscaner(String cadena) {
		this.cadenaOriginal=cadena;
		this.subcadenaRestante=cadena;
		this.subcadenaProcesada="";
		
		this.pos=0;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getSubcadenaRestante() {
		return subcadenaRestante;
	}

	public void setSubcadenaRestante(String subcadenaRestante) {
		this.subcadenaRestante = subcadenaRestante;
	}

	public String getSubcadenaProcesada() {
		return subcadenaProcesada;
	}

	public void setSubcadenaProcesada(String subcadenaProcesada) {
		this.subcadenaProcesada = subcadenaProcesada;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public double nextDouble() {
		int signo=1;
		for (int i=pos;i<cadenaOriginal.length();i++) {
			if (cadenaOriginal.charAt(i)=='-') {
				pos=pos+1;
				signo=-signo;
			} else {
				break;
			}
		}
		StringTokenizer st=new StringTokenizer(subcadenaRestante,"*+/-");
		String str=st.nextToken();
			
		double resultado=Double.valueOf(str);
			
		if (st.hasMoreTokens()) {
			subcadenaRestante=st.nextToken("");
			pos=cadenaOriginal.indexOf(subcadenaRestante);
			subcadenaProcesada=cadenaOriginal.substring(0,pos);
		} else {
			subcadenaRestante="";
			pos=cadenaOriginal.length();
			subcadenaProcesada=cadenaOriginal;
		}
				
		return signo*resultado;
	}
	
	public String nextToken() {
		int signo=1;
		
		String op=String.valueOf(cadenaOriginal.charAt(pos));
		if ("*+/".contains(op) && !esUnariaMenos(pos, subcadenaRestante)) {			
			pos++;
			subcadenaProcesada=cadenaOriginal.substring(0,pos);
			subcadenaRestante=cadenaOriginal.substring(pos);
			
			return op;
		} else {
			// se intenta un token de este aspecto -sen-10.5 o pi
			
			for (int i=pos;i<cadenaOriginal.length();i++) {
				if (cadenaOriginal.charAt(i)=='-') {
					pos=pos+1;
					signo=-signo;
				} else {
					break;
				}
			}
		
			String resultado="";
			boolean primero=(signo>0);
			int pos2=pos;
			for (int i=pos;i<cadenaOriginal.length();i++) {
				String s=String.valueOf(cadenaOriginal.charAt(i));				
				if (Character.isAlphabetic(cadenaOriginal.charAt(i)) || Character.isDigit(cadenaOriginal.charAt(i)) || s.equals(".") || (s.equals("-") && esUnariaMenos(i, cadenaOriginal) && primero && Character.isDigit(cadenaOriginal.charAt(i+1))) || (i==pos2 && s.equals("-") && esUnariaMenos(i, cadenaOriginal) && primero)) {
					pos=pos+1;
					resultado=resultado+s;
					if (cadenaOriginal.charAt(i)=='-') {
						primero=false;
					}
					
				} else {
					break;
				}
			}
										
			subcadenaProcesada=cadenaOriginal.substring(0,pos);
			subcadenaRestante=cadenaOriginal.substring(pos);
			
			if (signo==-1) {
				return "-"+resultado;
			} else {
				return resultado;
			}
		}
	}	
	
	public String nextFuncion() {
		String resultado="";
		int pos2=pos;
		for(int i=pos;i<cadenaOriginal.length();i++) {
			String s=String.valueOf(cadenaOriginal.charAt(i));
			if (Character.isAlphabetic(cadenaOriginal.charAt(i)) || (cadenaOriginal.charAt(i)=='-' && i==pos2)) {
				resultado=resultado+s;
				pos++;
			}
		}
		
		subcadenaProcesada=cadenaOriginal.substring(0,pos);
		subcadenaRestante=cadenaOriginal.substring(pos);
		
		return resultado;
	}
	
	public String nextOperador() {
		String op=String.valueOf(cadenaOriginal.charAt(pos));
		if ("+*/".contains(op)) {
			pos++;
		
			subcadenaProcesada=cadenaOriginal.substring(0,pos);
			subcadenaRestante=cadenaOriginal.substring(pos);
		
			return op;
		} else if(op.equals("-")) {
			return "+";
		} else {
			return op;
		}
	}
	
	public boolean hayNext() {
		if (pos==cadenaOriginal.length()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hayNextDouble() {
		try {
			MiEscaner mi2=new MiEscaner(subcadenaRestante);
			mi2.nextDouble();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean hayNextToken() {
		try {
			MiEscaner mi2=new MiEscaner(subcadenaRestante);
			mi2.nextToken();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}	
	
	public boolean hayNextOperador() {
		try {
			MiEscaner mi2=new MiEscaner(subcadenaRestante);
			mi2.nextOperador();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	private boolean esUnariaMenos(int pos,String cadena) {
		if (pos==0 || Character.isAlphabetic(cadena.charAt(pos-1)) || (pos!=cadena.length() && ("*+/".contains(String.valueOf(subcadenaRestante.charAt(pos+1))) || (Character.isAlphabetic(cadena.charAt(pos+1)) || cadena.charAt(pos+1)=='-')))) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Introduzca una expresion: ");
		Scanner sc=new Scanner(System.in);
		String cadena=sc.nextLine();
		MiEscaner mi=new MiEscaner(cadena);
		System.out.println("Cadena            : "+mi.getCadenaOriginal());
		//System.out.println("Primer real       : "+mi.nextDouble());
		//System.out.println("Se ha procesado   : "+mi.getSubcadenaProcesada());
		//System.out.println("Queda por procesar: "+mi.getSubcadenaRestante());
		System.out.println("===========================");
		System.out.println("Next operador     : "+mi.nextToken());
		System.out.println("Se ha procesado   : "+mi.getSubcadenaProcesada());
		System.out.println("Queda por procesar: "+mi.getSubcadenaRestante());
	}

}
