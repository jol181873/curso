package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.PersonaBean;

public class Formulario extends JFrame {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem item1;

	private JLabel etqNombre;
	private JTextField txtNombre;

	private JLabel etqCorreo;
	private JTextField txtCorreo;

	private JLabel etqPassword;
	private JPasswordField txtPassword;

	private JLabel etqEdad;
	private JSlider sldEdad;

	private JLabel etqSexo;
	private JComboBox<String> cmbSexo;

	private JTabbedPane panelPestanias;
	private JLabel etqObservaciones;
	private JTextArea areaObservaciones;
	private JTextArea areaAficiones;
	private JLabel etqAficiones;
	private JPanel panelPestania1;
	private JPanel panelPestania2;
	private JPanel panelPestania3;

	private JPanel panelPrincipal;
	private JButton botonAceptar;

	private PersonaBean personaBean;
	private Controlador controlador;

	public Formulario() {
		super();
		initComponents();
	}

	public Formulario(String titulo, PersonaBean personaBean, Controlador controlador) {
		super();
		this.personaBean = personaBean;
		this.controlador = controlador;
		initComponents();
		addListenersAModelo();
		setTitle(titulo);
	}

	public void initComponents() {
		configurarVentana();
		configurarFormulario();
		addComponentesAVentana();
		addListeners();
		pack();
		setSize(500, 700);

	}

	private void configurarVentana() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void configurarFormulario() {
		menuBar = new JMenuBar();
		menu = new JMenu("Listados");
		item1 = new JMenuItem("Listado de todas las personas");
		menu.add(item1);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);

		this.setLayout(new GridBagLayout());
		panelPrincipal = new JPanel();
		BorderLayout borderLayout = new BorderLayout(100, 10);
		panelPrincipal.setLayout(borderLayout);

		etqNombre = new JLabel("Nombre:");
		txtNombre = new JTextField();
		txtNombre.setText(personaBean.getNombre());

		etqCorreo = new JLabel("Correo:");
		txtCorreo = new JTextField();
		txtCorreo.setText(personaBean.getCorreo());

		etqPassword = new JLabel("Password:");
		txtPassword = new JPasswordField();
		txtPassword.setText(personaBean.getPassword());

		etqEdad = new JLabel("Edad:");
		sldEdad = new JSlider(0, 150);
		sldEdad.setMajorTickSpacing(10);
		sldEdad.setPaintTicks(true);
		sldEdad.setPaintLabels(true);
		sldEdad.setLabelTable(sldEdad.createStandardLabels(20));
		sldEdad.setValue(personaBean.getEdad());

		etqSexo = new JLabel("Sexo:");
		cmbSexo = new JComboBox<>(new String[] { "Hombre", "Mujer" });
		cmbSexo.setSelectedItem(personaBean.getSexo());

		panelPestanias = new JTabbedPane();
		panelPestanias.setPreferredSize(new Dimension(300, 300));

		etqObservaciones = new JLabel("Observaciones:");
		etqObservaciones.setBounds(10, 10, 100, 10);
		etqAficiones = new JLabel("Aficiones:");
		etqAficiones.setBounds(10, 10, 100, 10);

		areaObservaciones = new JTextArea();
		areaObservaciones.setBounds(10, 30, 200, 200);
		areaObservaciones.setText(personaBean.getObservaciones());

		areaAficiones = new JTextArea();
		areaAficiones.setBounds(10, 30, 200, 200);
		areaAficiones.setText(personaBean.getAficiones());

		panelPestania1 = new JPanel();
		panelPestania1.setLayout(null);
		panelPestania1.add(etqObservaciones);
		panelPestania1.add(areaObservaciones);

		panelPestania2 = new JPanel();
		panelPestania2.setLayout(null);
		panelPestania2.add(etqAficiones);
		panelPestania2.add(areaAficiones);

		panelPestania3 = new JPanel();

		panelPestanias.add("Observaciones", panelPestania1);
		panelPestanias.add("Aficiones", panelPestania2);
		panelPestanias.add("Preferencias", panelPestania3);

		botonAceptar = new JButton("Aceptar");

	}

	private void addComponentesAVentana() {
		JPanel panelEste = new JPanel();
		panelEste.setLayout(new GridLayout(6, 1, 0, 10));
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(6, 1, 0, 10));

		panelPrincipal.add(panelEste, BorderLayout.WEST);
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);

		panelEste.add(etqNombre);
		panelCentro.add(txtNombre);

		panelEste.add(etqCorreo);
		panelCentro.add(txtCorreo);

		panelEste.add(etqPassword);
		panelCentro.add(txtPassword);

		panelEste.add(etqSexo);
		panelCentro.add(cmbSexo);

		panelEste.add(etqEdad);
		panelCentro.add(sldEdad);

		panelCentro.add(botonAceptar);

		panelPrincipal.add(panelPestanias, BorderLayout.SOUTH);

		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridheight = 3;
		restricciones.gridheight = 3;
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		add(panelPrincipal, restricciones);
	}

	private void addListeners() {
		final Formulario this2 = this;

		botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonaBean persona2 = new PersonaBean();
				persona2.setAficiones(areaAficiones.getText());
				persona2.setObservaciones(areaObservaciones.getText());
				persona2.setCorreo(txtCorreo.getText());
				persona2.setEdad(sldEdad.getValue());
				persona2.setNombre(txtNombre.getText());
				persona2.setPassword(new String(txtPassword.getPassword()));
				persona2.setSexo((String) cmbSexo.getSelectedItem());

				ArrayList<String> listaErrores = persona2.validar();

				if (!listaErrores.isEmpty()) {
					JDialog dialogo = new JDialog(this2, "Errores", true);
					dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					dialogo.setLayout(new GridLayout(10, 1));
					for (String error : listaErrores) {
						dialogo.add(new JLabel(error));
					}

					dialogo.setSize(300, 300);
					dialogo.setVisible(true);
				} else {
					personaBean.setAficionesProperty(areaAficiones.getText());
					personaBean.setObservacionesProperty(areaObservaciones.getText());
					personaBean.setCorreoProperty(txtCorreo.getText());
					personaBean.setEdadProperty(sldEdad.getValue());
					personaBean.setNombreProperty(txtNombre.getText());
					personaBean.setPasswordProperty(new String(txtPassword.getPassword()));
					personaBean.setSexoProperty((String) cmbSexo.getSelectedItem());

					try {
						controlador.guardar();
						JOptionPane.showMessageDialog(this2, "Se han grabado los cambios", "", JOptionPane.OK_OPTION);
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(this2, "Se ha producido un error al guardar los datos", "ERROR",
								JOptionPane.OK_OPTION);
					}
				}
			}
		});

		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListaPersonas frame = new ListaPersonas("Lista personas", controlador);
			}
		});

	}

	private void addListenersAModelo() {
		personaBean.addPropiedadListener("nombre", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				txtNombre.setText((String) evt.getNewValue());

			}
		});

		personaBean.addPropiedadListener("correo", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				txtCorreo.setText((String) evt.getNewValue());
			}
		});

		personaBean.addPropiedadListener("password", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				txtPassword.setText((String) evt.getNewValue());

			}
		});

		personaBean.addPropiedadListener("sexo", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				cmbSexo.setSelectedItem(evt.getNewValue());

			}
		});

		personaBean.addPropiedadListener("edad", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				sldEdad.setValue((int) evt.getNewValue());
			}
		});

		personaBean.addPropiedadListener("observaciones", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				areaObservaciones.setText((String) evt.getNewValue());
			}
		});

		personaBean.addPropiedadListener("aficiones", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				areaAficiones.setText((String) evt.getNewValue());
			}
		});
	}
}
