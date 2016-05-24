package micalculadora;

import java.util.Random;

public class MatricesMultiHilo {
	public static void main(String[] args) {
		int filas=200;
		int columnas=200;
		
		final Matriz resultado=new Matriz(filas,columnas,null);
		final Matriz resultado2=new Matriz(filas,columnas,null);								
		Matriz m1=new Matriz(filas, columnas,null);
		Matriz m2=new Matriz(filas, columnas,null);
		
		final long millis=System.currentTimeMillis();
		resultado.setCallBack(
			new Runnable() {		
				@Override
				public void run() {
					System.out.println(resultado);
					System.out.print("Tiempo multiplicacion normal: ");
					System.out.println(System.currentTimeMillis()-millis);				
				}
			}
		);
		m1.multiplica(m2,resultado);
		
																
		final long millis2=System.currentTimeMillis();
		resultado2.setCallBack(
				new Runnable() {		
					@Override
					public void run() {
						System.out.println(resultado2);
						System.out.print("Tiempo multiplicacion normal: ");
						System.out.println(System.currentTimeMillis()-millis2);
						
						System.out.println("¿Ambas matrices son iguales?");
						if (resultado.iguales(resultado2)) {
							System.out.println("sí");
						} else {
							System.out.println("no");
						}
					}
				}
		);
		m1.multiplicaMultiHilo(m2,resultado2);												
	}
}

class Matriz {
	private double[][] tabla;
	private int filas;
	private int columnas;
	private int completado;
	Runnable callback;
		
	public Matriz(int filas,int columnas,Runnable callback) {
		this.filas=filas;
		this.columnas=columnas;
		tabla=new double[filas][columnas];
		for (int i=0;i<filas;i++) {
			for (int j=0;j<columnas;j++) {
				tabla[i][j]=(new Random()).nextInt(10);
			}
		}
		this.completado=0;
		this.callback=callback;
	}


	public double[][] getTabla() {
		return tabla;
	}


	public void setTabla(double[][] tabla) {
		this.tabla = tabla;
	}


	public int getFilas() {
		return filas;
	}


	public void setFilas(int filas) {
		this.filas = filas;
	}


	public int getColumnas() {
		return columnas;
	}


	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	public double get(int i,int j) {
		return tabla[i][j];
	}
	
	public synchronized void set(int i,int j,double valor) {
		tabla[i][j]=valor;
		
		completado++;
		if (completado==filas*columnas) {		
			callback.run();
		}
	}
	
	public String toString() {
		String resultado="";
		for (int i=0;i<filas;i++) {
			for (int j=0;j<columnas;j++) {
				resultado=resultado+"  "+tabla[i][j]+"|  ";
			}
			resultado=resultado+"\n";
		}
		
		return resultado;
	}
	
	public double multiplica(Matriz m2,int fila,int columna) {
		double resultado=0;
		for (int i=0;i<columnas;i++) {
				resultado=resultado+tabla[fila][i]*m2.tabla[i][columna];
		}
		
		return resultado;
	}
	
	public Matriz multiplica(Matriz m2,Matriz resultado) {			
		for (int i=0;i<filas;i++) {
			for (int j=0;j<m2.columnas;j++) {
				resultado.tabla[i][j]=multiplica(m2,i,j);
			}
		}

		resultado.callback.run();
		return resultado;
	}
	
	public Matriz multiplicaMultiHilo(Matriz m2,Matriz resultado) {		
		for (int i=0;i<filas;i++) {
			for (int j=0;j<m2.columnas;j++) {
				Hilo hilo=new Hilo(this, m2, resultado, i, j);
				hilo.start();
			}
		}
				
		/* espera a que temrinen todos los hilos
		while(resultado.completado!=resultado.filas*resultado.columnas) {
			try {
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		return resultado;
	}		
		
	public void setCallBack(Runnable callback) {
		this.callback=callback;
	}
	
	public boolean iguales(Matriz otra) {
		if (filas!=otra.filas || columnas!=otra.columnas) {
			return false;
		}
		
		for (int i=0;i<filas;i++) {
			for (int j=0;j<otra.columnas;j++) {
				if (Math.abs(tabla[i][j]-otra.tabla[i][j])>0.001) {					
					return false;
				}
			}
		}
		return true;
	}

}

class Hilo extends Thread {
	Matriz m1;
	Matriz m2;
	Matriz resultado;
	int i;
	int j;
	
	public Hilo(Matriz m1,Matriz m2,Matriz resultado,int i,int j) {
		this.m1=m1;
		this.m2=m2;
		this.resultado=resultado;
		this.i=i;
		this.j=j;
	}
	
	public void run() {
		double res=m1.multiplica(m2,i,j);
		
		resultado.set(i,j,res);
	}
}
