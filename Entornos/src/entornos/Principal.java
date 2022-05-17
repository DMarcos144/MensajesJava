package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	static boolean administrador;
	
	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	static ArrayList<Administrador> admins = new ArrayList<Administrador>();
	
	public static void crearCarpetas() throws FileNotFoundException {
		File carpeta = new File("C:\\dCuadrado");
		carpeta.mkdir();
		
		File carpetaUsuarios = new File("C:\\dCuadrado\\Usuarios");
		carpetaUsuarios.mkdir();
		
		carpetaUsuarios = new File("C:\\dCuadrado\\Admins");
		carpetaUsuarios.mkdir();
	}
	
	public static int pedirNumero() {
		int num = 0;
		for (boolean error = true ; error ;) {
			error = false;
			try {
				Scanner sc = new Scanner(System.in);
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
	
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		try {
			crearCarpetas();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println();
				
		usuarios.add(new Usuario("123456789"));
		usuarios.add(new Usuario("987654321"));
		
		admins.add(new Administrador("123123123"));
		boolean salir = false;
		while (!salir) {
			System.out.print("Selecciona una opción (1-8): ");
			int eleccion = pedirNumero();
			String num;
			switch (eleccion) {
				case 1:
						for(boolean valido = false ; !valido;) {
							
							try {
								System.out.print("\nIntroduce el numero de usuario: ");
								Scanner sc = new Scanner(System.in);
								num = Integer.toString(sc.nextInt());
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
									if (administrador)
										System.out.println("\nTe has registrado como administrador");
									else {
										System.out.println("\nTe has registrado como usuario");
									}
								}
							} catch (InputMismatchException e) {
								System.out.println("\nSe han introducido caracteres no válidos");
								num = "111111111";
						}
					}
				break;
				default:
					System.out.println("a");
			}
		}
	}
}