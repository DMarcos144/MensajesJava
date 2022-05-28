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
				File carpeta = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo);
				carpeta.mkdirs();
				
				int cantidadArchivos;
					
				cantidadArchivos = new File("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo).list().length;
				
				
				PrintWriter out = new PrintWriter("dCuadrado\\Mensajes\\" + destinatario + "\\recibidos\\" + Principal.usuarioActivo + "\\" + (cantidadArchivos+1) + ".txt");
				System.out.println(msg.generarMensaje());
				out.print(msg.generarMensaje());
				System.out.println("\nMensaje enviado.");
				out.close();
			} catch (java.io.FileNotFoundException e) {
				System.out.println("\nHubo un error enviando el mensaje\n");
			} catch (NullPointerException e) {
				System.out.println("\nNo se pudo encontrar el directorio de mensajes del destinatario. Cancelando mensaje...\n");
			}
			
		} else {
			System.out.println("No se ha encontrado al destinatario");
		}
	}
	
}
