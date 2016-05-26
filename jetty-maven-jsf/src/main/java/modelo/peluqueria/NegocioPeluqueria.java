package modelo.peluqueria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.BD;

public class NegocioPeluqueria extends BD<Peluqueria> {
	private static NegocioPeluqueria instancia;

	private Connection conn = null;

	private NegocioPeluqueria() {

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		consulta_findAll = "SELECT * FROM PELUQUERIA";
		consulta_findLike = "SELECT * FROM PELUQUERIA WHERE ";
	}

	public static NegocioPeluqueria getInstance() {
		if (instancia == null) {
			instancia = new NegocioPeluqueria();
		}

		return instancia;
	}

	@Override
	public void conectar() throws SQLException {
		setConnection(DriverManager.getConnection("jdbc:h2:~/curso", "sa", ""));
	}

	public void cerrarConexion(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Peluqueria obtenerObjeto(Connection conn, String consulta) throws SQLException {
		return super.obtenerObjeto(conn, consulta);
	}

	@Override
	public Peluqueria obtenerObjeto(Connection conn, String consulta, String... parametros) throws SQLException {
		return super.obtenerObjeto(conn, consulta, parametros);
	}

	@Override
	public List<Peluqueria> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
		return super.obtenerListaObjetos(conn, consulta);
	}

	/**
	 * OJO NO CIERRA LA CONEXION
	 * 
	 * @return
	 * @throws SQLException
	 */
	private long getIdSecuencia() throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("call NEXT VALUE FOR peluqueria_secuencia");
			rs.next();

			return rs.getLong(1);
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void insertarObjeto(Connection conn, Peluqueria objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO PELUQUERIA(PELU_ID,PELU_NOMBRE,PELU_DIRECCION,PELU_TELEFONO) VALUES(?,?,?,?)");
			st.setString(1, String.valueOf(getIdSecuencia()));
			st.setString(2, objeto.getNombre());
			st.setString(3, objeto.getDireccion());
			st.setString(4, objeto.getTelefono());

			int executeUpdate = st.executeUpdate();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void modificarObjeto(Connection conn, Peluqueria objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?");
			st.setString(1, objeto.getNombre());
			st.setString(2, objeto.getDireccion());
			st.setString(3, objeto.getTelefono());
			st.setString(4, objeto.getId());

			int executeUpdate = st.executeUpdate();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void modificarListaObjetos(Connection conn, List<Peluqueria> listaObjetos) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?");
			for (Peluqueria objeto : listaObjetos) {
				st.setString(1, objeto.getNombre());
				st.setString(2, objeto.getDireccion());
				st.setString(3, objeto.getTelefono());
				st.setString(4, objeto.getId());

				int executeUpdate = st.executeUpdate();
			}

		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void borrarObjeto(Connection conn, Peluqueria objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE PELUQUERIA WHERE PELU_ID=?");
			st.setString(1, objeto.getId());

			int executeUpdate = st.executeUpdate();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	// ================================================================================================
	// ================================================================================================
	// ================================================================================================

	@Override
	protected Peluqueria tratarResultSet(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return construirObjeto(rs);
		} else {
			return null;
		}
	}

	@Override
	protected Peluqueria construirObjeto(ResultSet rs) throws SQLException {
		Peluqueria peluqueria = new Peluqueria();
		peluqueria.setId(rs.getString("PELU_ID"));
		peluqueria.setNombre(rs.getString("PELU_NOMBRE"));
		peluqueria.setDireccion(rs.getString("PELU_DIRECCION"));
		peluqueria.setTelefono(rs.getString("PELU_TELEFONO"));

		return peluqueria;
	}

	@Override
	protected List<Peluqueria> tratarListaResultSet(ResultSet rs) throws SQLException {
		ArrayList<Peluqueria> lista = new ArrayList<Peluqueria>();

		while (rs.next()) {
			Peluqueria peluqueria = construirObjeto(rs);

			lista.add(peluqueria);
		}
		return lista;
	}

	@Override
	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	// ================================================================================================
	// ================================================================================================
	// ================================================================================================
}
