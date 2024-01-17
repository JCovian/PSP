package mpd.prsp.ut2;


import java.util.Scanner;


/*
            Entre 2 y 10 millones hay 664579 números primos
 */

public class MainMultiHilo {
    private final static int MAX = 10_000_000; //Buscaremos primos hasta este valor
    private final static int MIN_HILOS = 1; //Mínimo número de hilos admitidos
    private final static int MAX_HILOS = 30;//Máximo número de hilos admitidos

    public static void main(String[] args) {
        int numHilos;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.printf("Introduce el número de hilos a utilizar (%d-%d): ", MIN_HILOS, MAX_HILOS);
            numHilos = sc.nextInt();
        } while (numHilos < MIN_HILOS || numHilos > MAX_HILOS);



        //Añadido ejercicio 2
        ContadorCompartido contador = new ContadorCompartido();

        //Calculamos cuántos números verificará cada hilo
        int salto = (MAX - 2) / numHilos;

        long desde = 2;
        long hasta = desde + salto;

        //Creamos el array de hilos y vamos instanciando los hilos
        Thread[] arrayHilos = new CuentaPrimosHilo[numHilos];
        int i = 0;
        while (i < numHilos) {
            hasta = Math.min(hasta, MAX);//Nos aseguramos de que hasta no supere MAX
            //Pasamos a cada hilo el contador que deben usar para contabilizar los primos encontrados
            arrayHilos[i] = new CuentaPrimosHilo(i, desde, hasta, contador);
            desde = hasta + 1;
            hasta += salto;
            //Con esto, le damos más amplitud al último hilo para asegurar llegar hasta MAX
            long faltan = MAX - hasta;
            if (faltan < salto)
                hasta = MAX;
            i++;
        }

        //Contamos el tiempo total
        long startTime = System.currentTimeMillis();
        //Lanzamos los hilos
        for (Thread hilo : arrayHilos) {
            hilo.start();
        }
        //esperamos a que los hilos finalicen para contabilizar el tiempo de finalización
        for (Thread hilo : arrayHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total de los cálculos: " + (endTime - startTime)/1000.0);

        //Añadido ejercicio 2
        System.out.println("Total de primos encontrados: " + contador.getContador());
    }
}
