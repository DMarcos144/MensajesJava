package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Representa a un Usuario con derechos de administrador.
 * 
 * Administrador es una clase heredada de Usuario.
 * 
 * @author Daniel Marcos
 * @author Danut Anghel
 *
 */
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
	
	/**
	 * Permite a un administrador leer mensajes que se han
	 * intercambiado otros usuarios entre sí
	 */
	public static void leerMensajesAdmin() {
		System.out.print("Escribe el numero del remitente: ");
		String remitente = Principal.comprobarNumero();
		String destinatario = Principal.comprobarNumero();
		if (!remitente.isEmpty() || !destinatario.isEmpty()) {
			
			 try {
				  String salir = "";
				  int cantidadArchivos = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + remitente).list().length;
				  for (int i = cantidadArchivos; i > 0 && !salir.toUpperCase().equals("SALIR");i--) {
				      File myObj = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + remitente + "\\" + i + ".txt");
				      Scanner myReader = new Scanner(myObj);
				      System.out.println("\nMensaje numero " + (i)+ "\n--------------------------------------------");
				      while (myReader.hasNextLine()) {
				        String data = myReader.nextLine();
				        System.out.println(data);
				      }
				      System.out.print("--------------------------------------------\nPulsa enter para leer el anterior mensaje o escribe SALIR: ");
				      sc = new Scanner(System.in);
				      salir = sc.nextLine();
				  }
	
			    } catch (FileNotFoundException e) {
			      System.out.println("Hubo un error");
			      e.printStackTrace();
			    } catch(NullPointerException e) {
			    	System.out.println("No hay ningún mensaje recibido");
			    }
		} else {
			System.out.println("No se ha encontrado al remitente");
		}
	}
}