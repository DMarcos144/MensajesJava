package entornos;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

/**
 * Representa a un mensaje que los usuarios pueden enviar.
 * 
 * Contiene un el mensaje en sí y opcionalmente una imagen.
 * 
 * @author Daniel Marcos
 * @author Danut Anghel
 *
 */
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
	
	/**
	 * Recibe un String mensaje y lo iguala a la variable mensaje.
	 * @param mensaje Representa el menasje del usuario.
	 */
	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
		imagen = null;
	}
	/**
	 * Recibe dos String (mensaje e imagen) y lo iguala a sus respectivas variables.
	 * @param mensaje Representa el mensaje del usuario
	 * @param imagen Representa la imagen que envía el usuario
	 */
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