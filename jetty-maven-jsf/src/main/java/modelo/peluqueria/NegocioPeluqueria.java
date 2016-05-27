package modelo.peluqueria;

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

import model.Peluqueria;
import modelo.BD;

public class NegocioPeluqueria extends BD<Peluqueria> {
	private static NegocioPeluqueria instancia;

	@PersistenceContext
	private EntityManager em = Persistence.createEntityManagerFactory("jol.pelus.mascotas").createEntityManager();

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
		// return super.obtenerObjeto(conn, consulta, parametros);

		return this.em.find(Peluqueria.class, Long.valueOf(parametros[0]));
	}

	@Override
	public List<Peluqueria> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
		// return super.obtenerListaObjetos(conn, consulta);

		List<Peluqueria> lista = this.em
				.createQuery("SELECT pelu FROM Peluqueria pelu ORDER BY pelu.peluNombre", Peluqueria.class)
				.getResultList();
		// em.close();
		return lista;

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
		/*
		 * PreparedStatement st = null; try { st = conn.prepareStatement(
		 * "INSERT INTO PELUQUERIA(PELU_ID,PELU_NOMBRE,PELU_DIRECCION,PELU_TELEFONO) VALUES(?,?,?,?)"
		 * ); st.setString(1, String.valueOf(getIdSecuencia())); st.setString(2,
		 * objeto.getPeluNombre()); st.setString(3, objeto.getPeluDireccion());
		 * st.setString(4, objeto.getPeluTelefono());
		 * 
		 * int executeUpdate = st.executeUpdate(); } finally { try { st.close();
		 * conn.close(); } catch (SQLException e) { throw new
		 * RuntimeException(e); } }
		 */

		this.em.persist(objeto);

	}

	@Override
	public void modificarObjeto(Connection conn, Peluqueria objeto) throws SQLException {
		/*
		 * PreparedStatement st = null; try { st = conn.prepareStatement(
		 * "UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?"
		 * ); st.setString(1, objeto.getPeluNombre()); st.setString(2,
		 * objeto.getPeluDireccion()); st.setString(3,
		 * objeto.getPeluTelefono()); st.setLong(4, objeto.getPeluId());
		 * 
		 * int executeUpdate = st.executeUpdate(); } finally { try { st.close();
		 * conn.close(); } catch (SQLException e) { throw new
		 * RuntimeException(e); } }
		 */

		this.em.merge(objeto);
	}

	@Override
	public void modificarListaObjetos(Connection conn, List<Peluqueria> listaObjetos) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PELUQUERIA SET PELU_NOMBRE=?,PELU_DIRECCION=?,PELU_TELEFONO=? WHERE PELU_ID=?");
			for (Peluqueria objeto : listaObjetos) {
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
	public void borrarObjeto(Connection conn, Peluqueria objeto) throws SQLException {
		/*
		 * PreparedStatement st = null; try { st = conn.prepareStatement(
		 * "DELETE PELUQUERIA WHERE PELU_ID=?"); st.setLong(1,
		 * objeto.getPeluId());
		 * 
		 * int executeUpdate = st.executeUpdate(); } finally { try { st.close();
		 * conn.close(); } catch (SQLException e) { throw new
		 * RuntimeException(e); } }
		 */

		this.em.remove(objeto);
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
		peluqueria.setPeluId(rs.getLong("PELU_ID"));
		peluqueria.setPeluNombre(rs.getString("PELU_NOMBRE"));
		peluqueria.setPeluDireccion(rs.getString("PELU_DIRECCION"));
		peluqueria.setPeluTelefono(rs.getString("PELU_TELEFONO"));

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
