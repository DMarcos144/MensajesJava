package entornos;

/**
 * Contiene el nombre y el tama�o de un archivo de imagen.
 * 
 * @author Daniel Marcos
 * @author Danut Anghel
 *
 */
public class Imagen {
		
		private String nombre;
		private String tamano;
		
		//Getters y Setters
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getTamano() {
			return tamano;
		}
		public void setTamano(String tamano) {
			this.tamano = tamano;
		}
		
		//Constructores
		
		/**
		 * Recibe dos String (nombre y tama�o) y los iguala a sus respectivos valores.
		 * @param nombre Recibe el nombre que tendr� la imagen.
		 * @param tamano Recibe el tama�o que tendr� la imagen.
		 */
		
		public Imagen(String nombre, String tamano) {

			this.nombre = nombre;
			this.tamano = tamano;
		}
		
		/**
		 * Constructor que crea el nombre y el tama�o de la imagen y lo deja en blanco.
		 */
		
		public Imagen() {
			this.nombre = null;
			this.tamano = null;
		}
		
		
}
