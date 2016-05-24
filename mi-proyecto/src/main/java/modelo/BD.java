package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BD<T> {
	private static final Logger log = LogManager.getLogger(BD.class);

	protected String consulta_findAll;
	protected String consulta_findLike;

	public T obtenerObjeto(Connection conn, String consulta) throws SQLException {
		log.entry();
		log.info(consulta);

		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(consulta);

			return log.exit(tratarResultSet(rs));
		} catch (SQLException ex) {
			log.error("Error obteniendo objetos de la bd");
			log.error("Excepcion propagada");
			log.catching(ex);

			throw ex;
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();

				log.exit();
			} catch (SQLException e) {
				log.error("Error cerrando la conexion, el statement o el resultset");
				log.error("Excepcion relanzada como RuntimeException");
				log.catching(e);

				throw new RuntimeException(e);
			}
		}
	}

	public T obtenerObjeto(Connection conn, String consulta, String... parametros) throws SQLException {
		log.entry();
		log.info(consulta);
		log.info(parametros);

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(consulta);
			for (int i = 0; i < parametros.length; i++) {
				st.setString(i + 1, parametros[i]);
			}
			rs = st.executeQuery();

			return log.exit(tratarResultSet(rs));
		} catch (SQLException ex) {
			log.error("Error obteniendo objeto con parametros de la bd");
			log.error("Excepcion propagada");
			log.catching(ex);

			throw ex;
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				log.error("Error cerrando la conexion, el statement o el resultset");
				log.error("Excepcion relanzada como RuntimeException");
				log.catching(e);

				throw new RuntimeException(e);
			}
		}
	}

	public List<T> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
		log.entry();
		log.info(consulta);

		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(consulta);

			return log.exit(tratarListaResultSet(rs));
		} catch (SQLException ex) {
			log.error("Error obteniendo lista de objetos de la bd");
			log.error("Excepcion propagada");
			log.catching(ex);

			throw ex;
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				log.error("Error cerrando la conexion, el statement o el resultset");
				log.error("Excepcion relanzada como RuntimeException");
				log.catching(e);

				throw new RuntimeException(e);
			}
		}
	};

	public List<T> obtenerListaObjetosParecidos(Connection conn, String consulta, HashMap<String, String> parametros)
			throws SQLException {
		log.entry();
		log.info(consulta);
		log.info(parametros);

		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			for (Entry<String, String> entrySet : parametros.entrySet()) {
				consulta = consulta + entrySet.getKey() + " LIKE '%" + entrySet.getValue() + "%' AND ";
			}
			consulta = consulta.substring(0, consulta.length() - 5);
			rs = st.executeQuery(consulta);

			return log.exit(tratarListaResultSet(rs));
		} catch (SQLException ex) {
			log.error("Error obteniendo lista de objetos parecidos de la bd");
			log.error("Excepcion propagada");
			log.catching(ex);

			throw ex;
		} finally {

			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				log.error("Error cerrando la conexion, el statement o el resultset");
				log.error("Excepcion relanzada como RuntimeException");
				log.catching(e);

				throw new RuntimeException(e);
			}
		}
	};

	public List<T> findAll(Connection conn) throws SQLException {
		log.entry();
		return log.exit(obtenerListaObjetos(conn, consulta_findAll));
	}

	public List<T> findParecidos(Connection conn, HashMap<String, String> parametros) throws SQLException {
		log.entry();
		log.info(parametros);
		return log.exit(obtenerListaObjetosParecidos(conn, consulta_findLike, parametros));
	}

	public abstract Connection getConnection();

	public abstract void conectar() throws SQLException;

	public abstract void insertarObjeto(Connection conn, T objeto) throws SQLException;

	public abstract void modificarObjeto(Connection conn, T objeto) throws SQLException;

	public abstract void modificarListaObjetos(Connection conn, List<T> listaObjetos) throws SQLException;

	// ========================================================================
	protected abstract T tratarResultSet(ResultSet rs) throws SQLException;

	protected abstract List<T> tratarListaResultSet(ResultSet rs) throws SQLException;

	protected abstract T construirObjeto(ResultSet rs) throws SQLException;
}
