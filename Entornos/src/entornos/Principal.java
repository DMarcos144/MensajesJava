package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	static boolean administrador;
	
	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	static ArrayList<Administrador> admins = new ArrayList<Administrador>();
	
	static Scanner sc = new Scanner(System.in);
	
	public static void crearCarpetas() throws FileNotFoundException {
		File carpeta = new File("C:\\dCuadrado");
		carpeta.mkdir();
		
		File carpetaUsuarios = new File("C:\\dCuadrado\\Usuarios");
		carpetaUsuarios.mkdir();
		
		carpetaUsuarios = new File("C:\\dCuadrado\\Admins");
		carpetaUsuarios.mkdir();
	}
	
	public static boolean registro(int num) {
		boolean usuarioEncontrado = false;
		for (Usuario i:  usuarios) {
			if (i.getTelefono() == num) {
				administrador = false;
				usuarioEncontrado = true;
			}
		}
		for (Administrador i:  admins) {
			if (i.getTelefono() == num) {
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
				
		usuarios.add(new Usuario(123456789));
		usuarios.add(new Usuario(987654321));
		
		admins.add(new Administrador(123123123));
		
		for(boolean valido = false ; !valido;) {
			System.out.print("\nIntroduce el numero de usuario: ");
			int num = sc.nextInt();
			valido = registro(num);
			if (!valido) {
				if (String.valueOf(num).length() <9)
					System.out.println("\nEl numero tiene menos de 9 digitos");
				else if (String.valueOf(num).length() >9) {
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
		}
	}
}
