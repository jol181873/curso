package ejemplos;

public enum diasSemana {
	LUNES,MARTES,MIERCOLES,JUEVES,VIERNES,SABADO,DOMINGO;
	
	public String toString() {
		switch(this) {
			case LUNES: 
				return "Lunes";
			case MARTES:
				return "Martes";
			case MIERCOLES:
				return "Miércoles";
			case JUEVES:
				return "Jueves";
			case VIERNES:
				return "Viernes";
			case SABADO:
				return "Sábado";
			case DOMINGO:
				return "Domingo";
			default: return null;
		}
	}

}
