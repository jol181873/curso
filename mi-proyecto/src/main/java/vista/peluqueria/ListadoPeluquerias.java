package vista.peluqueria;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.peluqueria.NegocioPeluqueria;
import modelo.peluqueria.Peluqueria;
import vista.genericos.FormularioGenerico;
import vista.genericos.ListadoGenerico;

public class ListadoPeluquerias extends ListadoGenerico<Peluqueria> {
	JPanel panelBusquedas;
	JTextField txtNombreBusq;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ListadoPeluquerias(FormularioGenerico<Peluqueria> form) {
		super(NegocioPeluqueria.getInstance(), form);

		panelBusquedas = new JPanel();
		panelBusquedas.setLayout(new GridBagLayout());
		GridBagConstraints res = new GridBagConstraints();
		res.gridx = 0;
		res.gridy = 0;
		panelBusquedas.add(new JLabel("Buscar por nombre: "), res);
		res = new GridBagConstraints();
		res.gridx = 1;
		res.gridy = 0;
		res.fill = GridBagConstraints.HORIZONTAL;
		res.weightx = 1;
		res.anchor = GridBagConstraints.WEST;
		txtNombreBusq = new JTextField();
		panelBusquedas.add(txtNombreBusq, res);

		txtNombreBusq.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					HashMap<String, String> hash = new HashMap<String, String>();
					hash.put("pelu_nombre", txtNombreBusq.getText());

					listaEncontrados = realizarBusqueda(hash);

					setVisible(true);
				}
			}
		});

		this.add(panelBusquedas, BorderLayout.NORTH);

	}

	@Override
	protected TableModel getModeloTabla(final List<Peluqueria> listaObjetos) {
		// TODO Auto-generated method stub
		return new TableModel() {
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			}

			public void removeTableModelListener(TableModelListener l) {
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			public Object getValueAt(int rowIndex, int columnIndex) {
				final Peluqueria pelu = listaObjetos.get(rowIndex);
				switch (columnIndex) {
				case 0:
					return pelu.getNombre();
				case 1:
					return pelu.getDireccion();
				case 2:
					return pelu.getTelefono();
				case 3:
					JPanel panel = new JPanel();
					panel.setLayout(new GridBagLayout());
					JButton boton = new JButton("Editar");
					GridBagConstraints cons = new GridBagConstraints();
					cons.gridx = 1;
					cons.gridy = 1;
					panel.add(boton, cons);

					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							formEditar.setBean(pelu);
							formEditar.setVisible(true);
						}
					});

					return panel;
				case 4:
					JPanel panel2 = new JPanel();
					panel2.setLayout(new GridBagLayout());
					JButton boton2 = new JButton("Nuevo");
					GridBagConstraints cons2 = new GridBagConstraints();
					cons2.gridx = 1;
					cons2.gridy = 1;
					panel2.add(boton2, cons2);

					boton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							formEditar.setBean(null);
							formEditar.setVisible(true);
						}
					});
					return panel2;
				}
				return null;
			}

			public int getRowCount() {
				return listaObjetos.size();
			}

			public String getColumnName(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return "Nombre";
				case 1:
					return "Dirección";
				case 2:
					return "Teléfono";
				case 3:
					return "";
				case 4:
					return "";
				}

				return null;
			}

			public int getColumnCount() {
				return 5;
			}

			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex < 3) {
					return String.class;
				} else if (columnIndex == 3) {
					return JButton.class;
				}

				return JButton.class;
			}

			public void addTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}
		};
	}
}
