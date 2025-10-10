package ejerciocio3;

import java.io.File;

public class InfoFichero {
    public static void main(String[] args) {
        String ruta;

        // Si no se pasa argumento, usar ruta por defecto
        if (args.length == 0) {
          
            ruta = "/Users/alumnado/Desktop/archivo.txt"; // Para Mac/Linux
          
        } else {
            ruta = args[0];
        }

        File f = new File(ruta);

        if (!f.exists()) {
            System.out.println("El fichero o directorio no existe: " + ruta);
            return;
        }

        System.out.println("Nombre: " + f.getName());
        System.out.println("Ruta: " + f.getPath());
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());

        if (f.isFile()) {
            System.out.println("Es un fichero.");
        } else if (f.isDirectory()) {
            System.out.println("Es un directorio.");
        }

        System.out.println("Se puede leer: " + f.canRead());
        System.out.println("Se puede escribir: " + f.canWrite());
        System.out.println("Tamaño (bytes): " + f.length());
        System.out.println("Directorio padre: " + f.getParent());
    }
}
