package ejercicio4;

import java.io.File;
import java.io.IOException;

public class GestionDirectorios {
    public static void main(String[] args) {
        try {
            // 1. Crear directorio NUEVODIR en el directorio actual
            File dir = new File("NUEVODIR");
            if (dir.mkdir()) {
                System.out.println("Directorio creado: " + dir.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio (puede que ya exista).");
            }

            // 2. Crear dos ficheros .txt vacíos en el directorio
            File fichero1 = new File(dir, "fichero1.txt");
            File fichero2 = new File(dir, "fichero2.txt");

            if (fichero1.createNewFile()) {
                System.out.println("Fichero creado: " + fichero1.getName());
            } else {
                System.out.println("No se pudo crear " + fichero1.getName());
            }

            if (fichero2.createNewFile()) {
                System.out.println("Fichero creado: " + fichero2.getName());
            } else {
                System.out.println("No se pudo crear " + fichero2.getName());
            }

            // 3. Renombrar el primer fichero
            File ficheroRenombrado = new File(dir, "fichero1_renombrado.txt");
            if (fichero1.renameTo(ficheroRenombrado)) {
                System.out.println("Fichero renombrado a: " + ficheroRenombrado.getName());
            } else {
                System.out.println("No se pudo renombrar el fichero.");
            }

            // 4. Borrar el segundo fichero
            if (fichero2.delete()) {
                System.out.println("Fichero " + fichero2.getName() + " borrado correctamente.");
            } else {
                System.out.println("No se pudo borrar " + fichero2.getName());
            }

        } catch (IOException e) {
            System.out.println("Error al crear los ficheros.");
            e.printStackTrace();
        }
    }
}

/*

Si ejecutas este programa desde eclipse,
el directorio "NUEVODIR" se crea dentro del **directorio de trabajo del proyecto**,
que normalmente corresponde a la carpeta raíz del proyecto (donde está la carpeta "src").


*/

