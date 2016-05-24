import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import controlador.Controlador;
import modelo.Modelo;
import modelo.PersonaBean;
import vista.Formulario;

public class Main {
	public static void main(String[] args) throws Exception {
		// Demo demo = new Demo("mi primera ventana");
		// MouseDemo demo=new MouseDemo();
		// FrameConBorderLayout demo=new FrameConBorderLayout("Border Layout");
		// FrameConFlowLayout demo2=new FrameConFlowLayout("Flow Layout");
		// FrameConGridLayout demo3=new FrameConGridLayout("Grid Layout");
		// FrameConCardLayout demo4=new FrameConCardLayout("Card Layout");
		// FrameConPanelesAnidados demo=new FrameConPanelesAnidados();

		Modelo.getInstance().cargar();
		PersonaBean persona = null;
		if (Modelo.getInstance().getListaPersonas().isEmpty()) {
			persona = new PersonaBean();
			Modelo.getInstance().getListaPersonas().add(persona);
		} else {
			persona = Modelo.getInstance().getListaPersonas().get(0);
		}

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(evt.getPropertyName() + ": " + evt.getOldValue() + " --> " + evt.getNewValue());

			}
		};
		persona.addPropiedadListener("nombre", listener);
		persona.addPropiedadListener("correo", listener);
		persona.addPropiedadListener("password", listener);
		persona.addPropiedadListener("edad", listener);
		persona.addPropiedadListener("sexo", listener);
		persona.addPropiedadListener("observaciones", listener);
		persona.addPropiedadListener("aficiones", listener);

		Formulario form = new Formulario("Mi formulario de alta", persona, new Controlador());
	}

}
