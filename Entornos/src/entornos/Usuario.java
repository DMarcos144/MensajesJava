package entornos;

public class Usuario {
	private String telefono;

	
	//GETTER y SETTER
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	//CONSTRUCTORES
	
	public Usuario() {
		telefono = "";
	}
	
	public Usuario(String tel) {
		telefono = tel;
	}
	
	//METODOS
	
	public static void pruebau() {
		System.out.println("Hola");
	}
	
}
