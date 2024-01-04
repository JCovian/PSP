/*
 * Programa usando hilos:
 * - Hilo 1 muestra 50 veces la palabra PIN
 * - Hilo 2 muestra 50 veces la palabra PAN
 * - Hilo 3 muestra 50 veces la palabra PUN
 * Usando semaforos conseguir mostrar por pantalla:
 * PIN PAN PUN PIN PAN PUN PIN PAN PUN...
 */

import java.util.concurrent.Semaphore;

public class AppMain {

    public static void main(String[] args) {
        // Crea una instancia de un objeto compartido entre los hilos y así los puedo bloquear
        ImprimeMensajes imprimeMensajes = new ImprimeMensajes();
        // Crea 3 semaforos para regular los tres hilos
        Semaphore semaforo1 = new Semaphore(1);
        Semaphore semaforo2 = new Semaphore(0);
        Semaphore semaforo3 = new Semaphore(0);

        //Crea las instancias de las clases que extienden Thread (los hilos)
        Mensajes hilo1 = new Mensajes(imprimeMensajes,"PIN",50,semaforo1, semaforo2);
        Mensajes hilo2 = new Mensajes(imprimeMensajes,"PAN",50,semaforo2, semaforo3);
        Mensajes hilo3 = new Mensajes(imprimeMensajes,"PUN",50,semaforo3, semaforo1);

        //Inicia los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
