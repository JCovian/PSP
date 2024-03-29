public class Mensajes extends Thread {
    private ImprimeMensajes imprimeMensajes;
    private String mensaje;
    private int repeticiones;
    private int orden;

    public Mensajes(ImprimeMensajes imprimeMensajes, String mensaje, int repeticiones, int orden) {
        this.imprimeMensajes = imprimeMensajes;
        this.mensaje = mensaje;
        this.repeticiones = repeticiones;
        this.orden = orden;
    }

    @Override
    public void run() {
        for (int i = 1; i <= repeticiones; i++) {
            // Llama al metodo sincronizado para imprimir la palabra correspondiente
            imprimeMensajes.mostrar(mensaje, orden);
        }
    }
}
