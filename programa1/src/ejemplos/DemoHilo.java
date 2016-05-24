package ejemplos;

public class DemoHilo {
	public static void main(String[] args) throws InterruptedException {
		Hilo h=new Hilo("H");
		Thread t=new Thread(h);
		t.start();
		
		Hilo h1=new Hilo("H1");
		Thread t1=new Thread(h1);
		t1.start();
		t1.wait(1000);			
	}

}
