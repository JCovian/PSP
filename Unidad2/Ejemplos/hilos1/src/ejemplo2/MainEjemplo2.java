package ejemplo2;

/**
 * Lanzamos hilos con clases que implementan Runnable
 */
public class MainEjemplo2 {
    public static void main(String[] args) {
        HiloEjemplo2 h = null;
        for (int i = 0; i < 3; i++) {
            //Creamos hilo y le pasamos la implementación de Runnable, y/o un nombre para el hilo
            new Thread(new HiloEjemplo2(i+1), "hilo" + i).start();
        }
        System.out.println("Creados 3 hilos");
    }
}