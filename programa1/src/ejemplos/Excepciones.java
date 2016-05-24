package ejemplos;

import java.util.Scanner;


class MiExcepcion1 extends Exception {

	public MiExcepcion1() {
		super();		
	}

	public MiExcepcion1(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);		
	}

	public MiExcepcion1(String arg0, Throwable arg1) {
		super(arg0, arg1);		
	}

	public MiExcepcion1(String message) {
		super(message);		
	}

	public MiExcepcion1(Throwable cause) {
		super(cause);		
	}
	
}

class MiExcepcion2 extends Exception {

	public MiExcepcion2() {
		super();		
	}

	public MiExcepcion2(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public MiExcepcion2(String message, Throwable cause) {
		super(message, cause);		
	}

	public MiExcepcion2(String message) {
		super(message);		
	}

	public MiExcepcion2(Throwable cause) {
		super(cause);		
	}
	
}

class MiExcepcion3 extends Exception {

	public MiExcepcion3() {
		super();		
	}

	public MiExcepcion3(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public MiExcepcion3(String message, Throwable cause) {
		super(message, cause);		
	}

	public MiExcepcion3(String message) {
		super(message);		
	}

	public MiExcepcion3(Throwable cause) {
		super(cause);		
	}
	
}

class MiOtraExcepcion extends Exception {

	public MiOtraExcepcion() {
		super();		
	}

	public MiOtraExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public MiOtraExcepcion(String message, Throwable cause) {
		super(message, cause);		
	}

	public MiOtraExcepcion(String message) {
		super(message);		
	}

	public MiOtraExcepcion(Throwable cause) {
		super(cause);		
	}
	
}

public class Excepciones {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Introduzca un número: ");
		int i=sc.nextInt();
		try { 
			switch (i) {
				case 1: throw new MiExcepcion1("Ha generado la excepcion 1, disfrútela!");
				case 2: throw new MiExcepcion2("Ha generado la excepcion 2, disfrútela!");
				case 3: throw new MiExcepcion3("Ha generado la excepcion 3, disfrútela!");
				default: throw new MiOtraExcepcion("Ha generado la 'otra' excepcion, disfrútela!");			
			}
		} catch (MiExcepcion1 ex) {
			System.out.println(ex.getMessage());
		} catch (MiExcepcion2 ex) {
			System.out.println(ex.getMessage());			
		} catch (MiExcepcion3 ex) {
			System.out.println(ex.getMessage());			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
