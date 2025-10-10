package ejercicio2;

import java.io.File;

public class ListarDirectorios {
    public static void main(String[] args) {
        // 1. Ruta a usar: argumento o user.home\AD
        String ruta;
        if (args.length > 0) {
            ruta = args[0];
        } else {
            String userHome = System.getProperty("user.home");
            ruta = userHome + File.separator + "AD";
        }

        File directorio = new File(ruta);

        // 2. Comprobar si existe y es un directorio
        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("La ruta especificada no existe o no es un directorio: " + ruta);
            return;
        }

        // 3. Listar contenido
        File[] archivos = directorio.listFiles();
        if (archivos == null || archivos.length == 0) {
            System.out.println("El directorio está vacío.");
            return;
        }

        System.out.println("Contenido de: " + ruta);
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                System.out.println("[DIR]  " + archivo.getName());
            } else if (archivo.isFile()) {
                System.out.println("[FILE] " + archivo.getName());
            } else {
                System.out.println("[OTRO] " + archivo.getName());
            }
        }
    }
}

