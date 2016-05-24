package vista.genericos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import modelo.BD;

public abstract class ListadoGenerico<T> extends JInternalFrame {
	private JInternalFrame frame;
	private JTable table;
	private BD<T> negocio;
	protected FormularioGenerico<T> formEditar;
	protected List<T> listaEncontrados;

	public ListadoGenerico(BD<T> negocio, FormularioGenerico<T> formEditar) {
		this.frame = this;
		this.negocio = negocio;
		this.formEditar = formEditar;
		setBounds(100, 100, 450, 300);
		table = new JTable();
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
	}

	@Override
	public void setVisible(boolean boo) {
		super.setVisible(boo);
		if (boo) {
			recargar();
		}
	}

	private void recargar() {
		try {
			if (listaEncontrados == null) {
				negocio.conectar();
				List<T> listaObjetos = negocio.findAll(negocio.getConnection());

				table.setRowHeight(30);

				table.setModel(getModeloTabla(listaObjetos));
			} else {
				table.setModel(getModeloTabla(listaEncontrados));
			}

			table.setDefaultRenderer(JButton.class, new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
						boolean tieneElFoco, int fila, int columna) {
					Component com = (Component) objeto;
					if (fila % 2 == 1) {
						com.setBackground(new Color(255, 191, 0));
					} else {
						com.setBackground(new Color(255, 255, 255));
					}
					return com;
				}
			});

			final DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado,
						boolean tieneElFoco, int fila, int columna) {
					if (fila % 2 == 1) {
						this.setOpaque(true);
						this.setBackground(new Color(255, 191, 0));
					} else {
						this.setOpaque(true);
						this.setBackground(new Color(255, 255, 255));
					}
					return super.getTableCellRendererComponent(jtable, objeto, estaSeleccionado, tieneElFoco, fila,
							columna);
				}
			};

			table.setDefaultRenderer(Object.class, tableCellRenderer);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int fila = table.rowAtPoint(e.getPoint());
					int columna = table.columnAtPoint(e.getPoint());

					if (table.getModel().getColumnClass(columna).equals(JButton.class)) {
						JPanel panel = (JPanel) table.getModel().getValueAt(fila, columna);
						JButton boton = (JButton) panel.getComponent(0);
						boton.doClick();
					}
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected abstract TableModel getModeloTabla(List<T> listaObjetos);

	protected List<T> realizarBusqueda(HashMap<String, String> params) {
		try {
			negocio.conectar();

			return negocio.findParecidos(negocio.getConnection(), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	};

}
