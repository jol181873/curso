package biblioteca;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utilidades {	
	public static double getDoublePositivo() {
		Scanner sc=new Scanner(System.in); 
		
		try {
			double real=sc.nextDouble();	
			if (real<0) {
				throw new Exception();
			}
			return real;
		} catch (Exception ex) {
			System.out.println("Número no válido");
			return -1;
		}
	}
	
	public static long getLongPositivo() {
		Scanner sc=new Scanner(System.in); 
		
		try {
			long entero=sc.nextLong();
			if (entero<0) {
				throw new Exception();
			}
			return entero;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Número no válido");
			return -1;
		}
	}
	
	public static String getString() {
		Scanner sc=new Scanner(System.in); 
				
		String cadena=sc.nextLine();
		return cadena;		
	}
	
	public static void clrScr() {
		for (int i=0;i<20;i++) {
			System.out.println();
		}
	}
	
	public static String getFecha(Date fecha) {
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fecha);
	}
	
	public static String getMd5(String otro) {
		try {
			MessageDigest message=MessageDigest.getInstance("md5");
			byte[] bytes=message.digest(otro.getBytes());
			return new String(bytes);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
