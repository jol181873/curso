package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public abstract class BeanAbstracto implements Serializable {
	protected PropertyChangeSupport pcs;

	public BeanAbstracto() {
		pcs = new PropertyChangeSupport(this);
	}

	public void addPropiedadListener(String propiedad, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propiedad, listener);
	}
}
