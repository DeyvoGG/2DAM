#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int terminar = 0;

// --- Función para capturar señales y mostrar mensaje ---
void manejador(int sig) {
    printf("\nSeñal recibida: %d\n", sig);

    switch(sig) {
        case SIGINT:
            printf("Has enviado SIGINT (Ctrl + C).\n");
            break;
        case SIGTERM:
            printf("Has enviado SIGTERM (kill -TERM).\n");
            break;
        case SIGQUIT:
            printf("Has enviado SIGQUIT (Ctrl + \\).\n");
            break;
        case SIGTSTP:
            printf("Has enviado SIGTSTP (Ctrl + Z).\n");
            break;
        case SIGCONT:
            printf("Has enviado SIGCONT (continuar proceso).\n");
            break;
        default:
            printf("Señal desconocida.\n");
    }

    // Preguntamos al usuario si quiere terminar
    char opcion;
    printf("¿Deseas terminar el proceso? (s/n): ");
    opcion = getchar();
    getchar(); // Limpiar buffer

    if (opcion == 's' || opcion == 'S') {
        terminar = 1;
    } else {
        printf("Continuando ejecución...\n");
    }
}

int main() {
    printf("PID del proceso: %d\n", getpid());
    printf("Ejecutando bucle infinito...\n");

    // Capturamos señales
    signal(SIGINT, manejador);   // Ctrl + C
    signal(SIGTERM, manejador);  // kill -TERM
    signal(SIGQUIT, manejador);  // Ctrl + 
    signal(SIGTSTP, manejador);  // Ctrl + Z
    signal(SIGCONT, manejador);  // kill -CONT

   

    while (!terminar) {
        printf("Trabajando...\n");
        sleep(2);
    }

    printf("Proceso terminado por el usuario.\n");
    return 0;
}



