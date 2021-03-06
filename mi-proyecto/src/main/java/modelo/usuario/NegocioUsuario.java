package modelo.usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.BD;

public class NegocioUsuario extends BD<Usuario> {
	private static NegocioUsuario instancia;
	private Connection conn = null;

	private NegocioUsuario() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		consulta_findAll = "SELECT * FROM USUARIO";
		consulta_findLike = "SELECT * FROM USUARIO WHERE ";
	}

	public static NegocioUsuario getInstance() {
		if (instancia == null) {
			instancia = new NegocioUsuario();
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
	public Usuario obtenerObjeto(Connection conn, String consulta) throws SQLException {
		return super.obtenerObjeto(conn, consulta);
	}

	@Override
	public Usuario obtenerObjeto(Connection conn, String consulta, String... parametros) throws SQLException {
		return super.obtenerObjeto(conn, consulta, parametros);
	}

	@Override
	public List<Usuario> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
		return super.obtenerListaObjetos(conn, consulta);
	}

	@Override
	public void insertarObjeto(Connection conn, Usuario objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO USUARIO(USUA_NOMBRE,USUA_PASSWORD) VALUES(?,?)");
			st.setString(1, objeto.getNombre());
			st.setString(2, objeto.getPassword());

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
	public void modificarListaObjetos(Connection conn, List<Usuario> listaObjetos) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE USUARIO SET USUA_PASSWORD=? WHERE USUA_ID=?");
			for (Usuario objeto : listaObjetos) {
				st.setString(1, objeto.getPassword());
				st.setString(2, objeto.getId());

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
	public void modificarObjeto(Connection conn, Usuario objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE USUARIO SET USUA_PASSWORD=? WHERE USUA_ID=?");
			st.setString(1, objeto.getPassword());
			st.setString(2, objeto.getId());

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
	protected Usuario tratarResultSet(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return construirObjeto(rs);
		} else {
			return null;
		}
	}

	@Override
	protected Usuario construirObjeto(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setNombre(rs.getString("USUA_NOMBRE"));
		usuario.setPassword(rs.getString("USUA_PASSWORD"));

		return usuario;
	}

	@Override
	protected List<Usuario> tratarListaResultSet(ResultSet rs) throws SQLException {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = construirObjeto(rs);

			lista.add(usuario);
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
