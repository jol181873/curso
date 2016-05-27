package utilidades;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utilidades {
	private static final Logger log = LogManager.getLogger(Utilidades.class);
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static double getDoublePositivo() {
		Scanner sc = new Scanner(System.in);

		try {
			double real = sc.nextDouble();
			if (real < 0) {
				throw new Exception();
			}
			return real;
		} catch (Exception ex) {
			log.error("N�mero no v�lido");
			log.error(ex.getMessage());
			log.catching(Level.ERROR, ex);

			return -1;
		}
	}

	public static long getLongPositivo() {
		Scanner sc = new Scanner(System.in);

		try {
			long entero = sc.nextLong();
			if (entero < 0) {
				throw new Exception();
			}
			return entero;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("N�mero no v�lido");
			log.error(ex.getMessage());
			log.catching(Level.ERROR, ex);

			return -1;
		}
	}

	public static String getString() {
		Scanner sc = new Scanner(System.in);

		String cadena = sc.nextLine();
		return cadena;
	}

	public static void clrScr() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	public static String getFecha(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fecha);
	}

	public static String getMd5(String otro) {
		try {
			MessageDigest message = MessageDigest.getInstance("md5");
			byte[] bytes = message.digest(otro.getBytes());
			return new String(bytes);
		} catch (Exception ex) {
			log.error("Error calculando hash MD5");
			log.error(ex.getMessage());
			log.catching(Level.ERROR, ex);

			return null;
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Usuario usuario = new Usuario("admin", "admin");
		// NegocioUsuario neg = NegocioUsuario.getInstance();
		// neg.insertarObjeto(neg.getConn(), usuario);

		// Peluqueria pelu = new Peluqueria("Peluquer�a excelente", "Calle de
		// los peluqueros, 56", "123456");
		// NegocioPeluqueria.getInstance().conectar();
		// NegocioPeluqueria.getInstance().insertarObjeto(NegocioPeluqueria.getInstance().getConnection(),
		// pelu);

	}

	// public static <T> Set<ConstraintViolation<T>> validarMetodo(T clase,
	// String nombreMetodo,
	// Class<Object>[] tiposParametros, Object... parametros) {

	public static <T> Set<ConstraintViolation<T>> validarMetodo(T clase, String nombreMetodo,
			Class<?>[] tiposParametros, Object... parametros) {
		try {
			return validator.forExecutables().validateParameters(clase,
					clase.getClass().getMethod(nombreMetodo, tiposParametros), parametros, Default.class);
		} catch (Exception ex) {
			log.error("Error validando el metodo " + nombreMetodo + " del objeto " + clase);
			log.error("Relanzada como RuntimeException");
			log.error(ex.getMessage());
			log.catching(Level.ERROR, ex);

			throw new RuntimeException(ex);
		}
	}
}
