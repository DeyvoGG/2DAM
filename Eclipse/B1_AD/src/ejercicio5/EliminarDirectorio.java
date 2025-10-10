package ejercicio5;

import java.io.File;

public class EliminarDirectorio {
    public static void main(String[] args) {
        File dir = new File("NUEVODIR"); // Directorio en el directorio actual

        if (!dir.exists()) {
            System.out.println("El directorio " + dir.getName() + " no existe.");
            return;
        }

        if (eliminarRecursivo(dir)) {
            System.out.println("Directorio eliminado correctamente: " + dir.getAbsolutePath());
        } else {
            System.out.println("No se pudo eliminar completamente el directorio: " + dir.getAbsolutePath());
        }
    }

    // Método recursivo para borrar directorios y archivos
    private static boolean eliminarRecursivo(File file) {
        if (file.isDirectory()) {
            File[] contenidos = file.listFiles();
            if (contenidos != null) {
                for (File f : contenidos) {
                    eliminarRecursivo(f); // llamada recursiva
                }
            }
        }

        // Intentar borrar el archivo/directorio
        if (file.delete()) {
            System.out.println("Eliminado: " + file.getAbsolutePath());
            return true;
        } else {
            System.out.println("Error al eliminar: " + file.getAbsolutePath());
            return false;
        }
    }
}