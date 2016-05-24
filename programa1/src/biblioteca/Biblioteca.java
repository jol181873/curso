package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;


/**
 * Singleton
 * @author jol
 *
 */
public class Biblioteca implements Serializable {
	private static Biblioteca biblioteca;
	
	private String nombre="Mi Biblioteca";
	private ArrayList<Libro> listaLibros;
	private ArrayList<Usuario> listaUsuarios;
	private ArrayList<Trabajador> listaTrabajadores;
	private ArrayList<Autor> listaAutores;
	private ArrayList<Prestamo> listaPrestamos;
	private ArrayList<Prestamo> listaMorosos;	
	private boolean esBibliotecario;
	private UsuarioLogin usuarioLogueado;
	
	private Biblioteca() {
		listaLibros=new ArrayList<Libro>();
		listaUsuarios=new ArrayList<Usuario>();
		listaTrabajadores=new ArrayList<Trabajador>();
		listaAutores=new ArrayList<Autor>();
		listaPrestamos=new ArrayList<Prestamo>();
		listaMorosos=new ArrayList<Prestamo>();
	}		
		
	public static Biblioteca getBiblioteca() {
		if (biblioteca==null) {
			biblioteca=new Biblioteca();
		}
		
		return biblioteca;
	}
			
	public boolean isEsBibliotecario() {
		return esBibliotecario;
	}

	public void setEsBibliotecario(boolean esBibliotecario) {
		this.esBibliotecario = esBibliotecario;
	}

	public UsuarioLogin getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioLogin usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public ArrayList<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(ArrayList<Autor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public ArrayList<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public void setListaPrestamos(ArrayList<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public ArrayList<Prestamo> getListaMorosos() {
		return listaMorosos;
	}

	public void setListaMorosos(ArrayList<Prestamo> listaMorosos) {
		this.listaMorosos = listaMorosos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ArrayList<Trabajador> getListaTrabajadores() {
		return listaTrabajadores;
	}

	public void setListaTrabajadores(ArrayList<Trabajador> listaTrabajadores) {
		this.listaTrabajadores = listaTrabajadores;
	}
	
	public void run() {	
		TareaMorosos tarea=new TareaMorosos(listaPrestamos, listaMorosos);
		Timer timer=new Timer(true);
		// comprueba cada 24 horas, los prestamos para encontrar los usuarios 
		// que han sobrepasado su periodo		
		timer.scheduleAtFixedRate(tarea, 0, 24*3600*1000);
		
		if (listaTrabajadores.isEmpty()) {
			Trabajador trabajador=new Trabajador("", "", "", 0, "admin", "admin");
			listaTrabajadores.add(trabajador);
		}
		
		System.out.println("Bienvenido al programa de gestión de la biblioteca "+this.nombre);
		boolean encontrado=false;
		while (!encontrado) {
			System.out.print("Introduzca usuario login     : ");
			String usuarioLogin=Utilidades.getString();
			System.out.print("Introduzca usuario contraseña: ");
			String password=Utilidades.getString();
			
			for (UsuarioLogin login:listaTrabajadores) {
				if (login.getNombrLogin().equals(usuarioLogin)) {
					// el nombre coincide, comparamos los password
					if (login.esCorrecto(password)) {
						esBibliotecario=true;
						encontrado=true;
						usuarioLogueado=login;
						System.out.println("Bienvenido bibliotecario");
					}
				}
			}
			if (!encontrado) {
				for (UsuarioLogin login:listaUsuarios) {
					if (login.getNombrLogin().equals(usuarioLogin)) {
						// el nombre coincide, comparamos los password
						if (login.esCorrecto(password)) {
							esBibliotecario=false;
							encontrado=true;
							usuarioLogueado=login;
							System.out.println("Bienvenido usuario");
						}
					}
				}
				if (!encontrado) {
					System.out.println("Credenciales no válidas");				
				}
			}
		}
		
		int opcion=-1;
		while (opcion!=0) {		
			if (esBibliotecario) {
				System.out.println("1  -- Ver libro");
				System.out.println("2  -- Ver usuario");
				System.out.println("3  -- Ver trabajador");
				System.out.println("4  -- Añadir libro");
				System.out.println("5  -- Añadir usuario");
				System.out.println("6  -- Añadir trabajador");
				System.out.println("7  -- Prestar libro");
				System.out.println("8  -- Devolver libro");
				System.out.println("9  -- Ver libros prestados");
				System.out.println("10 -- Ver libros no prestados");
				System.out.println("11 -- Comprobar retrasos en prestamos");
				System.out.println("0  -- Salir");
				
				opcion=(int)Utilidades.getLongPositivo();
				//Utilidades.clrScr();
				switch (opcion) {
					case 1: verLibro();
							break;
					case 2: verUsuario();
							break;
					case 3: verTrabajador();
							break;
					case 4: aniadirLibro();
							break;
					case 5: aniadirUsuario();
							break;
					case 6: aniadirTrabajador();
							break;
					case 7: prestarLibro();
							break;
					case 8: devolverLibro();
							break;
					case 9: verLibrosPrestados();
							break;
					case 10:verLibrosNoPrestados();
							break;
					case 11:comprobarMorosos();
							break;
					case 0: return;
					default:System.out.println("No he entendido su elección :-("); 
				}
			} else {
				System.out.println("1 -- Ver libro");																
				System.out.println("2 -- Prestar libro");
				System.out.println("3 -- Devolver libro");
				System.out.println("4 -- Ver libros prestados");
				System.out.println("5 -- Ver libros no prestados");
				System.out.println("6 -- Comprobar retrasos en prestamos");
				System.out.println("0 -- Salir");
				
				opcion=(int)Utilidades.getLongPositivo();
				//Utilidades.clrScr();
				switch (opcion) {
					case 1: verLibro();
							break;					
					case 2: prestarLibro(usuarioLogueado);
							break;
					case 3: devolverLibro(usuarioLogueado);
							break;
					case 4: verLibrosPrestados(usuarioLogueado);
							break;
					case 5: verLibrosNoPrestados();
							break;
					case 6: comprobarMorosos(usuarioLogueado);
							break;
					case 0: return;
					default:System.out.println("No he entendido su elección :-("); 
				}
			}
		}		
	}

	private void verLibrosNoPrestados() {
		System.out.println();
		System.out.println();
		
		if (listaLibros.isEmpty()) {
			System.out.println("No hay libros registrados en la biblioteca");
		} else {
			System.out.println("Lista de libros no prestados");
			boolean hayLibrosNoPrestados=false;
			for (Libro libro:listaLibros) {
				if (libro.getPrestamo()==null) {
					System.out.println("==================================");
					libro.pintar();
					hayLibrosNoPrestados=true;
				}
			}		
			if (!hayLibrosNoPrestados) {
				System.out.println("Todos los libros de la biblioteca están prestados");
			}
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void verLibrosPrestados() {
		System.out.println();
		System.out.println();
		
		if (listaLibros.isEmpty()) {
			System.out.println("No hay libros registrados en la biblioteca");
		} else {
			System.out.println("Lista de libros prestados");
			boolean hayLibrosPrestados=false;
			for (Libro libro:listaLibros) {
				if (libro.getPrestamo()!=null) {
					System.out.println("==================================");
					libro.pintar();
					System.out.println("Prestado a : "+libro.getPrestamo().getUsuario().getNombreCompleto()+" (id: "+libro.getPrestamo().getUsuario().getId()+"), hasta el "+Utilidades.getFecha(libro.getPrestamo().getHasta()));
					hayLibrosPrestados=true;
				}
			}
			if (!hayLibrosPrestados) {
				System.out.println("No existe ningún préstamo actualmente en la biblioteca");
			}
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void verLibrosPrestados(UsuarioLogin usuarioLogueado) {
		System.out.println();
		System.out.println();
		
		if (listaLibros.isEmpty()) {
			System.out.println("No hay libros registrados en la biblioteca");
		} else {
			System.out.println("Lista de libros prestados");
			Usuario usuario=(Usuario) usuarioLogueado;
			boolean hayLibrosPrestados=false;
			ArrayList<Libro> listaLibrosUsuario=new ArrayList<Libro>();
			for(Prestamo prestamo:usuario.getListaPrestamos()) {
				listaLibrosUsuario.addAll(prestamo.getListaLibrosEnPrestamo());
			}
			for (Libro libro:listaLibrosUsuario) {
				if (libro.getPrestamo()!=null) {
					System.out.println("==================================");
					libro.pintar();
					System.out.println("Prestado a : "+libro.getPrestamo().getUsuario().getNombreCompleto()+" (id: "+libro.getPrestamo().getUsuario().getId()+"), hasta el "+Utilidades.getFecha(libro.getPrestamo().getHasta()));
					hayLibrosPrestados=true;
				}
			}
			if (!hayLibrosPrestados) {
				System.out.println("No existe ningún préstamo actualmente en la biblioteca");
			}
		}
		
		System.out.println();
		System.out.println();
	}

	private void devolverLibro() {	
		System.out.println();
		System.out.println();
		
		if (listaPrestamos.isEmpty()) {
			System.out.println("No existe ningún préstamo actualmente en la biblioteca");
		} else {			
			long id=-1;
			Usuario usuario=null;
			while (usuario==null && id!=0) {
				System.out.print("Introduzca el identificador del usuario (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				int i=-1;
				for (int j=0;j<listaPrestamos.size();j++) {				
					if (listaPrestamos.get(j).getUsuario().getId()==id) {
						usuario=listaPrestamos.get(j).getUsuario();
						i=j;
						break;
					}
				}
				
				if (usuario==null) {
					System.out.println("Lo lamento, el usuario no tiene ningún prestamo en vigor");
				} else {
					for (Libro libro:listaPrestamos.get(i).getListaLibrosEnPrestamo()) {
						libro.setPrestamo(null);
					}
					usuario.setListaPrestamos(new ArrayList<Prestamo>());
					listaPrestamos.remove(i);
					System.out.println("El usuario ha devuelto todos los libros");
				}
			}		
					
			System.out.println();
			System.out.println();
		}
	}
	
	private void devolverLibro(UsuarioLogin usuarioLogueado) {	
		System.out.println();
		System.out.println();
		
		Usuario usuarioParam=(Usuario) usuarioLogueado;
		
		if (listaPrestamos.isEmpty()) {
			System.out.println("No existe ningún préstamo actualmente en la biblioteca");
		} else {			
			long id=-1;
			Usuario usuario=null;
			while (usuario==null && id!=0) {				
				int i=-1;
				for (int j=0;j<listaPrestamos.size();j++) {				
					if (listaPrestamos.get(j).getUsuario().getId()==usuarioParam.getId()) {
						usuario=listaPrestamos.get(j).getUsuario();
						i=j;
						break;
					}
				}
				
				if (usuario==null) {
					System.out.println("Lo lamento, no tienes ningún prestamo en vigor");
				} else {
					for (Libro libro:listaPrestamos.get(i).getListaLibrosEnPrestamo()) {
						libro.setPrestamo(null);
					}
					usuario.setListaPrestamos(new ArrayList<Prestamo>());
					listaPrestamos.remove(i);
					System.out.println("Has devuelto todos los libros");
				}
			}		
					
			System.out.println();
			System.out.println();
		}
	}

	private void prestarLibro() {
		System.out.println();
		System.out.println();
		
		Prestamo prestamo=new Prestamo();
		Usuario usuarioPrestamo=null;
		
		boolean encontrado=false;
		long id=-1;
		while (!encontrado && id!=0) {
			System.out.print("Introduzca el identificador del usuario (0 -- salir): ");
			id=Utilidades.getLongPositivo();
			for (Usuario usuario:listaUsuarios) {
				if (usuario.getId()==id) {
					if (!usuario.getListaPrestamos().isEmpty()) {
						// hay una tarea por debajo que avisa de la superacion del tiempo de prestamo
						// hasta que se devuelvan los libros no deja coger mas
						
						System.out.println("Lo lamento, el usuario ya tiene un prestamo en vigor");
						return;
					}
					usuarioPrestamo=usuario;
					prestamo.setUsuario(usuario);
					encontrado=true;
					break;
				}
			}
			if (!encontrado) {
				System.out.println("No existe ningún usuario de la biblioteca con ese identificador");
			}
		}
		
		System.out.print("Introduzca el número de libros prestados: ");
		long numeroLibros=Utilidades.getLongPositivo();
		
		for (int i=0;i<numeroLibros;i++) {
			encontrado=false;
			id=-1;
			while (!encontrado && id!=0) {
				System.out.print("Introduzca el identificador del libro (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				for (Libro libro:listaLibros) {
					if (libro.getId()==id) {
						if (libro.getPrestamo()!=null) {
							System.out.println("Lo lamento, pero el libro está prestado hasta la fecha: "+libro.getPrestamo().getHasta());
							encontrado=true;
						} else {
							System.out.println("Se ha añadido al prestamo el libro:");						
							libro.pintar();
							libro.setPrestamo(prestamo);							
							prestamo.getListaLibrosEnPrestamo().add(libro);
							listaPrestamos.add(prestamo);
							usuarioPrestamo.getListaPrestamos().addAll(listaPrestamos);
							encontrado=true;
							break;
						}
					}
				}
				if (!encontrado) {
					System.out.println("No existe ningún libro en la biblioteca con ese identificador");
				}
			}
		}
		
		System.out.println();
		System.out.println();
		
		prestamo.setDesde(new Date());
		// el prestamo dura por narices 15 dias
		Calendar calendario=Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, 15);
		//calendario.add(Calendar.SECOND, 10);
		prestamo.setHasta(calendario.getTime());
		
		String fecha1=Utilidades.getFecha(prestamo.getDesde());
		String fecha2=Utilidades.getFecha(prestamo.getHasta());
		
		System.out.println("El prestamo de los libros durará desde : "+fecha1);
		System.out.println("El prestamo de los libros durará hasta : "+fecha2);
				
		listaPrestamos.add(prestamo);
		
		System.out.println();
		System.out.println();
	}
	
	private void prestarLibro(UsuarioLogin usuarioLogueado) {		
		System.out.println();
		System.out.println();
		
		Usuario usuario=(Usuario) usuarioLogueado;
		if (!usuario.getListaPrestamos().isEmpty()) {
			// hay una tarea por debajo que avisa de la superacion del tiempo de prestamo
			// hasta que se devuelvan los libros no deja coger mas
			
			System.out.println("Lo lamento, ya tienes un prestamo en vigor");
			return;
		}
		
		Prestamo prestamo=new Prestamo();
		prestamo.setUsuario(usuario);
						
		System.out.print("Introduzca el número de libros prestados: ");
		long numeroLibros=Utilidades.getLongPositivo();
		
		boolean encontrado;
		long id;
		for (int i=0;i<numeroLibros;i++) {
			encontrado=false;
			id=-1;
			while (!encontrado && id!=0) {
				System.out.print("Introduzca el identificador del libro (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				for (Libro libro:listaLibros) {
					if (libro.getId()==id) {
						if (libro.getPrestamo()!=null) {
							System.out.println("Lo lamento, pero el libro está prestado hasta la fecha: "+libro.getPrestamo().getHasta());
							encontrado=true;
						} else {
							System.out.println("Se ha añadido al prestamo el libro:");						
							libro.pintar();
							libro.setPrestamo(prestamo);
							prestamo.getListaLibrosEnPrestamo().add(libro);
							listaPrestamos.add(prestamo);
							usuario.getListaPrestamos().addAll(listaPrestamos);
							encontrado=true;
							break;
						}
					}
				}
				if (!encontrado) {
					System.out.println("No existe ningún libro en la biblioteca con ese identificador");
				}
			}
		}
		
		System.out.println();
		System.out.println();
		
		prestamo.setDesde(new Date());
		// el prestamo dura por narices 15 dias
		Calendar calendario=Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, 15);
		//calendario.add(Calendar.SECOND, 10);
		prestamo.setHasta(calendario.getTime());
		
		String fecha1=Utilidades.getFecha(prestamo.getDesde());
		String fecha2=Utilidades.getFecha(prestamo.getHasta());
		
		System.out.println("El prestamo de los libros durará desde : "+fecha1);
		System.out.println("El prestamo de los libros durará hasta : "+fecha2);
				
		listaPrestamos.add(prestamo);
		
		System.out.println();
		System.out.println();
	}

	private void aniadirTrabajador() {		
		Trabajador trabajador=new Trabajador();
		trabajador.rellenarPersona();
		
		int indice=listaTrabajadores.indexOf(trabajador);
		if (indice==-1) {
			listaTrabajadores.add(trabajador);				
			System.out.println("Trabajador añadido");
		} else {
			System.out.println("Ya existe un trabajador con esos datos, no se ha vuelto a añadir");
		}
	}
	
	private void aniadirUsuario() {
		Usuario usuario=new Usuario();
		usuario.rellenarPersona();
		
		int indice=listaUsuarios.indexOf(usuario);
		if (indice==-1) {
			listaUsuarios.add(usuario);				
			System.out.println("Usuario añadido");
		} else {
			System.out.println("Ya existe un usuario con esos datos, no se ha vuelto a añadir");
		}
		
		System.out.println();
		System.out.println();
	}

	private void aniadirLibro() {	
		System.out.println();
		System.out.println();
		System.out.print("Introduzca título: ");
		String titulo=Utilidades.getString();
		System.out.print("Introduzca ISBN  : ");
		String isbn=Utilidades.getString();
		
		Libro libro=new Libro(titulo,isbn);
		
		System.out.print("Introduzca número de autores: ");		
		long numeroAutores=Utilidades.getLongPositivo();
		for (int i=0;i<numeroAutores;i++) {
			System.out.println("     .........................................");
			System.out.print("     Introduzca nombre del autor "+(i+1)+": ");
			String nombreAutor=Utilidades.getString();
			System.out.print("     Introduzca primer apellido : ");
			String primApellAutor=Utilidades.getString();
			System.out.print("     Introduzca segundo apellido: ");
			String segundApellAutor=Utilidades.getString();
					
			Autor autor=new Autor(nombreAutor,primApellAutor,segundApellAutor);
			
			int indice=listaAutores.indexOf(autor);
			if (indice==-1) {
				listaAutores.add(autor);				
			} else {
				autor=listaAutores.get(indice);
			}
			libro.getListaAutores().add(autor);
		}
		System.out.println("Libro añadido");
		System.out.println();
		System.out.println();
		listaLibros.add(libro);				
	}

	private void verTrabajador() {
		System.out.println();
		System.out.println();
		
		if (listaTrabajadores.isEmpty()) {
			System.out.println("No hay trabajadores registrados en la biblioteca");
		} else {
			System.out.println("Lista de todos los bibliotecarios");
			for (Trabajador trabajador:listaTrabajadores) {		
				System.out.println("==================================");
				System.out.println("Identificador : "+trabajador.getId());
				System.out.println("Nombre   : "+trabajador.getNombreCompleto());				
			}		
			
			System.out.println();
			System.out.println();
			
			long id=-1;
			boolean encontrado=false;
			while (!encontrado && id!=0) {
				System.out.print("Introduzca el identificador del trabajador para ver sus datos completos (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				encontrado=false;
				for (Trabajador trabajador:listaTrabajadores) {
					if (trabajador.getId()==id) {
						trabajador.pintar();
						encontrado=true;
						break;
					}
				}
				if (!encontrado && id!=0) {
					System.out.println("No existe un trabajador con ese identificador");
				}
			}
		} 
		
		System.out.println();
		System.out.println();
	}

	private void verUsuario() {
		System.out.println();
		System.out.println();
		
		if (listaUsuarios.isEmpty()) {
			System.out.println("No hay usuarios registrados en la biblioteca");
		} else {
		
			System.out.println("Lista de todos los usuarios");
			for (Usuario usuario:listaUsuarios) {		
				System.out.println("==================================");
				System.out.println("Identificador : "+usuario.getId());
				System.out.println("Nombre        : "+usuario.getNombreCompleto());				
			}		
			
			System.out.println();
			System.out.println();
			
			long id=-1;
			boolean encontrado=false;
			while (!encontrado && id!=0) {
				System.out.print("Introduzca el identificador del usuario para ver sus datos completos (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				encontrado=false;
				for (Usuario usuario:listaUsuarios) {
					if (usuario.getId()==id) {
						usuario.pintar();
						encontrado=true;
						break;
					}
				}
				if (!encontrado && id!=0) {
					System.out.println("No existe un usuario con ese identificador");
				}
			}
		}
		
		System.out.println();
		System.out.println();		
	}

	private void verLibro() {
		System.out.println();
		System.out.println();
		
		if (listaLibros.isEmpty()) {
			System.out.println("No hay libros registrados en la biblioteca");
		} else {
			System.out.println("Lista de todos los libros");
			for (Libro libro:listaLibros) {		
				System.out.println("==================================");
				System.out.println("Identificador   : "+libro.getId());
				System.out.println("Título          : "+libro.getTitulo());
				System.out.println("Autor principal : "+libro.getListaAutores().get(0).getNombreCompleto());			
				System.out.println("ISBN            : "+libro.getISBN());		
			}		
		
			System.out.println();
			System.out.println();
			
			long id=-1;
			boolean encontrado=false;
			while (!encontrado && id!=0) {
				System.out.print("Introduzca el identificador del libro para ver sus datos completos (0 -- salir): ");
				id=Utilidades.getLongPositivo();
				encontrado=false;
				for (Libro libro:listaLibros) {
					if (libro.getId()==id) {
						libro.pintar();
						encontrado=true;
						break;
					}
				}
				if (!encontrado && id!=0) {
					System.out.println("No existe un libro con ese identificador");
				}
			}
		}
		
		System.out.println();
		System.out.println();
	}
	
	public void comprobarMorosos() {
		System.out.println();
		System.out.println();
		if (listaMorosos.isEmpty()) {
			System.out.println("No hay retrasos en los prestamos");
		} else {
			for (Prestamo prestamo:listaMorosos) {
				System.out.println("======================================");
				System.out.println("Prestamo a    : "+prestamo.getUsuario().getNombreCompleto());
				System.out.println("Devolucion el : "+prestamo.getHasta());
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public void comprobarMorosos(UsuarioLogin usuarioLogueado) {
		System.out.println();
		System.out.println();
		if (listaMorosos.isEmpty()) {
			System.out.println("No hay retrasos en los prestamos");
		} else {
			Usuario usuario=(Usuario) usuarioLogueado;
			boolean encontrado=false;
			for (Prestamo prestamo:listaMorosos) {
				if (prestamo.getUsuario().getId()==usuario.getId()) {
					System.out.println("======================================");
					System.out.println("Prestamo a    : "+prestamo.getUsuario().getNombreCompleto());
					System.out.println("Devolucion el : "+prestamo.getHasta());
					encontrado=true;
				}
			}
			if (!encontrado) {
				System.out.println("No tiene retrasos en sus prestamos");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {		
		File fic=new File("datos_biblioteca.ser");
		Biblioteca biblioteca;
		if (fic.exists()) {
			System.out.println("Cargando datos al inicio");
			ObjectInputStream inStream=new ObjectInputStream(new FileInputStream(fic));
			biblioteca=(Biblioteca) inStream.readObject();
			inStream.close();
			System.out.println("Libros        : "+biblioteca.getListaLibros().size());
			System.out.println("Usuarios      : "+biblioteca.getListaUsuarios().size());
			System.out.println("Trabajadores  : "+biblioteca.getListaTrabajadores().size());
			System.out.println("Prestamos     : "+biblioteca.getListaPrestamos().size());
			System.out.println("Autores       : "+biblioteca.getListaAutores().size());
			System.out.println("Retrasos      : "+biblioteca.getListaMorosos().size());
			Autor.setAutoinc(biblioteca.listaAutores.size());
			Libro.setAutoinc(biblioteca.listaLibros.size());
			Trabajador.setAutoinc(biblioteca.listaTrabajadores.size());
			Usuario.setAutoinc(biblioteca.listaUsuarios.size());
		} else {
			System.out.println("No hay datos antiguos que cargar");
			biblioteca=new Biblioteca();
		}
		
		biblioteca.run();
		
		System.out.println("Guardando datos al finalizar");
		ObjectOutputStream outStream=new ObjectOutputStream(new FileOutputStream(fic));
		outStream.writeObject(biblioteca);
		outStream.close();
	}	
}
