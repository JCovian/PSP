/*
 * Programa usando hilos:
 * - Hilo 1 muestra 50 veces la palabra PIN
 * - Hilo 2 muestra 50 veces la palabra PAN
 * - Hilo 3 muestra 50 veces la palabra PUN
 * Usando notificaciones conseguir mostrar por pantalla:
 * PIN PAN PUN PIN PAN PUN PIN PAN PUN...
 */

public class AppMain {
    public static void main(String[] args) {
        //Utilizo este objeto para el bloqueo compartido
        Object bloqueo = new Object();

        //Crea las instancias de las clases que extienden Thread
        Mensajes hilo1 = new Mensajes("PIN",50,bloqueo,1);
        Mensajes hilo2 = new Mensajes("PAN",50, bloqueo,2);
        Mensajes hilo3 = new Mensajes("PUN",50,bloqueo,3);

        //Inicia los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        //Me aseguro que el hilo 1 sea el primero en ejecutarse
        synchronized (bloqueo){
            bloqueo.notify();
        }
    }
}
