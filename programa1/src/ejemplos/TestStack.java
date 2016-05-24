package ejemplos;

import java.util.Stack;

public class TestStack {

	public static void main(String[] args) {
		Stack s = new Stack<>();
		for(int i=0;i<5;i++){
			s.push(new Integer(i));
		}
		
		System.out.println("Ultima entrada en la pilar primera en salir: "+s.pop());
		System.out.println("Penúltima entrada en la pilar segunda en salir: "+s.pop());
		
		
	}

}
