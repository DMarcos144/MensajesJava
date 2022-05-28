package entornos;

public class Administrador extends Usuario{
	private String telefono;

	
	//GETTER y SETTER
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	//CONSTRUCTORES
	
	public Administrador() {
		telefono = "";
	}
	
	public Administrador(String tel) {
		telefono = tel;
	}
	
	public Administrador(Usuario u) {
		super(u);
	}
	
	//METODOS
	
	public static void prueba() {
		System.out.println("Hola");
	}
}