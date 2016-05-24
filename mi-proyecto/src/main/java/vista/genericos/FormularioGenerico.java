package vista.genericos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Path;

import modelo.BD;

public abstract class FormularioGenerico<T> extends JInternalFrame {
	protected T bean;
	protected BD<T> negocio;
	protected TipoFormulario tipo;

	public FormularioGenerico(final BD<T> negocio, T bean) {
		this(negocio);
		this.setTipo(TipoFormulario.EDITAR);
		this.setBean(bean);
	}

	public FormularioGenerico(final BD<T> negocio) {
		this.setTipo(TipoFormulario.NUEVO);
		setBean(null);
		this.negocio = negocio;

		iniciarComponentes();
	}

	protected void iniciarComponentes() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JButton btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 3;
		gbc_btnAceptar.gridy = 8;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					T objeto = prepararObjeto();
					if (objeto != null) {
						if (tipo == TipoFormulario.NUEVO) {
							negocio.conectar();
							negocio.insertarObjeto(negocio.getConnection(), objeto);
							JOptionPane.showMessageDialog(null, "Registro guardado");
							setVisible(false);
							getTopLevelAncestor().setVisible(true);
						} else {
							negocio.conectar();
							negocio.modificarObjeto(negocio.getConnection(), objeto);
							JOptionPane.showMessageDialog(null, "Cambios guardados");
							setVisible(false);
							getTopLevelAncestor().setVisible(true);
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error al guardar");
					e1.printStackTrace();
				}
			}
		});

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GridBagConstraints gbc_botonCancelar = new GridBagConstraints();
		gbc_botonCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_botonCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonCancelar.gridx = 5;
		gbc_botonCancelar.gridy = 8;
		getContentPane().add(botonCancelar, gbc_botonCancelar);
	}

	@Override
	public void setVisible(boolean boo) {
		if (boo) {
			recargar();
		}
		super.setVisible(boo);
	}

	public abstract void recargar();

	public abstract T prepararObjeto();

	public TipoFormulario getTipo() {
		return tipo;
	}

	public void setTipo(TipoFormulario tipo) {
		this.tipo = tipo;
	}

	public T getBean() {
		return bean;
	}

	public void setBean(T bean) {
		this.bean = bean;
		if (bean == null) {
			tipo = TipoFormulario.NUEVO;
		} else {
			tipo = TipoFormulario.EDITAR;
		}
	}

	protected void mostrarErrores(Set<ConstraintViolation<T>> errores) {
		String mensaje = "";
		for (ConstraintViolation<T> error : errores) {
			Path propertyPath = error.getPropertyPath();
			String campo = propertyPath.iterator().next().getName();
			mensaje = mensaje + campo + ": " + error.getMessage() + "\n";
		}
		JOptionPane.showMessageDialog(this, mensaje);
	};

}
