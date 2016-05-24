package biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

public class TareaMorosos extends TimerTask {
	private ArrayList<Prestamo> listaPrestamos;
	private ArrayList<Prestamo> listaMorosos;
	
	public TareaMorosos(ArrayList<Prestamo> listaPrestamos,ArrayList<Prestamo> listaMorosos) {
		this.listaPrestamos=listaPrestamos;
		this.listaMorosos=listaMorosos;
	}

	@Override
	public void run() {
		listaMorosos.clear();
		Calendar calendario1=Calendar.getInstance();
		Calendar calendario2=Calendar.getInstance();
				
		for (Prestamo prestamo:listaPrestamos) {
			calendario2.setTime(prestamo.getHasta());
			if (calendario1.after(calendario2)) {
				listaMorosos.add(prestamo);
			}
		}		
	}

}
