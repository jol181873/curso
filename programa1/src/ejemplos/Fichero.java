package ejemplos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fichero {
	public static final String MYPATH="src/Ficheros";
	public static String rutaActual;
	public static ArrayList<Persona>listaPersonas = new ArrayList<Persona>();
	
	public static void main(String[] args) {
		ArrayList<Persona> oread;
		String fileNom ="personas.dat";
		String directNom="archivos";
		Persona p;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fecha = Calendar.getInstance();
		int indexToDelete = -1;
		
		
			try {
				fecha.setTime(sdf.parse("05/05/1984"));
				listaPersonas.add(new Persona("12345678Z","Juan",fecha));
				fecha = Calendar.getInstance();
				fecha.setTime(sdf.parse("15/12/1985"));
				listaPersonas.add(new Persona("11111111H","Pepe",fecha));
				fecha = Calendar.getInstance();
				fecha.setTime(sdf.parse("30/05/1974"));
				listaPersonas.add(new Persona("88888888A","Antonio",fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			
		
		crearFichero(fileNom,directNom,listaPersonas);
		oread=leerFichero(rutaActual+"/"+fileNom);
		System.out.println("Lectura de personas:");
		if(!esNull(oread)){
			if(!oread.isEmpty()){
				for(int i=0;i<oread.size();i++){					
					if(oread.get(i) instanceof Persona){						
						p=(Persona)oread.get(i);
						System.out.println("Leemos: "+p.getNombre()+" DNI: "+p.getDni()+" Fecha Nacimiento: "+sdf.format(p.getFechaNacimiento().getTime()));
						if(p.getDni().equals("11111111H")){
							indexToDelete=i;
						}
					}
				}
				
				if(indexToDelete>=0){
					p=(Persona)oread.get(indexToDelete);
					System.out.println("Borramos a: "+p.getNombre()+" DNI: "+p.getDni()+" Fecha Nacimiento: "+sdf.format(p.getFechaNacimiento().getTime()));
					oread.remove(indexToDelete);
					saveData(fileNom, directNom, oread);
				}
			}
			System.out.println("Lectura de objetos: ");
			for(int i=0;i<oread.size();i++){
				if(oread.get(i) instanceof Persona){
					p=(Persona)oread.get(i);
					System.out.println("Leemos: "+p.getNombre()+" DNI: "+p.getDni()+" Fecha Nacimiento: "+sdf.format(p.getFechaNacimiento().getTime()));
				}
			}
		}			
	}
	
	private static boolean esNull(ArrayList<Persona> objeto){
		try{
			if(objeto== null){
				return true;
			}else{
				return false;
			}
			
		}catch(NullPointerException e){
			return true;
		}
	}
	
	public static ArrayList<Persona>leerFichero(String ruta){
		FileInputStream fis = null;
		ArrayList<Object> lista;
		ObjectInputStream lecturaObjeto = null;
		ArrayList<Persona> grupo = new ArrayList<Persona>();
		try{
			if(existeFichero(ruta)){
				fis= new FileInputStream(ruta);
				lecturaObjeto = new ObjectInputStream(fis);
				lista =(ArrayList<Object>)lecturaObjeto.readObject();
				for(int i=0;i<lista.size();i++){
					if((lista.get(i)!=null)&&(lista.get(i) instanceof Persona)){
						grupo.add((Persona)lista.get(i));					
					}
				}
				return grupo;
			}else{
				System.out.println("Fichero o ruta no existe");
				return null;
			}
			
		}catch(FileNotFoundException e){
			System.out.println("Fichero no existe");
			return null;
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
			return null;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}finally{
			try{
				if(fis!=null){
					fis.close();
				}
				if (lecturaObjeto!=null){
					lecturaObjeto.close();
				}
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static boolean saveData(String nombreArchivo,String nombreDirectorio,ArrayList<Persona>Lista){
		if(existeFichero(rutaActual+"/"+nombreArchivo)){
			File f = new File(rutaActual+"/"+nombreArchivo);
			if(f.delete()){
				return crearFichero(nombreArchivo,nombreDirectorio,Lista);
			}else{
				System.out.println("Error al eliminar el fichero");
				return false;
			}
		}else{
			return crearFichero(nombreArchivo,nombreDirectorio,Lista);
		}
		
	}
	
	public static boolean crearFichero(String nombreArchivo, String nombreDirectorio,ArrayList<Persona> lista){
		FileOutputStream fos= null;
		ObjectOutputStream escribeObjetos =null;
		try{
			 if(nuevoDirectorio(nombreDirectorio)||existeDirectorio(nombreDirectorio)){
				if(!existeFichero(rutaActual+"/"+nombreArchivo)){
					 fos = new FileOutputStream(rutaActual+"/"+nombreArchivo);
					 escribeObjetos = new ObjectOutputStream(fos);
					 escribeObjetos.writeObject(lista);
					 System.out.println("El fichero ha sido creado");
					 return true;
				}else{
					System.out.println("El fichero ya existe");
					return false;
				}
			 }else{
				 System.out.println("Fichero no creado");
				 return false;
			 }
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			return false;
		}catch(IOException e){
			System.out.println(e.getMessage());
			return false;
			
		}finally{
			try{
				if(fos!=null){
					fos.close();
				}
				if(escribeObjetos!=null){
					escribeObjetos.close();
				}
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	
	
	private static boolean existeFichero(String ruta){
		File f = new File(ruta);
		if(f.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean existeDirectorio(String nombreDirectorio){
		File dir = new File(MYPATH+"/"+nombreDirectorio);
		if(dir.exists()){
			rutaActual = dir.getAbsolutePath();
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean nuevoDirectorio(String nombreDirectorio){
		File dir = new File(MYPATH+"/"+nombreDirectorio);
		if(!dir.exists()){
			try{
				rutaActual = dir.getAbsolutePath();
				return dir.mkdirs();
			}catch(SecurityException e){
				System.out.println("Error al crear el directorio");
				return false;
			}
		}else{
			System.out.println("El directorio existe");
			return false;
		}
	}

}
