import java.util.concurrent.Semaphore;

public class ImprimeMensajes {
    public static void mostrar(String mensaje, Semaphore semaforoActual, Semaphore semaforoSiguiente) {
        try {
            semaforoActual.acquire();
            System.out.print(mensaje + " ");
            semaforoSiguiente.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
