package calculadora;

public class Operacion {
	public double numero=0;
	public double resultado=0;
	public char operando = 0;
	public boolean error= false;
	public String funcion="";
	
	public Operacion(){}
	
	public boolean isOperandoValido(char operando){
		
		switch(operando){
			case '+':
				return true;
			case '-':
				return true;
			case '*':
				return true;
			case '/':
				return true;
			case '=':
				return true;
			case 'c':
			case 'C':
				return true;
			case 'f':return true;
			case 'o':
			case 'O':
				return true;			
			default:
				System.out.println("Operador no reconocido: "+operando);
				return false;
				
		}	
	}
	
	public double calcula(Operacion op){
		if(isOperandoValido(op.operando)){
			switch(op.operando){
				case '+':
					error=false;
					//Que va a pasar
					op.operando=0;
					return op.resultado + op.numero;
				case '-':
					error=false;
					//Que va a pasar
					op.operando=0;
					return op.resultado - op.numero;
				case '*':
					error=false;
					//Que va a pasar
					op.operando=0;
					return op.resultado * op.numero;
				case '/':
					error=false;
					//Que va a pasar
					op.operando=0;
					return op.resultado / op.numero;
					
				//TODO				
				default:
					error=true;
					op.operando=0;
					System.out.println("error con "+op.operando);
					return 0;
			
			}
		}
		
		error=true;
		System.out.println("Error");
		
		return numero;		
	}		
}
