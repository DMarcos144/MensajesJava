package entornos;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Representa a un usuario de la aplicacion.
 * 
 * Solo contiene el numero de telefono.
 * 
 * @author Daniel Marcos
 * @author Danut Anghel
 * 
 */
public class Usuario {
	private String telefono;

	static Scanner sc;
	//GETTER y SETTER
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	//CONSTRUCTORES
	
	/**
	 * Crea el usuario con el teléfono en blanco. 
	*/
	public Usuario() {
		telefono = "";
	}
	/**
	 *  Recibe un String tel que es un número de teléfono y lo iguala a la variable teléfono.
	 * @param tel Representa el teléfono del usuario.
	 */
	public Usuario(String tel) {
		telefono = tel;
	}
	/**
	 *	Recibe un objeto Usuario y le da ese valor al telefono. 
	 * @param u Representa el Usuario.
	 */
	
	public Usuario(Usuario u) {
		this.telefono = u.getTelefono();
	}
	
	//METODOS
	
	/**
	 * Pide por pantalla nombre de usuario del destinatario y envia un mensaje,
	 * se le puede adjuntar opcionalmente una imagen.
	 * 
	 * El archivo se genera siempre con la fecha y la hora en la que se envió
	 * en la ultima linea del archivo.
	 */
	public static void enviarMensaje() {
		System.out.println("A quién deseas enviar un mensaje?\n");
		String destinatario = Principal.comprobarNumero();
		if (!destinatario.isEmpty()) {
			System.out.println("Escribe el mensaje que quieres enviar:");
			sc = new Scanner(System.in);
			String mensaje = sc.nextLine();
			System.out.print("Deseas adjuntar una imagen? S/N: ");
			String imagen = "";
			do {
				sc = new Scanner(System.in);
				imagen = sc.nextLine();
			} while(!(imagen.toUpperCase().equals("S") || imagen.toUpperCase().equals("N")));
			if (imagen.toUpperCase().equals("S")) {
				System.out.print("Escribe el nombre del archivo: ");
				imagen = sc.nextLine();
			} else
				imagen = null;
			Mensaje msg = new Mensaje(mensaje, imagen);
			try {
				//Creacion de carpetas en caso de que no existan
				
					//carpeta de recibidos en el directorio del destinatario
					File carpeta = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo);
					carpeta.mkdirs();
					
					//carpeta de enviados en el directorio del remitente
					carpeta = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\enviados\\" + destinatario);
					carpeta.mkdirs();
				
				int cantidadArchivos;
					
				cantidadArchivos = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo).list().length;
				System.out.println(new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\enviados\\" + destinatario).list().length);
				
				PrintWriter out = new PrintWriter("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo + "\\" + (cantidadArchivos+1) + ".txt");
				System.out.println(msg.generarMensaje());
				out.print(msg.generarMensaje());
				PrintWriter out2 = new PrintWriter("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\enviados\\" + destinatario + "\\" + (cantidadArchivos+1) + ".txt");
				out2.print(msg.generarMensaje());
				System.out.println("\nMensaje enviado.");
				out.close();
				out2.close();
			} catch (java.io.FileNotFoundException e) {
				System.out.println("\nHubo un error enviando el mensaje\n");
			} catch (NullPointerException e) {
				System.out.println("\nNo se pudo encontrar el directorio de mensajes del destinatario. Cancelando mensaje...\n");
			}
			
		} else {
			System.out.println("No se ha encontrado al destinatario");
		}
	}//fin enviarMensaje()
	
	/**
	 * Pide al usuario que introduzca el numero del remitente y saca por
	 * pantalla todos los mensajes que le ha enviado.
	 */
	public static void leerMensajesRecibidos() {
		System.out.print("Escribe el numero del remitente: ");
		String remitente = Principal.comprobarNumero();
		if (!remitente.isEmpty()) {
			
			 try {
				  String salir = "";
				  int cantidadArchivos = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\recibidos\\" + remitente).list().length;
				  for (int i = cantidadArchivos; i > 0 && !salir.toUpperCase().equals("SALIR");i--) {
				      File myObj = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\recibidos\\" + remitente + "\\" + i + ".txt");
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
			    	System.out.println("Este usuario no te ha enviado mensajes aún");
			    }
		} else {
			System.out.println("No se ha encontrado al remitente");
		}
	}
	
	/**
	 * Pide al usuario que introduzca el numero del destinatario y saca
	 * por pantalla los mensajes que le ha enviado el usuario al destinatario.
	 */
	public static void leerMensajesEnviados() {
		System.out.print("Escribe el numero del destinatario: ");
		String destinatario = Principal.comprobarNumero();
		if (!destinatario.isEmpty()) {
			
			 try {
				  String salir = "";
				  int cantidadArchivos = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo).list().length;
				  for (int i = cantidadArchivos; i > 0 && !salir.toUpperCase().equals("SALIR");i--) {
				      File myObj = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo + "\\" + i + ".txt");
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
			    	System.out.println("No has enviado mensajes a este usuario aún");
			    }
		} else {
			System.out.println("No se ha encontrado al remitente");
		}
	}
	
	/**
	 * Permite agregar un apodo a un usuario.
	 * 
	 * Los apodos son independientes de cada usuario, lo que quiere decir
	 * que solo el usuario puede ver los apodos que ha asignado el.
	 * 
	 * Los demas usuarios no pueden saber que apodo les has puesto.
	 */
	public static void agregarApodo() {
		System.out.println("A qué contacto deseas ponerlo un apodo?");
		String contacto = Principal.comprobarNumero();
		if (!contacto.isEmpty()) {
			if (!contacto.equals(Principal.usuarioActivo)) {
				try {	
	
					System.out.println("Introduce el apodo que quieras ponerle a este contacto: ");
					sc = new Scanner(System.in);
					String apodo = sc.nextLine();
					File carpeta = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos");
					carpeta.mkdirs();
					PrintWriter out = new PrintWriter("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos\\" + contacto + ".txt");
					out.print(apodo);
					out.close();
					System.out.println("Apodo añadido correctamente");
	
				} catch (java.io.FileNotFoundException e) {
					System.out.println("\nHubo un error encontrando el archivo\n");
				} catch (NullPointerException e) {
					System.out.println("\nHubo un error inesperado...\n");
				} 
			} else
				System.out.println("No puedes ponerte un apodo a ti mismo");

		} else {
			System.out.println("Este contacto no existe");
		}

	}
	
	/**
	 * Lista todos los contactos y su apodo en caso de que tenga uno.
	 */
	public static void listarContactos() {
		int num = 1;
		System.out.println("Usuarios:\n");
		for(Usuario i: Principal.usuarios) {
			if (!(i.getTelefono().equals(Principal.usuarioActivo))) {
				System.out.print(num + ". " + i.getTelefono() + " ");
				File file = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos\\" + i.getTelefono() + ".txt");
				if (file.exists()) {
					try {
						System.out.print(" (");
						File myObj = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos\\" + i.getTelefono() + ".txt");
						Scanner myReader = new Scanner(myObj);
						while (myReader.hasNextLine()) {
						  String data = myReader.nextLine();
						  System.out.print(data);
						}
						System.out.print(") ");
					} catch (FileNotFoundException e) {
						
					}
				}
			} else {
				System.out.println(num + ". " + "Tú- " + Principal.usuarioActivo);
			} 
			num++;
			System.out.println("\n");
		}
		System.out.println("\n----------------------------\nAdministradores:\n");
		num = 1;
		for(Administrador i: Principal.admins) {
			if (!(i.getTelefono().equals(Principal.usuarioActivo))) {
				System.out.print(num + ". " + i.getTelefono() + " ");
				File file = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos\\" + i.getTelefono() + ".txt");
				if (file.exists()) {
					try {
						System.out.print(" (");
						File myObj = new File("dCuadrado\\Mensajes\\" + Principal.usuarioActivo + "\\Contactos\\" + i.getTelefono() + ".txt");
						Scanner myReader = new Scanner(myObj);
						while (myReader.hasNextLine()) {
						  String data = myReader.nextLine();
						  System.out.print(data);
						}
						System.out.print(") ");
					} catch (FileNotFoundException e) {
						System.out.println("PITO");
					}
				}
			} else {
				System.out.println(num + ". " + "Tú- " + Principal.usuarioActivo);
			}
			num++;
			System.out.println("\n");
		}
	}


}
