package ejemplo2;

/**
 * Ejemplo de implementación de hilos mediante la implementación de la interfaz Runnable
 */
public class HiloEjemplo2 implements Runnable{
    private int x;

    public HiloEjemplo2(int x){
        this.x = x;
    }


    @Override
    public void run() {
        for (int i = 0; i < x; i++) {
            System.out.println("En " + Thread.currentThread().getName() + " => "  + i);
        }

    }
}
