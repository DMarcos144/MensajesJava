package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	static boolean administrador;
	
	static String usuarioActivo;

	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	static ArrayList<Administrador> admins = new ArrayList<Administrador>();
	
	static Scanner sc;
	
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
	
	public static Object sacarUsuario(String num) {
		Object usuarioEncontrado = new Object();
		for (Usuario i:  usuarios) {
			if (i.getTelefono().equals(num)) {
				administrador = false;
				usuarioEncontrado = new Usuario(i);
			}
		}
		for (Administrador i:  admins) {
			if (i.getTelefono().equals(num)) {
				administrador = true;
				usuarioEncontrado = new Administrador(i);
			}
		}
		return usuarioEncontrado;
	}

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

	public static void main(String[] args){
		// TODO Auto-generated method stub
		usuarios.add(new Usuario("123456789"));
		usuarios.add(new Usuario("987654321"));
		
		admins.add(new Administrador("123123123"));
		
		try {
			crearCarpetas();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		
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
				System.out.println("\n0: Cerrar sesión");
			} else {
				System.out.println("\n	Menú de Administr0ador");
				System.out.println("\n1: Enviar mensaje");
				System.out.println("\n2: Ver mensajes enviados");
				System.out.println("\n3: Ver mensajes recibidos");
				System.out.println("\n4: Ver lista de contactos:");
				System.out.println("\n5: Ver mensajes recibidos de un contacto");
				System.out.println("\n6: Ver mensajes enviados de un contacto");
				System.out.println("\n0: Cerrar sesión");
			}
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
				System.out.println("Mostrando lista de contactos: ");
			break;	
			case 69:
				sc = new Scanner(System.in);
			break;
			case 0:
				usuarioActivo = null;
			break;
			default:
				System.out.println("a");
			}
		}
	}
}