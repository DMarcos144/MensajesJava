package entornos;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Mensaje {


	String mensaje;

	String imagen;
	
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	static LocalDateTime now = LocalDateTime.now();  
	   
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
//CONSTRUCTORES
	
	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
		imagen = null;
	}

	public Mensaje(String mensaje,String imagen) {

		this.mensaje = mensaje;
		this.imagen = imagen;
	}

//METODOS

	public String generarMensaje() {
		String resultado = this.getMensaje();
		if (!(this.getImagen() == null))
			resultado = resultado + "\n\nImagen: " + this.getImagen();
		resultado = resultado + "\n\n" + dtf.format(now);
		return resultado;
	}
}