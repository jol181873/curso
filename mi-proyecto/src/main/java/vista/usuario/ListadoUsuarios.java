package vista.usuario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import modelo.usuario.NegocioUsuario;
import modelo.usuario.Usuario;
import vista.genericos.FormularioGenerico;
import vista.genericos.ListadoGenerico;

public class ListadoUsuarios extends ListadoGenerico<Usuario> {
	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ListadoUsuarios(FormularioGenerico<Usuario> form) {
		super(NegocioUsuario.getInstance(), form);
	}

	@Override
	protected TableModel getModeloTabla(final List<Usuario> listaObjetos) {
		// TODO Auto-generated method stub
		return new TableModel() {

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub

			}

			public void removeTableModelListener(TableModelListener l) {
				// TODO Auto-generated method stub

			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return false;
			}

			public Object getValueAt(int rowIndex, int columnIndex) {
				Usuario usuario = listaObjetos.get(rowIndex);
				switch (columnIndex) {
				case 0:
					return usuario.getNombre();
				case 1:
					return "****************";
				case 50:
					return usuario;
				}

				return null;
			}

			public int getRowCount() {
				return listaObjetos.size();
			}

			public String getColumnName(int columnIndex) {
				// TODO Auto-generated method stub
				switch (columnIndex) {
				case 0:
					return "Nombre";
				case 1:
					return "Contraseña";
				}

				return null;
			}

			public int getColumnCount() {
				return 2;
			}

			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}

			public void addTableModelListener(TableModelListener l) {
			}
		};
	}

	@Override
	protected List<Usuario> realizarBusqueda(HashMap<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}
}
