package org.jsf.jol181873.repositorio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.jsf.jol181873.modelo.dto.PeluqueriaDTO;
import org.jsf.jol181873.repositorio.RepoPeluqueriaI;

public class RepoPeluqueriaJDBC extends BdJDBC<PeluqueriaDTO> implements RepoPeluqueriaI {
	private static RepoPeluqueriaJDBC instancia;

	@PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("jol.pelus.mascotas").createEntityManager();

	private Connection conn = null;

	public RepoPeluqueriaJDBC() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		consulta_findAll = "SELECT * FROM PELUQUERIA";
		consulta_findLike = "SELECT * FROM PELUQUERIA WHERE ";
	}

	public static RepoPeluqueriaJDBC getInstance() {
		if (instancia == null) {
			instancia = new RepoPeluqueriaJDBC();
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
	public PeluqueriaDTO obtenerObjeto(Connection conn, String consulta) throws SQLException {
		return super.obtenerObjeto(conn, consulta);
	}

	@Override
	public PeluqueriaDTO obtenerObjeto(Connection conn, String consulta, String... parametros) throws SQLException {
		return super.obtenerObjeto(conn, consulta, parametros);
	}

	@Override
	public List<PeluqueriaDTO> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
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
	public void insertarObjeto(Connection conn, PeluqueriaDTO objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO PELUQUERIA(PELU_ID,PELU_NOMBRE,PELU_DIRECCION,PELU_TELEFONO) VALUES(?,?,?,?)");
			st.setString(1, String.valueOf(getIdSecuencia()));
			st.setString(2, objeto.getPeluNombre());
			st.setString(3, objeto.getPeluDireccion());
			st.setString(4, objeto.getPeluTelefono());

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
	public void modificarObjeto(Connection conn, PeluqueriaDTO objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?");
			st.setString(1, objeto.getPeluNombre());
			st.setString(2, objeto.getPeluDireccion());
			st.setString(3, objeto.getPeluTelefono());
			st.setLong(4, objeto.getPeluId());

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
	public void modificarListaObjetos(Connection conn, List<PeluqueriaDTO> listaObjetos) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?");
			for (PeluqueriaDTO objeto : listaObjetos) {
				st.setString(1, objeto.getPeluNombre());
				st.setString(2, objeto.getPeluDireccion());
				st.setString(3, objeto.getPeluTelefono());
				st.setLong(4, objeto.getPeluId());

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

	public void borrarObjeto(Connection conn, PeluqueriaDTO objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE PELUQUERIA WHERE PELU_ID=?");
			st.setLong(1, objeto.getPeluId());

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
	protected PeluqueriaDTO tratarResultSet(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return construirObjeto(rs);
		} else {
			return null;
		}
	}

	@Override
	protected PeluqueriaDTO construirObjeto(ResultSet rs) throws SQLException {
		PeluqueriaDTO peluqueria = new PeluqueriaDTO();
		peluqueria.setPeluId(rs.getLong("PELU_ID"));
		peluqueria.setPeluNombre(rs.getString("PELU_NOMBRE"));
		peluqueria.setPeluDireccion(rs.getString("PELU_DIRECCION"));
		peluqueria.setPeluTelefono(rs.getString("PELU_TELEFONO"));

		return peluqueria;
	}

	@Override
	protected List<PeluqueriaDTO> tratarListaResultSet(ResultSet rs) throws SQLException {
		ArrayList<PeluqueriaDTO> lista = new ArrayList<PeluqueriaDTO>();

		while (rs.next()) {
			PeluqueriaDTO peluqueria = construirObjeto(rs);

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
	// ====REPO========================================================================================
	// ====REPO========================================================================================
	// ====REPO========================================================================================

	@Override
	public PeluqueriaDTO obtenerObjeto(PeluqueriaDTO objeto) {
		try {
			this.conectar();

			return super.obtenerObjeto(this.conn, "SELECT * FROM PELUQUERIA WHERE PELU_ID=" + objeto.getPeluId());
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PeluqueriaDTO> obtenerTodoLosObjetos() {
		try {
			this.conectar();

			return findAll(conn);
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public void insertarObjeto(PeluqueriaDTO objeto) {
		try {
			this.conectar();

			this.insertarObjeto(conn, objeto);
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public void modificarObjeto(PeluqueriaDTO objeto) {
		try {
			this.conectar();

			this.modificarObjeto(conn, objeto);
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public void borrarObjeto(PeluqueriaDTO objeto) {
		try {
			this.conectar();

			this.borrarObjeto(conn, objeto);
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}
}
