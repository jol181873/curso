package mamiferos;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;


class HiloSwing extends Thread {
	private Tablero tablero;
	
	public HiloSwing(Tablero tablero) {
		super();
		this.tablero=tablero;	
	}
	public void run() {		
		while(true) {
			tablero.descenderSalud();
			tablero.mover();
			tablero.reproducir();
			tablero.comer();
			tablero.morir();
			tablero.dibujarSwing();
			try {
				Thread.sleep(1500);
			} catch (Exception ex) {
				
			}
		}
	}
}

class HiloConsola extends Thread {
	private Tablero tablero;
	
	public HiloConsola(Tablero tablero) {
		super();
		this.tablero=tablero;
	}
	public void run() {
		while(true) {
			tablero.mover();
			tablero.reproducir();
			tablero.comer();
			tablero.morir();
			tablero.pintarTabla();
			try {
				Thread.sleep(1500);
			} catch (Exception ex) {
				
			}
		}
	}
}

class Casilla {
	int i;
	int j;
	public Casilla(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
}

class Tablero extends JFrame {	
	Mamifero[][] tabla = new Mamifero[9][9];
	int[][] salud=new int[9][9];

  
	public static void main(String args[]){
	  Tablero tablero = new Tablero();
	  tablero.setLayout(new GridLayout(9, 9));
	  for (int i=0;i<81;i++) {
		  tablero.getContentPane().add(new JButton());
	  }
	  tablero.setSize(600, 600);
	  Perro perro = new Perro("macho");
	  Perro perro2 = new Perro("hembra");
	  Morsa morsa = new Morsa("macho");
	  Morsa morsa2 = new Morsa("hembra");
	  Murcielago mu1=new Murcielago("macho");
	  Murcielago mu2=new Murcielago("hembra");
	  
  	  tablero.tabla[0][0] = perro;
  	  tablero.tabla[4][0] = morsa;
  	  tablero.tabla[0][8] = perro2;
	  tablero.tabla[4][8] = morsa2;
	  tablero.tabla[8][0] = mu1;
	  tablero.tabla[8][8] = mu2;
	  
	  tablero.salud[0][0] = 200;
  	  tablero.salud[4][0] = 200;
  	  tablero.salud[0][8] = 200;
	  tablero.salud[4][8] = 200;
	  tablero.salud[8][0] = 200;
	  tablero.salud[8][8] = 200;
	  
  	  tablero.pintarTabla();
  	  tablero.mover();
  	  System.out.println();
  	  tablero.setVisible(true);
  	  
  	  HiloSwing hilo=new HiloSwing(tablero);
  	  hilo.start();
  	  
  	  //HiloConsola hiloConsola=new HiloConsola(tablero);
  	  //hiloConsola.start();
  }  
	
	public synchronized void descenderSalud() {
		for (int i=0;i<9;i++) {
			  for(int j=0;j<9;j++) {
				  salud[i][j]--;
			  }
		}
	}
   
	public synchronized void mover() {
		//Mamifero[][] tabla2=new Mamifero[9][9];
		
		for (int i=0;i<9;i++) {
		  for(int j=0;j<9;j++) {
			  if (tabla[i][j]!=null) {
				  // hay un bicho dentro
				  
				  ArrayList<Casilla> listaLibres=new ArrayList<Casilla>();
				  for (int a=-1;a<2;a++) {
					  for (int b=-1;b<2;b++) {
						  if (i+a>=0 && i+a<9 && j+b>=0 && j+b<9 && tabla[i+a][j+b]==null /*&& tabla2[i+a][j+b]==null*/) {
							  // no hay bicho
							  // y estamos dentro del tablero luego vale la casilla libre
							  
							  Casilla casillaLibre=new Casilla(a,b);								 
							  listaLibres.add(casillaLibre);							  
						  }
					  }
				  }
				  if (!listaLibres.isEmpty()) {
					  // hay alguna posicion adyacente libre
					  
					  Random rnd=new Random();
					  int aleatorio=rnd.nextInt(listaLibres.size());
					  Casilla destino=listaLibres.get(aleatorio);
					  
					  int destx=i+destino.i;
					  int desty=j+destino.j;
					  
					  tabla[destx][desty]=tabla[i][j];
					  salud[destx][desty]=salud[i][j];
					  tabla[i][j]=null;
					  salud[i][j]=0;
				  }
			  }
		  }
		  
		}
		//tabla=tabla2;	  
  }
	
	public synchronized void comer() {
		//Mamifero[][] tabla2=new Mamifero[9][9];
		
		for (int i=0;i<9;i++) {
		  for(int j=0;j<9;j++) {
			  if (tabla[i][j]!=null) {
				  // hay un bicho dentro
				  
				  ArrayList<Casilla> listaPresas=new ArrayList<Casilla>();
				  for (int a=-1;a<2;a++) {
					  for (int b=-1;b<2;b++) {
						  if (i+a>=0 && i+a<9 && j+b>=0 && j+b<9 && tabla[i+a][j+b]!=null && a!=0 && b!=0 && tabla[i][j].getClass()!=tabla[i+a][j+b].getClass()) {
							  // hay bicho
							  // y estamos dentro del tablero luego vale la casilla ocupada
							  // perro no come perro
							  
							  Casilla casillaOcupadaPresa=new Casilla(a,b);								 
							  listaPresas.add(casillaOcupadaPresa);							  
						  }
					  }
				  }
				  if (!listaPresas.isEmpty()) {
					  // hay alguna posicion adyacente con presa
					  
					  Random rnd=new Random();
					  int aleatorio=rnd.nextInt(listaPresas.size());
					  Casilla destino=listaPresas.get(aleatorio);
					  
					  int destx=i+destino.i;
					  int desty=j+destino.j;
					  
					  tabla[i][j].comer();
					  salud[i][j]++;
					  System.out.println(tabla[i][j]+" come a "+tabla[destx][desty]);
					  tabla[destx][desty]=null; // ha sido comida por (i,j)
					  salud[destx][desty]=0;
				  }
			  }
		  }
		  
		}
		//tabla=tabla2;	  
  }	
	  
  public void pintarTabla(){
	  for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			if(tabla[i][j]==null){
				System.out.print("  |");
			} else{
				char nombre = tabla[i][j].getNombreComun().charAt(0);
				char sexo = tabla[i][j].getSexo().charAt(0);
				System.out.print(String.valueOf(nombre)+String.valueOf(sexo)+"|");
			}
		}
		System.out.println();
	}
  }
  
  public synchronized void reproducir() {
	  for (int i=0;i<9;i++) {
		  for (int j=0;j<9;j++) {
			  if (tabla[i][j]!=null) {
				  ArrayList<Casilla> listaLibres=new ArrayList<Casilla>();
				  for (int a=-1;a<2;a++) {
					  for (int b=-1;b<2;b++) {
						  if (i+a>=0 && i+a<9 && j+b>=0 && j+b<9 && a!=0 && b!=0 && tabla[i+a][j+b]!=null) {
							  // hay una pareja de (i,j) en las cercanias
							  try{
								  Mamifero[] hijos=tabla[i][j].reproducir(tabla[i+a][j+b]);
								  
								  for (Mamifero m:hijos) {
									  boolean boo=false;
									  for (int x=0;x<9;x++) {
										  for (int y=0;y<9;y++) {
											  if (tabla[x][y]==null) {
												  tabla[x][y]=m;
												  salud[x][y]=200;
												  boo=true;
												  break;
											  }
										  }
										  if (boo) {
											  break;
										  }
									  }							
								  }
							  } catch (Exception ex) {
								  
							  }
						  }
					  }
				  }
			  }
		  }
	  }
  }
  
  public synchronized void morir() {
	  for (int i=0;i<9;i++) {
		  for (int j=0;j<9;j++) {
			  if (tabla[i][j]!=null && salud[i][j]<=0) {
				  tabla[i][j].morir();
				  tabla[i][j]=null;				  
			  } else {
				  salud[i][j]--;
			  }
		  }
	  }
  }

  public void dibujarSwing() {
	  for (int i=0;i<9;i++) {
		  for(int j=0;j<9;j++) {			  
			  JButton boton=(JButton) this.getContentPane().getComponent(i*9+j);
		  
			  if (tabla[i][j]==null){
				  boton.setText(" ");
			  } else {
				  char nombre = tabla[i][j].getNombreComun().charAt(0);
				  char sexo = tabla[i][j].getSexo().charAt(0);
				  boton.setText(String.valueOf(nombre)+String.valueOf(sexo));
			  }
		  }
	  }
	  this.repaint();
  }
  	  
  

}//end tablero
 
