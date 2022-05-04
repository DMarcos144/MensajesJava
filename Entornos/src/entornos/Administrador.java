package entornos;

public class Administrador extends Usuario {
	private int telefono;

	
	//GETTER y SETTER
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	//CONSTRUCTORES
	
	public Administrador() {
		telefono = 0;
	}
	
	public Administrador(int tel) {
		telefono = tel;
	}
	
	//METODOS
	
}