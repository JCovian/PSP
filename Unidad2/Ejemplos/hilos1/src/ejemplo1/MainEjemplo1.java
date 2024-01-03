package ejemplo1;

/**
 * Crea hilos de la clase HiloEjemplo1 (Thread)
 */
public class MainEjemplo1 {
    public static void main(String[] args) {
        HiloEjemplo1 h = null;
        for (int i = 0; i < 3; i++) {
            //Creamos hilo
            h = new HiloEjemplo1("" + (i+1));
            //Iniciamos hilo
            h.start();
        }
        System.out.println("Creados 3 hilos");
    }
}
