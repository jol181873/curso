package org.jsf.jol181873.repositorio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsf.jol181873.modelo.dto.UsuarioDTO;
import org.jsf.jol181873.repositorio.jdbc.BdJDBC;

public class RepoUsuarioJDBC extends BdJDBC<UsuarioDTO> {
	private static RepoUsuarioJDBC instancia;
	private Connection conn = null;

	private RepoUsuarioJDBC() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		consulta_findAll = "SELECT * FROM USUARIO";
		consulta_findLike = "SELECT * FROM USUARIO WHERE ";
	}

	public static RepoUsuarioJDBC getInstance() {
		if (instancia == null) {
			instancia = new RepoUsuarioJDBC();
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
	public UsuarioDTO obtenerObjeto(Connection conn, String consulta) throws SQLException {
		return super.obtenerObjeto(conn, consulta);
	}

	@Override
	public UsuarioDTO obtenerObjeto(Connection conn, String consulta, String... parametros) throws SQLException {
		return super.obtenerObjeto(conn, consulta, parametros);
	}

	@Override
	public List<UsuarioDTO> obtenerListaObjetos(Connection conn, String consulta) throws SQLException {
		return super.obtenerListaObjetos(conn, consulta);
	}

	@Override
	public void insertarObjeto(Connection conn, UsuarioDTO objeto) throws SQLException {
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
	public void modificarListaObjetos(Connection conn, List<UsuarioDTO> listaObjetos) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE USUARIO SET USUA_PASSWORD=? WHERE USUA_ID=?");
			for (UsuarioDTO objeto : listaObjetos) {
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
	public void modificarObjeto(Connection conn, UsuarioDTO objeto) throws SQLException {
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

	@Override
	public void borrarObjeto(Connection conn, UsuarioDTO objeto) throws SQLException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE USUARIO WHERE USUA_ID=?");
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
	protected UsuarioDTO tratarResultSet(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return construirObjeto(rs);
		} else {
			return null;
		}
	}

	@Override
	protected UsuarioDTO construirObjeto(ResultSet rs) throws SQLException {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setNombre(rs.getString("USUA_NOMBRE"));
		usuario.setPassword(rs.getString("USUA_PASSWORD"));

		return usuario;
	}

	@Override
	protected List<UsuarioDTO> tratarListaResultSet(ResultSet rs) throws SQLException {
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		while (rs.next()) {
			UsuarioDTO usuario = construirObjeto(rs);

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
