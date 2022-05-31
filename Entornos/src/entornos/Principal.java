package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal del programa, contiene los Arrays de usuarios
 * y las variables usuarioActivo( el usuario que está iniciado sesion)
 * y administrador (detrmina si el usuario que tiene iniciada la sesión
 * es administrador o no).
 * 
 * @author Daniel Marcos
 * @author Danut Anghel
 *
 */
public abstract class Principal {

	static boolean administrador;
	
	static String usuarioActivo;

	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	static ArrayList<Administrador> admins = new ArrayList<Administrador>();
	
	static Scanner sc;
	
	/**
	 * 
	 * Lee todos los usuarios que hay dados de alta y crea sus carpetas personales
	 * dentro de la carpeta del programa (dCuadrado).
	 * 
	 * @throws FileNotFoundException
	 */
	public static void crearCarpetas() throws FileNotFoundException {
		File carpeta = new File("dCuadrado");
		carpeta.mkdir();
		
		File carpetaUsuarios = new File("dCuadrado\\Mensajes");
		carpetaUsuarios.mkdir();
		
		for (Usuario i : usuarios) {
			carpeta = new File("dCuadrado\\Mensajes\\" + i.getTelefono() + "\\enviados");
			carpeta.mkdirs();
			carpeta = new File("dCuadrado\\Mensajes\\" + i.getTelefono() + "\\recibidos");
			carpeta.mkdirs();
		}
		for (Administrador i : admins) {
			carpeta = new File("dCuadrado\\Mensajes\\" + i.getTelefono() + "\\enviados");
			carpeta.mkdirs();
			carpeta = new File("dCuadrado\\Mensajes\\" + i.getTelefono() + "\\recibidos");
			carpeta.mkdirs();
		}
	}
	
	/**
	 * Pide un numero por pantalla y captura errores en caso de que se
	 * introduzcan letras o caracteres especiales
	 * 
	 * @return un numero entero
	 */
	public static int pedirNumero() {
		int num = 0;
		for (boolean error = true ; error ;) {
			error = false;
			try {
				sc = new Scanner(System.in);
				num = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nPor favor, introduzca solo numeros");
				error = true;
			  }
		}
		return num;
	}
	
	/**
	 * Comprueba el numero y lo busca en todos los contactos, en caso de que exista
	 * la funcion devuelve true, en caso contrario devuelve false.
	 * Además, asigna valor a la variable estatica "administrador".
	 * 
	 * @param num el numero de telefono de un usuario
	 * @return valor booleano
	 */
	public static boolean registro(String num) {
		boolean usuarioEncontrado = false;
		for (Usuario i:  usuarios) {
			if (i.getTelefono().equals(num)) {
				administrador = false;
				usuarioEncontrado = true;
			}
		}
		for (Administrador i:  admins) {
			if (i.getTelefono().equals(num)) {
				administrador = true;
				usuarioEncontrado = true;
			}
		}
		return usuarioEncontrado;
	}
	
	/**
	 * Función similar al metodo registro() con la unica diferencia de que
	 * este metodo solo comprueba si existe el usuario y no el valor de la
	 * variable "administrador".
	 * 
	 * @param num el numero de telefono de un usuario
	 * @return valor booleano
	 */
	public static boolean registroSinCambio(String num) {
		boolean usuarioEncontrado = false;
		for (Usuario i:  usuarios) {
			if (i.getTelefono().equals(num)) {
				usuarioEncontrado = true;
			}
		}
		for (Administrador i:  admins) {
			if (i.getTelefono().equals(num)) {
				usuarioEncontrado = true;
			}
		}
		return usuarioEncontrado;
	}

	/**
	 * Funcion que se encarga de pedir un numero por pantalla constantemente hasta que
	 * encuentre un usuario valido para registrarse.
	 * 
	 * Para saber el usuario activo, el programa utiliza la variable "usuarioActivo"
	 * y para saber si tiene o no derechos de administrador, utiliza la variable
	 * "administrador·.
	 */
	public static void iniciarSesion() {
		String num;
		for(boolean valido = false ; !valido;) {

			try {
				System.out.print("\nIntroduce el numero de usuario: ");
				sc = new Scanner(System.in);
				num = Long.toString(sc.nextLong());
				valido = registro(num);
				if (!valido) {
					if (num.length() <9)
						System.out.println("\nEl numero tiene menos de 9 digitos");
					else if (num.length() >9) {
						System.out.println("\nEl numero tiene más de 9 digitos");
	 				}
					else if (!valido)
						System.out.println("\nUsuario no encontrado");
				} else {
					if (administrador) {
						System.out.println("\nTe has registrado como administrador\n");
						usuarioActivo = num;
					}else {
						System.out.println("\nTe has registrado como usuario");
						usuarioActivo = num;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nSe han introducido caracteres no válidos");
			}
		}
	}
	
	/**
	 * Pide numero de telefono al usuario para comprobar si el formato está bien
	 * (9 numeros, sin letras ni espacios), en caso de que el formato del numero
	 * sea valido, lo devuelve, en caso de que no lo sea, devuelve es String vacío.
	 * 
	 * @return numero de telefono o string vacío
	 */
	public static String comprobarNumero() {
		String num;
		String resultado = "";

			try {

				System.out.print("\nIntroduce el numero de usuario: ");
				sc = new Scanner(System.in);
				num = Long.toString(sc.nextLong());
				boolean valido = registroSinCambio(num);
				if (!valido) {
					if (num.length() <9)
						System.out.println("\nEl numero tiene menos de 9 digitos");
					else if (num.length() >9) {
						System.out.println("\nEl numero tiene más de 9 digitos");
					}
					else if(!valido) {
						System.out.println("\nUsuario no encontrado");
					}					
				} else {
					System.out.println("");
					resultado = num;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nSe han introducido caracteres no válidos o demasiados caracteres");
			}
			return resultado;
	}

	/**
	 * Funcion main de la clase principal, mete a los usuarios y administradores dentro de los arrays.
	 * 
	 * Contiene los switches para las opciones de Usuarios y administradores.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.out.println("");
		usuarios.add(new Usuario("123456789"));
		usuarios.add(new Usuario("987654321"));
		usuarios.add(new Usuario("697465214"));
		usuarios.add(new Usuario("744567697"));
		
		admins.add(new Administrador("123123123"));
		admins.add(new Administrador("111111111"));
		
		
		try {
			crearCarpetas();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		
		Usuario.listarContactos();
		
		boolean salir = false;
		while (!salir) {
			if (usuarioActivo == null)
				iniciarSesion();
			
			if(!administrador) {
				System.out.println("\n	Menú de Usuario");
				System.out.println("\n1: Enviar mensaje");
				System.out.println("\n2: Ver mensajes enviados");
				System.out.println("\n3: Ver mensajes recibidos");
				System.out.println("\n4: Ver lista de contactos");
				System.out.println("\n5: Asociar un apodo a un contacto");
				System.out.println("\n0: Cerrar sesión");
				
				int eleccion = pedirNumero();
				switch (eleccion) {
				case 1:
					Usuario.enviarMensaje();													
				break;
				case 2:
					Usuario.leerMensajesEnviados();
				break;
				case 3:
					Usuario.leerMensajesRecibidos();
				break;
				case 4:
					Usuario.listarContactos();
				break;
				case 5:
					Usuario.agregarApodo();
				break;
				case 0:
					usuarioActivo = null;
				break;
				default:
					System.out.println("No es una opción válida.");
				}
			} else {
				System.out.println("\n	Menú de Administr0ador");
				System.out.println("\n1: Enviar mensaje");
				System.out.println("\n2: Ver mensajes enviados");
				System.out.println("\n3: Ver mensajes recibidos");
				System.out.println("\n4: Ver lista de contactos:");
				System.out.println("\n5: Asociar un apodo a un contacto");
				System.out.println("\n6: Ver mensajes de un contacto");
				System.out.println("\n0: Cerrar sesión");
				
				int eleccion = pedirNumero();
				switch (eleccion) {
				case 1:
					Administrador.enviarMensaje();													
				break;
				case 2:
					Administrador.leerMensajesEnviados();
				break;
				case 3:
					Administrador.leerMensajesRecibidos();
				break;
				case 4:
					Administrador.listarContactos();
				break;
				case 5:
					Administrador.agregarApodo();
				break;
				case 6:
					Administrador.leerMensajesAdmin();
				break;
				case 0:
					usuarioActivo = null;
				break;
				default:
					System.out.println("No es una opción válida.");
				}
			}
		}
	}
}