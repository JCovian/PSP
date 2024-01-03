package terminado;

/**
 * Ejemplo de la forma correcta de terminar un hilo
 * sin usar stop()
 * usando variables
 */
public class HiloEjemploTerminado extends Thread{
    //Variable que determinará si el hijo continúa su ejecución o termina
    private boolean stopHilo = false;


    public void pararHilo(){
        stopHilo = true;
    }

    @Override
    public void run() {
        while(!stopHilo){
            System.out.println("Sigo vivo!!");
        }
    }

    public static void main(String[] args) {
        HiloEjemploTerminado hilo = new HiloEjemploTerminado();
        System.out.println("Creamos el hilo");
        hilo.start();
        try {
            //Dejamos el hilo mostrar sus mensajes durante 2 segundos (dormimos al hilo main, no al creado)
            sleep(2000);

            //Lo detenemos con la función pararHilo
            hilo.pararHilo();

            //Esperamos a que el hilo muera con join()
            //Si lo comentáis veréis que tarda un poco en morir
            hilo.join();


            System.out.println("El hilo sigue vivo? " + hilo.isAlive());
            //Tarda en morir el jodío...
            sleep(2000);
            System.out.println("El hilo sigue vivo? " + hilo.isAlive());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}

