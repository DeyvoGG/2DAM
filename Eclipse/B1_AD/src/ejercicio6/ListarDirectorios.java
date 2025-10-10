package ejercicio6;

import java.io.File;

public class ListarDirectorios {
    public static void main(String[] args) {
       
        String ruta;
        if (args.length == 0) {
            ruta = System.getProperty("user.home") + "/Desktop/";
        } else {
            ruta = args[0];
        }

        File dir = new File(ruta);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("La ruta no es válida o no es un directorio: " + ruta);
            return;
        }

        System.out.println("Contenido de: " + dir.getAbsolutePath());
        listar(dir, 0); // Llamada inicial con nivel 0
    }

    // Función recursiva que lista directorios y ficheros con indentación
    private static void listar(File dir, int nivel) {
        File[] contenido = dir.listFiles();
        if (contenido == null) return;

        // Ordenamos para que quede más claro 
        java.util.Arrays.sort(contenido);

        for (File f : contenido) {
            // Crear indentación según nivel
            String sangria = "    ".repeat(nivel);

            if (f.isDirectory()) {
                System.out.println(sangria + "/" + f.getName());
                listar(f, nivel + 1); // Recursión
            } else {
                System.out.println(sangria + f.getName());
            }
        }
    }
}

/*
Le paso la ruta del escritoprio con lo cual por terminal me mostrara muchisimos archivos y directorios
*/
