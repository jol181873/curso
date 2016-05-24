package ejemplos;

public class Ejercicio_array_matriz {

	public static void main(String[] args) {
		int[][] matriz=new int[4][5];
		
		for (int row=0;row<4;row++) {
			for (int col=0;col<5;col++) {
				matriz[row][col]=row+col;
			}
		}
		
		
		for (int row=0;row<4;row++) {
			for (int col=0;col<5;col++) {
				System.out.print(matriz[row][col]+"   ");
			}
			System.out.println();
		}
		
		
		//tres dimensiones
		int[][][] matriz3=new int[4][5][3];
		
		for (int row=0;row<matriz3.length;row++) {
			for (int col=0;col<matriz3[0].length;col++) {
				for (int i=0;i<matriz3[0][0].length;i++) {
					matriz3[row][col][i]=row+col+i;
				}
			}
		}
		
		for (int row=0;row<matriz3.length;row++) {
			for (int col=0;col<matriz3[0].length;col++) {
				for (int i=0;i<matriz3[0][0].length;i++) {
					System.out.print(matriz3[row][col][i]+"   ");
				}
			}
			System.out.println();
		}
					
	}

}
