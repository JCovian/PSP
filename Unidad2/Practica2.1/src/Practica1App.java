import java.util.Random;

public class Practica1App {
    public static void main(String[] args) {
        Random generador = new Random();
        Thread[] arrayHilos = new Thread[3];

        int valorAleatorio = generador.nextInt(1, 31);
        arrayHilos[0] = new HiloThread(valorAleatorio, "Hilo Thread");
        //arrayHilos[0].setName("Hilo Thread");

        valorAleatorio = generador.nextInt(1, 31);
        arrayHilos[1] = new Thread(new HiloRunnable(valorAleatorio), "Hilo runnable");

        valorAleatorio = generador.nextInt(1, 31);
        arrayHilos[2] = new Thread(() -> {
            int valorAleatorioLambda = generador.nextInt(1, 31);
            for (int i = 1; i <= valorAleatorioLambda; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }, "Hilo Lambda");

        for (Thread hilo : arrayHilos) {
            hilo.start();
        }

        for (Thread hilo : arrayHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Final del proceso");
    }
}
