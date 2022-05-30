package entornos;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
	
	public Usuario() {
		telefono = "";
	}
	
	public Usuario(String tel) {
		telefono = tel;
	}
	public Usuario(Usuario u) {
		this.telefono = u.getTelefono();
	}
	
	//METODOS
	
	public static void pruebau() {
		System.out.println("Hola");
	}
	
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
}
