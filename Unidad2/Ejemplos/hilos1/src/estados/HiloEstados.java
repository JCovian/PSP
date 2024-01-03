package estados;

/**
 * Ejemplo para mostrar el paso por varios estados de un hilo
 */
public class HiloEstados extends Thread {
    private volatile boolean stop = false;

    //El constructor simplemente asigna un nombre al hilo
    public HiloEstados(String nombre) {
        Thread.currentThread().setName(nombre);
    }

    public void stopThread() {
        stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            try {
                System.out.println("Duermo 10 segundos");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //ignoramos la excepción
            }
        }
        System.out.println("Termino");
    }

    public static void main(String[] args) throws InterruptedException {
        HiloEstados hilo = new HiloEstados("dormilon");
        hilo.start();
        System.out.println("El hilo " + hilo.getName() + " está en estado " + hilo.getState());
        //Le damos tiempo a pasar a dormido durmiendo un poco el hilo main
        Thread.sleep(1000);
        System.out.println("El hilo " + hilo.getName() + " está en estado " + hilo.getState());
        hilo.stopThread();
        hilo.join(); //esperamos a que termine efectivamente
        System.out.println("El hilo " + hilo.getName() + " está en estado " + hilo.getState());
    }
}
