package ejemplos;

public class Mito {
	private int valor;
	
	public static void metodoReferencia1(Mito o) {
		o.valor=3;		
	}
	
	public static void metodoReferencia2(Mito o) {
		Mito otraO=new Mito();
		o=otraO;
		o.valor=3;
		
		System.out.println("Aquí viene metodoReferencia2: ");
		System.out.println("-------------------------------");
	}
	
	public static void metodoReferencia3(Mito o1,Mito o2){
		Mito otraO=new Mito();
		otraO=o1;
		o1.valor=56;
		System.out.println("Aquí viene metodoReferencia3: ");
		System.out.println("-------------------------------");
		System.out.println("otraO vale: "+otraO.valor);
		System.out.println("o1 vale   : "+o1.valor);
		o1=o2; // no le importa el de fuera
		o2=otraO;
	}

	public static void main(String[] args) {
		System.out.println("Desmitificando parámetros por referencia");
		Mito miObjeto=new Mito();
		miObjeto.valor=2;
		System.out.println("El valor inicial de mi objeto es: "+miObjeto.valor);
		metodoReferencia1(miObjeto);
		System.out.println("El valor de miObjeto es: "+miObjeto.valor);
		miObjeto.valor=1;
		System.out.println("El valor de miObjeto es: "+miObjeto.valor);
		metodoReferencia2(miObjeto);
		System.out.println("El valor de miObjeto es: "+miObjeto.valor);
		
		
		Mito miObjeto2=new Mito();
		miObjeto2.valor=5;
		metodoReferencia3(miObjeto, miObjeto2);
		System.out.println("miObjeto actualizado (valor=1) su valor ahora es: "+miObjeto.valor);
		System.out.println("miObjeto secundario (valor=5) su valor ahora es: "+miObjeto2.valor);
		
	}

}
