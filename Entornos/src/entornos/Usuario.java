package entornos;

public class Usuario {
	private int telefono;

	
	//GETTER y SETTER
	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	//CONSTRUCTORES
	
	public Usuario() {
		telefono = 0;
	}
	
	public Usuario(int tel) {
		telefono = tel;
	}
	
	//METODOS
	
}
