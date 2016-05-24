package vista;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import controlador.Controlador;
import modelo.PersonaBean;

public class ListaPersonas extends JFrame {
	private Controlador controlador;
	private JTable tabla;

	public ListaPersonas(String titulo, Controlador controlador) {
		super(titulo);
		this.controlador = controlador;
		initComponents();
		addListenersAModelo();
		setTitle(titulo);
	}

	private void addListenersAModelo() {
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
		// TODO Auto-generated method stub

	}

	private void addComponentesAVentana() {
		this.add(tabla);

	}

	private void addListeners() {
		// TODO Auto-generated method stub

	}

	private void initComponents() {
		final ArrayList<PersonaBean> listaPersonas = controlador.getListaPersonas();

		TableModel modeloTabla = new TableModel() {

			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub

			}

			@Override
			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return listaPersonas.get(rowIndex).getNombre();
				case 1:
					return listaPersonas.get(rowIndex).getCorreo();
				case 2:
					return listaPersonas.get(rowIndex).getSexo();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return listaPersonas.size();
			}

			@Override
			public String getColumnName(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return "Nombre";
				case 1:
					return "Correo";
				case 2:
					return "Sexo";
				}
				return "";
			}

			@Override
			public int getColumnCount() {
				return 3;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			@Override
			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub
			}
		};

		tabla = new JTable(modeloTabla);
	}

}
