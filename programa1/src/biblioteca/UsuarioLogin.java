package biblioteca;

public class UsuarioLogin extends Persona {	
	private String nombrLogin;
	private String password;
	
	public UsuarioLogin() {
		
	}
	
	public UsuarioLogin(String nombr,String primApell,String segunApell,String nombrLogin, String password) {
		super(nombr,primApell,segunApell);
		this.nombrLogin = nombrLogin;
		this.setPassword(password);
	}

	public String getNombrLogin() {
		return nombrLogin;
	}
	
	public void setNombrLogin(String nombrLogin) {
		this.nombrLogin = nombrLogin;		
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Se guarda el hash md5 del password, si se pierde la contraseña es irrecuperable
	 * @param nombrLogin
	 */
	public void setPassword(String password) {
		this.password = Utilidades.getMd5(password);
	}
	
	public boolean esCorrecto(String otraPass) {		
		return this.password.equals(Utilidades.getMd5(otraPass));		
	}
	
	@Override
	public void rellenarPersona() {
		super.rellenarPersona();
		System.out.print("Introduzca nombre del usuario para login: ");
		this.nombrLogin=Utilidades.getString();
		System.out.print("Introduzca contraseña                   : ");
		setPassword(Utilidades.getString());				
	}
}
