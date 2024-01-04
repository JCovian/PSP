/*
 * Versión simplificada del ejercicio con semaforos
 */

import java.util.concurrent.Semaphore;

public class AppMain2 {

    // Se utiliza un Semaphore con un permiso para garantizar el orden correcto de impresión
    private static Semaphore semaforo1 = new Semaphore(1);
    private static Semaphore semaforo2 = new Semaphore(0);
    private static Semaphore semaforo3 = new Semaphore(0);

    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> imprimir("PIN",50, semaforo1, semaforo2));
        Thread hilo2 = new Thread(() -> imprimir("PAN", 50,semaforo2, semaforo3));
        Thread hilo3 = new Thread(() -> imprimir("PUN", 50,semaforo3, semaforo1));

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }

    private static void imprimir(String palabra, int repeticiones, Semaphore semaforoActual, Semaphore semaforoSiguiente) {
        for (int i = 0; i < repeticiones; i++) {
            try {
                // Adquirir el semáforo actual
                semaforoActual.acquire();

                // Imprimir la palabra
                System.out.print(palabra + " ");

                // Liberar el semáforo siguiente
                semaforoSiguiente.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}