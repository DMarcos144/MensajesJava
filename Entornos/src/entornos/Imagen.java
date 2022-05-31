package entornos;

/**
 * Contiene el nombre y el tamaño de un archivo de imagen.
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
		
		public Imagen(String nombre, String tamano) {

			this.nombre = nombre;
			this.tamano = tamano;
		}
		
		public Imagen() {
			this.nombre = null;
			this.tamano = null;
		}
		
		
}
