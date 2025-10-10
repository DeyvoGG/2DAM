#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t pid, hijo_pid;
    int numero;

    printf("Introduce un número entero: ");
    scanf("%d", &numero);

    pid = fork(); // Creamos el proceso hijo

    if (pid == -1) { // Error al crear el proceso
        printf("No se ha podido crear el proceso hijo...\n");
        exit(-1);
    }

    if (pid == 0) { // Proceso hijo
        printf("\n--- PROCESO HIJO ---\n");
        printf("PID del hijo: %d, PID del padre: %d\n", getpid(), getppid());
        int resultado_hijo = numero + 4;
        printf("El hijo suma 4 a %d -> Resultado: %d\n", numero, resultado_hijo);
    } 
    else { // Proceso padre
        hijo_pid = wait(NULL); // Espera a que el hijo termine
        printf("\n--- PROCESO PADRE ---\n");
        printf("PID del padre: %d, PID de su padre (bash): %d\n", getpid(), getppid());
        int resultado_padre = numero - 5;
        printf("El padre resta 5 a %d -> Resultado: %d\n", numero, resultado_padre);
        printf("El proceso hijo con PID %d ha terminado.\n", hijo_pid);
    }

    return 0;
}
