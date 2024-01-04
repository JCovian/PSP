import java.util.concurrent.Semaphore;

public class Mensajes extends Thread {
    private ImprimeMensajes imprimeMensajes;
    private String mensaje;
    private int repeticiones;
    private Semaphore semaforoActual;
    private Semaphore semaforoSiguiente;

    public Mensajes(ImprimeMensajes imprimeMensajes, String mensaje, int repeticiones, Semaphore semaforoActual, Semaphore semaforoSiguiente) {
        this.imprimeMensajes = imprimeMensajes;
        this.mensaje = mensaje;
        this.repeticiones = repeticiones;
        this.semaforoActual = semaforoActual;
        this.semaforoSiguiente = semaforoSiguiente;
    }

    @Override
    public void run() {
        for (int i = 1; i <= repeticiones; i++) {
            // Llama al metodo sincronizado para imprimir la palabra correspondiente
            imprimeMensajes.mostrar(mensaje, semaforoActual, semaforoSiguiente);
        }
    }
}
