package entornos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File carpeta = new File("dCuadrado");
		carpeta.mkdir();
		
		File carpetaUsuarios = new File("dCuadrado\\Usuarios");
		carpetaUsuarios.mkdir();
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario(123456789));
		usuarios.add(new Usuario(987654321));
		
		ArrayList<Administrador> admins = new ArrayList<Administrador>();
		admins.add(new Administrador(123123123));
		//PrintWriter out = new PrintWriter(direccion + "filename.txt");
		//semento
	}

}
