package prioridades;

import java.util.HashMap;
import java.util.List;

/**
 * Ejemplo del uso de prioridades en hilos
 *
 * ATENCIÓN a la declaración de la variable booleana stop
 */
class ThreadPriority extends Thread {

    private int c = 0; //Un contador

    private static HashMap<String, Integer> ejecuciones= new HashMap<>();

    /*
     * Declaro la variable booleana que controla la parada como "volatile"
     * Si no, cada hilo lee su valor de una especie de memoria cache, y no recupera su valor actualizado
     * por lo que el programa principal no termina nunca (al tener hilos en ejecución)
     */

    private volatile boolean stop = false;

    public int getCounter() {
        return c;
    }

    public void stopThread() {
        stop = true;
    }

    //El método run simplemente incrementa el contador mientras no se le obligue a detenerse
    @Override
    public void run() {
        while (!stop) {
            c++;
        }
    }


    public static void main(String[] args) {
        //Creamos los 3 hilos
        ThreadPriority h1 = new ThreadPriority();
        ThreadPriority h2 = new ThreadPriority();
        ThreadPriority h3 = new ThreadPriority();

        //distinct priorities
        h1.setPriority(Thread.NORM_PRIORITY);
        h2.setPriority(Thread.MAX_PRIORITY);
        h3.setPriority(Thread.MIN_PRIORITY);

        h1.start();
        h2.start();
        h3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {
            //Ignoramos el tratamiento de la excepción
        }


        h1.stopThread();
        h2.stopThread();
        h3.stopThread();


        /* Teóricamente el contador de los hilos debería ser h2, h1, h3
        dada su prioridad. Pero no es así en muchos casos. El sistema no
        se basa sólo en la prioridad
         */
        System.out.println("h2 (máx priority): " + h2.getCounter());
        System.out.println("h1 (medium priority): " + h1.getCounter());
        System.out.println("h3 (low priority): " + h3.getCounter());
    }
}

