package ejercicio1;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Del SO
System.out.println("El Sistema operativo es: "+System.getProperty("os.name"));
System.out.println("La version del so es: "+System.getProperty("os.version"));
System.out.println("Su arquitectura es: "+ System.getProperty("os.arch"));
		//Del User
System.out.println("Directorio HOME"+ System.getProperty("user.home"));
System.out.println("Nombre"+ System.getProperty("user.name"));
System.out.println("Nombre del directorioi donde se ejecuta el programa: "+System.getProperty("user.dir"));


		//De java
System.out.println("Directorio HOME"+ System.getProperty("user.home"));
System.out.println("Version"+ System.getProperty("os.arch"));
System.out.println("Nombre del distribuidor"+ System.getProperty("java.vendor"));
System.out.println("URL del distribuidor"+ System.getProperty("java.vendor.url"));
		//Del Sistema de archivo
System.out.println("Caracter especial para rutas: "+ System.getProperty("file.separator"));
System.out.println("CE para separar lineas de ficheros de texto: "+ System.getProperty("line.separator"));
	}

}
