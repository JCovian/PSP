package mpd.prsp.ut2;

/*
 * Clase principal de la práctica primos y contador compartido
 * Tarea UT2
 * @author: Jose Aquilino Covián Ornia
 */

import java.util.Scanner;

public class MainMultiHilo {
    private static final int MAX = 10_000_000; //Buscaremos primos hasta este valor
    private static final int MIN_HILOS = 1; //Mínimo número de hilos admitidos
    private static final int MAX_HILOS = 30; //Máximo número de hilos admitidos
    private static final int MIN = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numHilos = 0;
        int rangos;
        int inicio, fin;
        CuentaPrimos contador = new CuentaPrimos(); //Acumula el número de primos calculado por cada hilo
        Thread[] arrayHilos;

        //Número de procesadores que intervienen en la operación
        int procesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Procesadores: " + procesadores);

        // Pide numero de hilos a utilizar
        while ((numHilos < MIN_HILOS) || (numHilos > MAX_HILOS)){
            System.out.print("Introduce el número de hilos a utilizar (1-30): ");
            if(!sc.hasNextInt()){
                sc.nextLine();
                System.err.println("Introduzca un valor numérico");
            } else numHilos = sc.nextInt();
            System.out.println("");
        }

        // Creamos un Array para los hilos
        arrayHilos = new Thread[numHilos];
        sc.close(); // Cierra el scanner, ya no se va a utilizar más
        rangos = calcularRangos(numHilos);
        inicio = 2;
        fin = inicio + rangos;
        for (int i = 0; i < numHilos; i++) {
            fin = Math.min(fin, MAX); // No aseguramos de que fin no supere MAX
            arrayHilos[i] = new CuentaPrimosHilo(i, inicio, fin, contador);
            inicio = fin + 1;
            fin += rangos;
            //Esto le da amplitud al último hilo para que llegue hasta MAX
            long quedan = MAX - fin;
            if(quedan < rangos){
                fin = MAX;
            }
        }

        //Contamos el tiempo total
        long startTime = System.currentTimeMillis();
        //Lanzamos los hilos
        for (Thread hilo: arrayHilos) {
            hilo.start();
        }
        // Esperamos a que los hilos finalicen para contabilizar el tiempo de finalizacion
        for (Thread hilo: arrayHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\nTiempo total de los cálculos: " + (endTime - startTime)/1000.0);
        System.out.println("Número total de primos: " + contador.numeroPrimos());
    }

    // Calcula cuantos rangos va a haber
    public static int calcularRangos(int cantidad) {
        return (MAX - MIN) / cantidad;
    }
}
