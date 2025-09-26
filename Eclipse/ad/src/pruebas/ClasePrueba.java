package pruebas;

import java.io.File;

public class ClasePrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("La carpeta de mi usuario es "
				+ System.getProperty("user.home"));
		File miFichero = new File(System.getProperty("user.home"), "fichero.txt");
	}

}
