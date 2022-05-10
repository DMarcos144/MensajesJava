package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	
	public static void registro(int num) {
		for (Usuario i:  usuarios) {
			if (i.getTelefono() == num)
				administrador = false; 
		}
		for (Administrador i:  admins) {
			if (i.getTelefono() == num)
				administrador = true;
		}
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
		
		registro(123456789);
		
		System.out.println(administrador);
	}
}
